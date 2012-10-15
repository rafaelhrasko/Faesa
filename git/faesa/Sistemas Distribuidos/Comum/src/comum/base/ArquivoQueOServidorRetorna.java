package comum.base;

import comum.valor.ClienteAtivo;
import java.util.ArrayList;
import java.util.HashMap;

public class ArquivoQueOServidorRetorna extends ArquivoQueOClienteInforma {

    private static HashMap<String, ArquivoQueOServidorRetorna> existentes=new HashMap<String, ArquivoQueOServidorRetorna>();
    private static ArrayList<ArquivoQueOServidorRetorna> l_existentes=new ArrayList<ArquivoQueOServidorRetorna>();

    public ArrayList<ClienteAtivo> clientes=new ArrayList<ClienteAtivo>();

    public static void excluirClienteDasListas(ClienteAtivo c) {
        for (ArquivoQueOServidorRetorna ar:l_existentes) {
            ar.removerCliente(c);
        }
    }

    public static boolean existe(ArquivoQueOClienteInforma ac) {
        ArquivoQueOServidorRetorna x=existentes.get(ac.chave);
        if (x==null)
            return false;
        else
            return true;
    }

    public static ArquivoQueOServidorRetorna pega(ArquivoQueOClienteInforma ac) {
        return existentes.get(ac.chave);
    }

    public ArquivoQueOServidorRetorna(ArquivoQueOClienteInforma a) {
        super(a);
        existentes.put(chave, this);
        l_existentes.add(this);
    }

    public String toString() {
        return "Arquivo no Servidor ["+super.toString()+", clientes "+clientes+"]";
    }

    public synchronized void adicionar(ClienteAtivo cl) {
        if (clientes.indexOf(cl)!=-1)
            return;
        clientes.add(cl);
    }

    public int obterQuantosPedacos() {
        long resto=tamanho%Padroes.tamanhoDoPedaco;
        if (resto==0)
            return (int)(tamanho/Padroes.tamanhoDoPedaco);
        return (int)((tamanho/Padroes.tamanhoDoPedaco)+1);
    }

    public String pegaNomeTemporario() {
        return f.getName()+".$temp";
    }

    public String pegaNome() {
        return f.getName();
    }

    public String pegaNomeTemporarioNaPasta() {
        return Padroes.pasta + "\\temp\\" + pegaNomeTemporario();
    }

    public String pegaNomeNaPasta() {
        return Padroes.pasta + pegaNome();
    }

    private synchronized void removerCliente(ClienteAtivo c) {
        clientes.remove(c);
    }
}
