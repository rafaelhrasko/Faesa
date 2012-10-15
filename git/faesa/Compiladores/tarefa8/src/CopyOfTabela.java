import java.util.HashMap;

public class CopyOfTabela {
      private HashMap<String,Simbolo> tab;
      public CopyOfTabela() {
            this.tab = new HashMap<String,Simbolo>();
      }
      public void inclui(Simbolo _simb) {
            if(this.tab.containsKey(_simb.getNome()))
            	System.out.println("Erro semantico\n" + _simb.getNome() + " ja existe!");
            else {
                  this.tab.put(_simb.getNome(),_simb);
            }
      }
      
      public boolean isExiste(String _chave) {
            return this.tab.containsKey(_chave);
      }
      public String toString() {
            return this.tab.toString();
      }
}