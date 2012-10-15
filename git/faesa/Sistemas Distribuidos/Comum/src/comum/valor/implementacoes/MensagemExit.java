package comum.valor.implementacoes;

import comum.processo.Comunicacao;
import comum.valor.Comando;

public class MensagemExit extends Comando {

    @Override
    public void executar(Comunicacao com) throws Exception {
        System.exit(0);
    }

}
