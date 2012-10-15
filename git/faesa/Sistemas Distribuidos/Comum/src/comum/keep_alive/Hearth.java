package comum.keep_alive;

import comum.processo.Comunicacao;
import comum.valor.implementacoes.MensagemHearthBeat;

public class Hearth {

    public Hearth(final Comunicacao com) {
        new Thread() {
            @Override
            public void run() {
                while(true) {
                    try {
                        new MensagemHearthBeat().vai(com);
                        Thread.sleep(10000);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }.start();;
    }

}
