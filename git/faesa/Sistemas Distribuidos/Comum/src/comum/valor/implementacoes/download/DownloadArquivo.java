package comum.valor.implementacoes.download;

import comum.base.Aleatorio;
import comum.base.ArquivoQueOServidorRetorna;
import comum.base.Padroes;
import comum.base.Saida;
import java.io.File;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;

public class DownloadArquivo implements Serializable {

    void reiniciarPedaco(int pedacoSorteadoParaBaixar) {
        temEstePedaco[pedacoSorteadoParaBaixar] = EstadoDeDownload.NAO_TEM;
        Saida.escrever("Pedaço de arquivo " + pedacoSorteadoParaBaixar + " marcado como NAO TEM.");
    }

    enum EstadoDeDownload {

        NAO_TEM,
        TEM,
        BAIXANDO
    };
    ArquivoQueOServidorRetorna vaiBaixar;
    EstadoDeDownload[] temEstePedaco;

    public synchronized void receberPedaco(int pedacoSorteadoParaBaixar, byte[] dados) {
        Saida.escrever("Recebeu pedaço " + pedacoSorteadoParaBaixar);
        try {
            int inicio;
            int fim;

            File arq = new File(vaiBaixar.pegaNomeTemporarioNaPasta());

            if (!arq.exists()) {
                arq.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(arq, "rw");
            inicio = pedacoSorteadoParaBaixar * Padroes.tamanhoDoPedaco;
            fim = inicio + (Padroes.tamanhoDoPedaco - 1);
            if (fim >= vaiBaixar.tamanho) {
                fim = vaiBaixar.tamanho - 1;
            }

            Saida.escrever("Vai salvar pedaço [" + pedacoSorteadoParaBaixar + "], inicio [" + inicio + "], fim [" + fim + "], do arquivo " + vaiBaixar.pegaNomeTemporario());

            //dados = new byte[fim - inicio + 1];
            raf.seek(inicio);
            raf.write(dados, 0, dados.length);
            raf.close();
            Saida.escrever("Lido com sucesso pedaço [" + pedacoSorteadoParaBaixar + "], inicio [" + inicio + "], fim [" + fim + "], do arquivo " + vaiBaixar.pegaNomeTemporario());

            DownloadsAtivos.salva();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        temEstePedaco[pedacoSorteadoParaBaixar] = EstadoDeDownload.TEM;
    }

    public DownloadArquivo(ArquivoQueOServidorRetorna vaiBaixar) {
        this.vaiBaixar = vaiBaixar;

        int quantosPedacos = vaiBaixar.obterQuantosPedacos();

        temEstePedaco = new EstadoDeDownload[quantosPedacos];

        for (int i = 0; i < temEstePedaco.length; i++) {
            temEstePedaco[i] = EstadoDeDownload.NAO_TEM;
        }

        DownloadsAtivos.inclui(this);

        iniciar();

    }

    public void reiniciar() {

        for (int i = 0; i < temEstePedaco.length; i++) {
            if (temEstePedaco[i] == EstadoDeDownload.BAIXANDO) {
                temEstePedaco[i] = EstadoDeDownload.NAO_TEM;
            }
        }
        iniciar();
    }

    public void iniciar() {

        final DownloadArquivo esteDownload = this;

        new Thread() {

            public void run() {
                Saida.escrever("Vai baixar o arquivo " + vaiBaixar);

                while (naoTerminou()) {

                    ArrayList<Integer> quaisNaoTem = new ArrayList<Integer>();
                    for (int i = 0; i < temEstePedaco.length; i++) {
                        if (temEstePedaco[i] == EstadoDeDownload.NAO_TEM) {
                            quaisNaoTem.add(i);
                        }
                    }

                    if (quaisNaoTem.size() > 0) {
                        int pedacoSorteadoParaBaixar = quaisNaoTem.get(Aleatorio.numeroAleatorioAte(quaisNaoTem.size()));
                        int clienteSorteado = Aleatorio.numeroAleatorioAte(vaiBaixar.clientes.size());

                        switch (temEstePedaco[pedacoSorteadoParaBaixar]) {
                            case NAO_TEM:

                                try {
                                    Saida.escrever("Vai baixar pedaço [" + pedacoSorteadoParaBaixar + "] do arquivo " + vaiBaixar.pegaNomeTemporario() + " do cliente " + clienteSorteado);
                                    temEstePedaco[pedacoSorteadoParaBaixar] = EstadoDeDownload.BAIXANDO;
                                    new DownloadPedacoArquivo(esteDownload, vaiBaixar, pedacoSorteadoParaBaixar, vaiBaixar.clientes.get(clienteSorteado));
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }

                                break;
                            case TEM:
                                break;
                            case BAIXANDO:
                                break;
                        }

                        try {
                            Thread.sleep(Padroes.TEMPO_ESPERA_ENTRE_DOWNLOADS);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }

                File f = new File(vaiBaixar.pegaNomeTemporarioNaPasta());
                File novo = new File(vaiBaixar.pegaNomeNaPasta());
                if (novo.exists()) {
                    Saida.escrever("Não vai renomear o arquivo do temp para a pasta de downloads pois o arquivo já existe. Apagar arquivo do temp.");
                    f.delete();
                } else {
                    f.renameTo(novo);
                }

                DownloadsAtivos.finaliza(esteDownload);

            }
        }.start();
    }

    private boolean naoTerminou() {
        boolean retorno = false;
        for (int i = 0; i < temEstePedaco.length; i++) {
            if (temEstePedaco[i] != EstadoDeDownload.TEM) {
                retorno = true;
            }
        }
        Saida.escrever(status());
        return retorno;
    }

    private String status() {
        StringBuilder sb = new StringBuilder("Status ");
        for (int i = 0; i < temEstePedaco.length; i++) {
            sb.append("" + i + ":" + temEstePedaco[i] + ", ");
        }
        sb.append(".");
        return sb.toString();
    }

    public String toString() {
        return "Download [" + vaiBaixar + " - " + status() + "]";
    }
}
