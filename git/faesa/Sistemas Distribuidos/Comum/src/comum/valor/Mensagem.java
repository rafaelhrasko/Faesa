package comum.valor;

import comum.processo.Comunicacao;
import java.io.Serializable;

public abstract class Mensagem implements Serializable {

    public void vai(Comunicacao com) throws Exception {
        com.vai(this);
    }
}
