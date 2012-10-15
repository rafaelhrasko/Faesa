/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comum.base;

/**
 *
 * @author hrischter
 */
public class Saida {

    public static void escrever(String string) {
        escreverSemPularLinha(string+"\n");
    }

    public static void escrever() {
        escrever("");
    }

    public static synchronized void escreverSemPularLinha(String str) {
        System.out.print(str);
    }

}
