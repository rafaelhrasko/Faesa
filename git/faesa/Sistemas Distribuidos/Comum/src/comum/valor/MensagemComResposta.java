package comum.valor;

import comum.processo.Comunicacao;
import java.io.ObjectOutputStream;

public abstract class MensagemComResposta extends Comando {

    public void executar(Comunicacao com) throws Exception {
         Mensagem m=gerarResposta(com);
         m.vai(com);
    }

    public abstract Mensagem gerarResposta(Comunicacao com) throws Exception;
}
