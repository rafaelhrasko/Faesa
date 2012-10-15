package comum.valor.implementacoes;

import comum.base.Saida;
import comum.processo.Comunicacao;
import comum.valor.Comando;

public class MensagemTexto extends Comando {

    public String saida;
    public MensagemTexto(String texto) {
        saida=texto;
    }

    @Override
    public void executar(Comunicacao com) throws Exception {
        Saida.escrever(saida);
    }

    @Override
    public String toString() {
        return "Mensagem ["+saida+"]";
    }

}
