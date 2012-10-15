package comum.valor.implementacoes;

import comum.processo.Comunicacao;
import comum.processo.controle.RegistroDeClientesAtivos;
import comum.valor.ClienteAtivo;
import comum.valor.Comando;

public class MensagemMorri extends Comando {

    @Override
    public void executar(Comunicacao de) throws Exception {
        ClienteAtivo c=RegistroDeClientesAtivos.obterClienteComEstaComunicacao(de);
        RegistroDeClientesAtivos.removerCliente(c);
    }

}
