package comum.processo.controle;

import comum.base.ArquivoQueOServidorRetorna;
import comum.base.Saida;
import comum.processo.Comunicacao;
import comum.valor.ClienteAtivo;
import java.util.Vector;

public class RegistroDeClientesAtivos {

    public static Vector clientes = new Vector();

    public static synchronized void adicionar(ClienteAtivo a) {
        clientes.add(a);
    }

    public static synchronized ClienteAtivo obterClienteComEstaComunicacao(Comunicacao com) throws Exception {
        for (Object o : clientes) {
            ClienteAtivo c = (ClienteAtivo) o;
            if (c.porta == com.getPortaServidora() && c.ip.equals(com.ip)) {
                return c;
            }
        }
        Saida.escrever("Está procurando um cliente na lista com uma porta e ip que não existem " + com + " a lista é " + clientes);
        return null;
    }

    public static synchronized void removerCliente(ClienteAtivo c) {
        if (c==null) {
            Saida.escrever("Não precisa excluir esse cliente pois parece que ele não existe.");
            return;
        }
        Saida.escrever("Excluindo cliente " + c);
        clientes.remove(c);
        ArquivoQueOServidorRetorna.excluirClienteDasListas(c);
        Saida.escrever("Conseguiu excluir o cliente " + c);
    }
}
