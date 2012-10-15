package comum.valor;

import comum.processo.Comunicacao;

public abstract class Comando extends Mensagem {
    public abstract void executar(Comunicacao de)  throws Exception;
}
