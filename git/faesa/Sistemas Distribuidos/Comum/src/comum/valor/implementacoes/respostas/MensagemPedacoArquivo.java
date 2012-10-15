package comum.valor.implementacoes.respostas;

import comum.base.ArquivoQueOServidorRetorna;
import comum.base.Padroes;
import comum.base.Saida;
import comum.processo.Comunicacao;
import comum.valor.Comando;
import comum.valor.implementacoes.download.DownloadArquivo;
import comum.valor.implementacoes.download.DownloadsAtivos;
import java.io.FileInputStream;

public class MensagemPedacoArquivo extends Comando {

    ArquivoQueOServidorRetorna vaiBaixar;
    int pedacoSorteadoParaBaixar;
    byte []dados;
    int inicio;
    int fim;

    public MensagemPedacoArquivo(ArquivoQueOServidorRetorna vaiBaixar, int pedacoSorteadoParaBaixar) {
        this.vaiBaixar=vaiBaixar;
        this.pedacoSorteadoParaBaixar=pedacoSorteadoParaBaixar;
        try {
            FileInputStream fis = new FileInputStream(Padroes.pasta + "\\" + vaiBaixar.pegaNome());
            inicio=pedacoSorteadoParaBaixar*Padroes.tamanhoDoPedaco;
            fim=inicio+(Padroes.tamanhoDoPedaco-1);
            if (fim>=vaiBaixar.tamanho)
                fim=vaiBaixar.tamanho-1;

            Saida.escrever("Vai entregar pedaço ["+pedacoSorteadoParaBaixar+"], inicio ["+inicio+"], fim ["+fim+"], do arquivo "+vaiBaixar.pegaNome());

            dados=new byte[fim-inicio+1];
            fis.skip(inicio);
            fis.read(dados, 0, dados.length);
            fis.close();
            Saida.escrever("Lido com sucesso pedaço ["+pedacoSorteadoParaBaixar+"], inicio ["+inicio+"], fim ["+fim+"], do arquivo "+vaiBaixar.pegaNome());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void executar(Comunicacao de) throws Exception {
        Saida.escrever("Vai receber pedaço ["+pedacoSorteadoParaBaixar+"], inicio ["+inicio+"], fim ["+fim+"], do arquivo "+vaiBaixar.pegaNome());
        DownloadArquivo da=DownloadsAtivos.obterDownload(vaiBaixar);
        da.receberPedaco(pedacoSorteadoParaBaixar,dados);
    }

}
