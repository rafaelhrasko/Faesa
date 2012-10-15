package comum.valor;

import comum.base.ArquivoQueOClienteInforma;
import comum.base.ArquivoQueOServidorRetorna;
import comum.base.Diretorio;
import comum.processo.Comunicacao;
import comum.keep_alive.Corpo;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ClienteAtivo extends Corpo implements Serializable {
    public int porta;
    public InetAddress ip;
    public Diretorio d;
    
    public String toString() {
        return "Cliente ["+ip+"-"+porta+"]";
    }

    public ClienteAtivo(Comunicacao com,Diretorio d) {
        this.porta=com.getPortaServidora();
        this.ip=com.ip;
        this.d=d;
        com.setChangeListener(this);
    }

    public Socket criarSocket() throws Exception {
        return new Socket(ip, porta);
    }

    public ArrayList<ArquivoQueOServidorRetorna> retornaArquivosComNome(String nome) {
        ArrayList<ArquivoQueOServidorRetorna> ar=new ArrayList<ArquivoQueOServidorRetorna>();

        for (ArquivoQueOClienteInforma ac:d.ar) {
            if (ac.bate(nome)) {

                boolean existe=ArquivoQueOServidorRetorna.existe(ac);

                if (existe) {
                    ArquivoQueOServidorRetorna jaTem=ArquivoQueOServidorRetorna.pega(ac);
                    jaTem.adicionar(this);
                    ar.add(jaTem);
                } else {
                    ArquivoQueOServidorRetorna arqserv=new ArquivoQueOServidorRetorna(ac);
                    arqserv.adicionar(this);
                    ar.add(arqserv);
                }
            }
        }

        return ar;
    }

}
