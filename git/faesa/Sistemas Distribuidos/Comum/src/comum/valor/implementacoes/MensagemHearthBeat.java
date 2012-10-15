package comum.valor.implementacoes;

import comum.base.Saida;
import comum.processo.Comunicacao;
import comum.processo.controle.RegistroDeClientesAtivos;
import comum.valor.Comando;
import comum.keep_alive.Corpo;
import java.util.GregorianCalendar;

public class MensagemHearthBeat extends Comando {

    public MensagemHearthBeat() {
        Saida.escrever("Enviando heartbeat");
    }
    
    
    public void executar(Comunicacao de) throws Exception {
        Corpo c=RegistroDeClientesAtivos.obterClienteComEstaComunicacao(de);
        GregorianCalendar d=new GregorianCalendar();
        try {
            c.estaVivo(d);
            Saida.escrever("Recebido heartbeat da comunicacao "+de);
        } catch(Exception e) {
            Saida.escrever("NÃ£o conseguiu processar heartbeat da comunicacao "+de);
        }
    }
}
