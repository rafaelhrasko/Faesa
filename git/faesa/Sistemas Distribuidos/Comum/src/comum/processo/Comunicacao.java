package comum.processo;

import comum.valor.ClienteAtivo;
import comum.valor.Mensagem;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;

public class Comunicacao {

    private static Hashtable<String, Comunicacao> comunicacoes_h = new Hashtable<String, Comunicacao>();
    private static ArrayList<Comunicacao> comunicacoes_l = new ArrayList<Comunicacao>();
    
    private void incluir(Comunicacao c) {
        comunicacoes_h.put(c.getChave(), c);
        comunicacoes_l.add(c);
    }
            

    private String getChave() {
        return getChave(ip,portaRemota);
    }

    private static String getChave(InetAddress host, int porta) {
        return getChave(host.getHostAddress(),porta);
    }
    
    private static String getChave(String host, int porta) {
        return host + "$" + porta;
    }

    private static Comunicacao getComunicacao(String host, int porta) {
        Comunicacao com = comunicacoes_h.get(getChave(host, porta));
        if (com!=null && com.out != null) {
            return com;
        }
        return null;
    }

    static Comunicacao createInstance(Socket clientSocket, ServerSocket serverSocket) throws Exception {
        return new Comunicacao(clientSocket, serverSocket);
    }

    public static Comunicacao getInstance(InetAddress ip, int porta) throws Exception {
        return getInstance(ip.getHostAddress(),porta);
    }

    public static Comunicacao getInstance(String ip, int porta) throws Exception {
        //Comunicacao com = getComunicacao(ip, porta);
        //if (com != null) {
//            return com;
//        }
        Socket kkSocket = new Socket(ip, porta);
        return new Comunicacao(kkSocket, null);
    }

    public static Comunicacao getInstance(ClienteAtivo destino) throws Exception {
        return getInstance(destino.ip, destino.porta);
    }
    public ObjectOutputStream out;
    public ObjectInputStream in;
    public InetAddress ip;
    private int portaServidora;
    private int portaRemota;

    public String toString() {
        return "Comunicação [" + ip.getHostAddress() + ":" + portaServidora + "]";
    }

    private Comunicacao(ObjectOutputStream out, ObjectInputStream in, InetAddress ip, int portaServidora, int portaRemota) {
        this.in = in;
        this.out = out;
        this.ip = ip;
        this.portaServidora = portaServidora;
        this.portaRemota=portaRemota;
        incluir(this);
    }

    private Comunicacao(Socket clientSocket, ServerSocket serverSocket) throws Exception {
        this(new ObjectOutputStream(clientSocket.getOutputStream()), new ObjectInputStream(clientSocket.getInputStream()), clientSocket != null ? clientSocket.getInetAddress() : null, -1,clientSocket.getPort());
    }

//    private Comunicacao(ClienteAtivo destino) throws Exception {
//        this(destino.criarSocket(), null);
//    }

    public void vai(Mensagem ob) throws Exception {
        out.writeObject(ob);
    }
    ClienteAtivo listener;

    public void setChangeListener(ClienteAtivo list) {
        listener = list;
    }

    public int getPortaServidora() {
        return portaServidora;
    }
    
    public void setPortaServidora(int porta) {
        portaServidora = porta;
        if (listener != null) {
            listener.porta = porta;
        }
    }
}
