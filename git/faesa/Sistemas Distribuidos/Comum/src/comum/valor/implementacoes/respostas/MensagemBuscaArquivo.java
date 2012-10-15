package comum.valor.implementacoes.respostas;

import comum.base.ArquivoQueOServidorRetorna;
import comum.base.LeitorDeTeclado;
import comum.base.Saida;
import comum.processo.Comunicacao;
import comum.processo.controle.RegistroDeClientesAtivos;
import comum.valor.ClienteAtivo;
import comum.valor.Comando;
import comum.valor.implementacoes.download.DownloadArquivo;
import java.util.ArrayList;

public class MensagemBuscaArquivo extends Comando {

    ArrayList<ArquivoQueOServidorRetorna> arquivos;
    String nome;

    public MensagemBuscaArquivo(String nome) {
        this.nome = nome;

        arquivos = new ArrayList<ArquivoQueOServidorRetorna>();

        for (Object o : RegistroDeClientesAtivos.clientes) {
            ClienteAtivo c = (ClienteAtivo) o;

            Saida.escrever("BUSCA servidor verifica no cliente "+c+" quais arquivos ele tem");
            ArrayList<ArquivoQueOServidorRetorna> retorno = c.retornaArquivosComNome(nome);
            Saida.escrever("BUSCA o cliente "+c+" tem os arquivos "+retorno);

            adicionarEstesArquivosEncontrados(retorno);
            Saida.escrever("BUSCA os arquivos foram adicionados no todo, que ficou assim "+arquivos);
        }
    }

    private void adicionarEstesArquivosEncontrados(ArrayList<ArquivoQueOServidorRetorna> retorno) {
        for (ArquivoQueOServidorRetorna ar : retorno) {
            if (arquivos.indexOf(ar)==-1)
                arquivos.add(ar);
        }
    }

    @Override
    public void executar(Comunicacao de) throws Exception {
        Saida.escrever("Recebi estes arquivos disponiveis " + arquivos);

        LeitorDeTeclado d=LeitorDeTeclado.getInstance();

        Saida.escrever("Escolha um arquivo digitando seu numero:");

        int i=0;
        for (ArquivoQueOServidorRetorna ar:arquivos) {
            Saida.escrever(""+i+++": "+ar.toString());
        }
        
        int qual;
        do {
            Saida.escrever("Digite o numero:");
            qual=d.lerInt("NUMERO");
        } while(qual<0 || qual>=arquivos.size());

        ArquivoQueOServidorRetorna vaiBaixar=arquivos.get(qual);

        new DownloadArquivo(vaiBaixar);

    }
}
