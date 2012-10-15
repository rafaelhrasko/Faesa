package comum.processo;

import comum.valor.Mensagem;

public interface TratadorDeMensagens {

    public void executar(Mensagem recebida) throws Exception;
    public void trataConexaoPerdida(Comunicacao com);

}
