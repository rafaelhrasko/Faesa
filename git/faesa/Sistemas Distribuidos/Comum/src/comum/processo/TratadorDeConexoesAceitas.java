/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package comum.processo;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Fred
 */
public interface TratadorDeConexoesAceitas {

    public void trataConexaoAceita(Comunicacao com) throws Exception;

}
