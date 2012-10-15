package comum.valor.implementacoes.respostas;

import comum.base.Diretorio;
import comum.base.Saida;
import comum.keep_alive.Cerebro;
import comum.processo.Comunicacao;
import comum.processo.Purgatorio;
import comum.processo.controle.RegistroDeClientesAtivos;
import comum.valor.ClienteAtivo;
import comum.valor.Comando;

public class MensagemClienteAtivo extends Comando {

    ClienteAtivo cli;
    int porta;
    Diretorio d;

    public MensagemClienteAtivo(int porta,Diretorio d) {
        this.porta = porta;
        this.d=d;
    }

    public String toString() {
        return cli.toString();
    }

    @Override
    public void executar(Comunicacao com) throws Exception {
        cli=new ClienteAtivo(com,d);
        RegistroDeClientesAtivos.adicionar(cli);

        Cerebro cer=new Cerebro(cli,Purgatorio.getAlma());

        Saida.escrever("Recebi do cliente "+cli+" o diretorio "+d);
    }

    public ClienteAtivo getClienteAtivo() {
        return cli;
    }
}
