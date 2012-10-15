package comum.processo;

import comum.base.Saida;
import comum.valor.Comando;
import comum.valor.Mensagem;

public class Executor {

    Comunicacao com;
    TratadorDeMensagens tm;


    public Executor(Comunicacao com) {
        this(com,null);
    }

    public Executor(final Comunicacao com, final TratadorDeMensagens tm) {
        this.com = com;
        this.tm = tm;

        new Thread() {

            @SuppressWarnings("CallToThreadDumpStack")
            @Override
            public void run() {
                Mensagem recebida = null;
                do {
                    try {
                        recebida = null;
                        recebida = (Mensagem) com.in.readObject();
                        if (recebida != null) {
                            if (recebida instanceof Comando) {
                                try {
                                    Comando cmd=((Comando) recebida);
                                    cmd.executar(com);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            if (tm!=null)
                                tm.executar(recebida);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        recebida = null;
                    }
                } while (recebida != null);
                Saida.escrever("conexÃ£o perdida");
                tm.trataConexaoPerdida(com);
            }
        }.start();
    }

}
