/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package comum.base;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author aluno
 */
public class LeitorDeTeclado {

    //static Scanner sc;
    static LeitorDeTeclado ldt;

    // Singleton
    public static LeitorDeTeclado getInstance() {
        if (ldt==null)
            return new LeitorDeTeclado();
        return ldt;
    }
    
    private LeitorDeTeclado() {
        //if (sc==null)
        //    sc=new Scanner(System.in);
        //ldt=this;
    }

    public synchronized String ler(String texto) {

        String titulo="";
        if (Identificacao.definida())
            titulo="Cliente "+Identificacao.getId();


        Saida.escreverSemPularLinha("["+titulo+"] DIGITE "+texto+": ");

        return JOptionPane.showInputDialog(null,texto,titulo,JOptionPane.INFORMATION_MESSAGE);
                //return sc.nextLine();
    }

    public int lerInt(String titulo) {
        String lido;
        do {
            try {
                lido=ler(titulo);
                return Integer.parseInt(lido.trim());
            } catch(NumberFormatException e) {
                Saida.escrever("Digite um numero correto.");
            }
        }while(true);
    }
}
