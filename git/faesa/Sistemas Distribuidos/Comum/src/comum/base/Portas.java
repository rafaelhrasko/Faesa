/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package comum.base;

/**
 *
 * @author Fred
 */
public class Portas {

    private static int atual=Padroes.porta+1;
    public static int obterProxima() {
        return atual++;
    }

    public static boolean acabou() {
        return atual>Padroes.porta+1000;
    }

}
