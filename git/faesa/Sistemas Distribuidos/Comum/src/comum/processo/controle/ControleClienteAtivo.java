package comum.processo.controle;

import comum.base.Diretorio;
import comum.processo.Receptor;

public class ControleClienteAtivo {

    private static boolean ativo;
    public static int porta;
    public static Diretorio d;

    public static boolean ativo() {
        return ativo;
    }

    public static void ativar(Receptor rec) {
        porta = rec.getPorta();
        ativo = true;
    }

    public static void setDiretorio(Diretorio d2) {
        d=d2;
    }
}
