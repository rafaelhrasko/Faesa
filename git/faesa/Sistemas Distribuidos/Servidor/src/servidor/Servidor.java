package servidor;

import comum.base.Padroes;
import comum.base.Saida;
import comum.processo.Comunicacao;
import comum.processo.Receptor;
import comum.processo.TratadorDeConexoesAceitas;
import comum.processo.TratadorDeMensagens;
import comum.processo.controle.RegistroDeClientesAtivos;
import comum.valor.ClienteAtivo;
import comum.valor.Mensagem;
import comum.valor.implementacoes.MensagemTexto;
import comum.valor.implementacoes.requisicoes.MensagemRequisicaoClienteAtivo;
import comum.keep_alive.Alma;
import comum.keep_alive.Corpo;
import comum.processo.Purgatorio;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor implements TratadorDeConexoesAceitas, TratadorDeMensagens, Alma {

    @SuppressWarnings({"ResultOfObjectAllocationIgnored", "CallToThreadDumpStack"})
    public static void main(String[] args) {
        try {
            new Servidor();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("CallToThreadDumpStack")
    public Servidor() throws Exception {
        Purgatorio.setAlma(this);
        Receptor rec = new Receptor(this, this, Padroes.porta);
    }

    public void trataConexaoAceita(Comunicacao com) throws Exception {
        new MensagemTexto("toma cliente, eu sou o servidor").vai(com);
        //new MensagemRequisicaoArquivosDisponiveis().vai(com);
        new MensagemRequisicaoClienteAtivo().vai(com);
    }

    public void executar(Mensagem recebida) throws Exception {
    }

    public void trataConexaoPerdida(Comunicacao com) {
        ClienteAtivo c;
        try {
            Saida.escrever("Tratando conexao perdida");
            c = RegistroDeClientesAtivos.obterClienteComEstaComunicacao(com);
            RegistroDeClientesAtivos.removerCliente(c);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void morre(Corpo c) {
            Saida.escrever("Removendo cliente ");
        if (c instanceof ClienteAtivo) {
            ClienteAtivo cli=(ClienteAtivo)c;
            Saida.escrever("Removido cliente "+cli);
            RegistroDeClientesAtivos.removerCliente(cli);
        }
    }

}
