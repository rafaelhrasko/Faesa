package comum.valor.implementacoes.download;

import comum.base.ArquivoQueOServidorRetorna;
import comum.base.Saida;
import comum.processo.Comunicacao;
import comum.processo.Executor;
import comum.processo.TratadorDeMensagens;
import comum.valor.ClienteAtivo;
import comum.valor.Mensagem;
import comum.valor.implementacoes.requisicoes.MensagemRequisicaoPedacoArquivo;

class DownloadPedacoArquivo implements TratadorDeMensagens {

    DownloadArquivo da;
    int pedacoSorteadoParaBaixar;

    public DownloadPedacoArquivo(DownloadArquivo da, ArquivoQueOServidorRetorna vaiBaixar, int pedacoSorteadoParaBaixar, ClienteAtivo destino) throws Exception {
        Comunicacao com = null;
        try {
            this.da = da;
            this.pedacoSorteadoParaBaixar = pedacoSorteadoParaBaixar;
            com = Comunicacao.getInstance(destino);
            Executor exec = new Executor(com, this);
            new MensagemRequisicaoPedacoArquivo(vaiBaixar, pedacoSorteadoParaBaixar).vai(com);
        } catch (Exception e) {
            trataConexaoPerdida(com);
        }
    }

    public void executar(Mensagem recebida) throws Exception {
    }

    public void trataConexaoPerdida(Comunicacao com) {
        Saida.escrever("Tratando conexÃ£o perdida no download de pedaÃ§o de arquivo [" + pedacoSorteadoParaBaixar + "].");
        da.reiniciarPedaco(pedacoSorteadoParaBaixar);
    }
}
