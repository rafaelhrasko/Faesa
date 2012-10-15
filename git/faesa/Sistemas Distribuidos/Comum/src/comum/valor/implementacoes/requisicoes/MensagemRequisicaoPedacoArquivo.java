package comum.valor.implementacoes.requisicoes;

import comum.base.ArquivoQueOServidorRetorna;
import comum.valor.implementacoes.respostas.MensagemPedacoArquivo;
import comum.processo.Comunicacao;
import comum.valor.Mensagem;
import comum.valor.MensagemComResposta;

public class MensagemRequisicaoPedacoArquivo extends MensagemComResposta {

    ArquivoQueOServidorRetorna vaiBaixar;
    int pedacoSorteadoParaBaixar;

    public MensagemRequisicaoPedacoArquivo(ArquivoQueOServidorRetorna vaiBaixar, int pedacoSorteadoParaBaixar) {
        this.vaiBaixar=vaiBaixar;
        this.pedacoSorteadoParaBaixar=pedacoSorteadoParaBaixar;
    }

    public Mensagem gerarResposta(Comunicacao com) throws Exception {
        return new MensagemPedacoArquivo(vaiBaixar,pedacoSorteadoParaBaixar);
    }

}
