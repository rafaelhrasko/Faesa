package cliente;

import comum.valor.implementacoes.MensagemMorri;
import comum.keep_alive.Hearth;
import comum.base.Diretorio;
import comum.base.Identificacao;
import comum.base.LeitorDeTeclado;
import comum.valor.implementacoes.MensagemEstouNaPorta;
import comum.processo.Executor;
import comum.valor.Mensagem;
import comum.valor.implementacoes.MensagemTexto;
import comum.base.Padroes;
import comum.base.Portas;
import comum.base.Saida;
import comum.processo.Comunicacao;
import comum.processo.Receptor;
import comum.processo.TratadorDeConexoesAceitas;
import comum.processo.TratadorDeMensagens;
import comum.processo.controle.ControleClienteAtivo;
import comum.valor.implementacoes.MensagemExit;
import comum.valor.implementacoes.download.DownloadsAtivos;
import comum.valor.implementacoes.requisicoes.MensagemRequisicaoBuscaArquivo;
import java.io.File;

public class Cliente implements TratadorDeConexoesAceitas, TratadorDeMensagens {

    long id=Identificacao.getId();
    File f;
    Diretorio d;

    public static void main(String[] args) {
        try {
            new Cliente();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    int porta = 0;

    public Cliente() throws Exception {

        // definindo diretorio e arquivos locais
        Padroes.pasta=Padroes.diretorioPadrao+"\\"+id+"\\";
        f=new File(Padroes.pasta);
        if (!f.exists()) {
            morre("Pasta não existe");
        }
        if (!f.isDirectory()) {
            morre("Pasta não é diretório");
        }
        d =new Diretorio(f);

        ControleClienteAtivo.setDiretorio(d);

        // conectando com o servidor
        Comunicacao com = Comunicacao.getInstance(Padroes.host, Padroes.porta);

        Executor c = new Executor(com, this);

        new MensagemTexto("toma servidor, eu sou o cliente").vai(com);
        new Hearth(com);

        // carregando downloads que estavam rodando
        DownloadsAtivos.carrega();
        
        // esperando outros clientes
        boolean conseguiu = false;
        do {
            try {
                porta = Portas.obterProxima();
                Receptor rec = new Receptor(this, this, porta);
                ControleClienteAtivo.ativar(rec);
                conseguiu = true;
                new MensagemEstouNaPorta(porta).vai(com);
            } catch (Exception e) {
            }
        } while (!conseguiu || Portas.acabou());

        // le linha de comando

        LeitorDeTeclado l=LeitorDeTeclado.getInstance();
        while(true) {
            new MensagemTexto("digite um comando").executar(null);

            String digitado=l.ler("COMANDO");
            if (digitado==null)
                continue;
            
            if (
                    digitado.equals("morrer") || digitado.equals("m") ||
                    digitado.equals("fechar") || digitado.equals("x") ||
                    digitado.equals("kill") || digitado.equals("quit")) {
                new MensagemMorri().vai(com);
                Thread.sleep(1000);
                new MensagemExit().executar(null);
            }

            if (digitado.equals("espera") || digitado.equals("wait")) {
                Thread.sleep(5000);
            }

            if (digitado.equals("download") || digitado.equals("d")) {

                new MensagemTexto("entre com o nome do arquivo").executar(null);
                String qual=l.ler("NOME");
                new MensagemRequisicaoBuscaArquivo(qual).vai(com);
                
            }


        }

        // se mata
        //new MensagemExit().executar(out);
    }

    public void trataConexaoAceita(Comunicacao com) throws Exception {
        new MensagemTexto("toma cliente, eu sou outro cliente, estou na porta " + porta).vai(com);
    }

    public void executar(Mensagem recebida) throws Exception {
        /*if (recebida instanceof MensagemClientesAtivos) {
            MensagemClientesAtivos mca = (MensagemClientesAtivos) recebida;

            for (Object c : mca.clientes) {
                ClienteAtivo cli = (ClienteAtivo) c;
                Socket kkSocket = cli.criarSocket();
                Comunicacao com = new Comunicacao(kkSocket, null);
                Executor exe = new Executor(com, this);
            }
        }*/
    }

    private void morre(String s) throws Exception {
        Saida.escrever(s);
        new MensagemExit().executar(null);
    }

    public void trataConexaoPerdida(Comunicacao com) {
    }
}
