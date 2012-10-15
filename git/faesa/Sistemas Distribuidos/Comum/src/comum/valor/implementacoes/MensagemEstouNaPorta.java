/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package comum.valor.implementacoes;

import comum.base.Saida;
import comum.processo.Comunicacao;
import comum.valor.Comando;

/**
 *
 * @author Fred
 */
public class MensagemEstouNaPorta extends Comando {

    int porta;
    public MensagemEstouNaPorta(int porta) {
        this.porta=porta;
    }

    @Override
    public void executar(Comunicacao de) throws Exception {
        Saida.escrever("cliente esta na porta "+porta);
        de.setPortaServidora(porta);
    }
}
