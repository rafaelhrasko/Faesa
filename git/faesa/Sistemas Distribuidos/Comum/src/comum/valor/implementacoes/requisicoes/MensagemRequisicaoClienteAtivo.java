package comum.valor.implementacoes.requisicoes;

import comum.processo.Comunicacao;
import comum.valor.implementacoes.respostas.MensagemNaoTemClienteAtivo;
import comum.processo.controle.ControleClienteAtivo;
import comum.valor.Mensagem;
import comum.valor.MensagemComResposta;
import comum.valor.implementacoes.respostas.MensagemClienteAtivo;

public class MensagemRequisicaoClienteAtivo extends MensagemComResposta {

    private int cont;

    @Override
    public Mensagem gerarResposta(Comunicacao com) throws Exception {
        do {
            if (ControleClienteAtivo.ativo()) {
                return new MensagemClienteAtivo(ControleClienteAtivo.porta,ControleClienteAtivo.d);
            } else {
                Thread.sleep(1000);
            }
        } while (cont++ < 20);

        return new MensagemNaoTemClienteAtivo();
    }
}
