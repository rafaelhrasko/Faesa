package comum.valor.implementacoes.requisicoes;

import comum.processo.Comunicacao;
import comum.valor.Mensagem;
import comum.valor.MensagemComResposta;
import comum.valor.implementacoes.respostas.MensagemBuscaArquivo;

public class MensagemRequisicaoBuscaArquivo extends MensagemComResposta {

    String nome;
    public MensagemRequisicaoBuscaArquivo(String nome) {
        this.nome=nome;
    }

    @Override
    public Mensagem gerarResposta(Comunicacao com) throws Exception {
        return new MensagemBuscaArquivo(nome);
    }
}
