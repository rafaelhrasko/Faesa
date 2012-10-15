/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package comum.base;

import java.util.Scanner;

/**
 *
 * @author aluno
 */
public class Identificacao {
    private static boolean def=false;
    private static long id;
    public static long getId() {
        if (def) return id;
        Saida.escrever("Digite o id do cliente");
        LeitorDeTeclado ler=LeitorDeTeclado.getInstance();
        id=ler.lerInt("ID");
        def=true;
        return id;
    }

    static boolean definida() {
        return def;
    }
}
