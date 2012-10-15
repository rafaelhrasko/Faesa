package comum.processo;

import comum.base.Saida;
import java.net.ServerSocket;
import java.net.Socket;

public class Receptor {

    TratadorDeConexoesAceitas trata;
    TratadorDeMensagens tm;
    Thread serv;
    int porta;

    public Receptor(TratadorDeConexoesAceitas t, final TratadorDeMensagens tm, final int porta) throws Exception {
        trata = t;
        this.tm=tm;
        this.porta = porta;

        ServerSocket hereSocket = null;
        hereSocket = new ServerSocket(porta);
        Saida.escrever("listening on: " + porta);
        final ServerSocket serverSocket = hereSocket;

        serv = new Thread() {

            public void run() {

                while (true) {
                    Socket clientSocket = null;
                    try {
                        Saida.escrever("Esperando cliente");
                        clientSocket = serverSocket.accept();
                        final Comunicacao com=Comunicacao.createInstance(clientSocket,serverSocket);
                        Saida.escrever("Com criado no receptor");
                        Executor c = new Executor(com,tm);
                        Saida.escrever("Executor criado no receptor");

                        new Thread() {

                            public void run() {
                                try {
                                    trata.trataConexaoAceita(com);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        serv.start();
    }

    public int getPorta() {
        return porta;
    }
}
