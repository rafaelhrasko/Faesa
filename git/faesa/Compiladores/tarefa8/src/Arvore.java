import java.util.ArrayList;

public class Arvore {

    // O código abaixo foi usado para teste, descomentando ele, quando cria-se uma nova árvore, ele pede a lista pós fixada
    // da árvore anterior, para exibir o resultado, pois a chamada meDahPostFix não está sendo feita no javaCC ainda
    //static Arvore a;
    //
    //public Arvore() {
    //    if (a != null) {
    //         System.out.println("Já foi, veja a lista pós-fixada ->> "+a.meDahPostFix());
    //    }
    //    a = this;
    //}
    
    ArrayList<Item> itens = new ArrayList<Item>();
    String expressao = "";
    No raiz = null, ondeTah = null;

    private boolean ehOperador(No item) {
    	if (item.item == null) return false;
        return item.item.getTipo() == 'o';
    };

    private boolean ehInteiro(No item) {
    	if (item.item == null) return true;
        return item.item.getTipo() == 'i';
    }

    private int precedencia(Item i) {
        char c=i.getvalor().charAt(0);
        switch (c) {
        	case '~':
        		return 60;
            case '^':
                return 50;
            case '*':
            case '/':
                return 40;
            case '+':
            case '-':
                return 30;
            case '>':
            case '<':
            case '=':
            case '!':
            case 'M':
            case 'm':
            	return 20;
            case '&':
            	return 10;
            case '|':
            	return 0;
        }
        return 0;
    }

    private boolean maiorPrecedencia(No item, No item0) {
    	if (item.item == null) return false;
        return precedencia(item.item) > precedencia(item0.item);
    }

    public class No {

        No esq;
        No dir;
        No pai;
        Item item;

        private No(Item item) {
            this.item = item;
        }

        public String toString() {
            return "No " + item;
        }

        private void insereAcima(No este) throws Exception {
            if (pai != null) {
                este.pai = pai;
                pai.trocaFilho(this, este);
            }
            setaPai(este);
        }

        private void trocaFilho(No antigo, No novo) {
            if (dir == antigo) {
                dir = novo;

            }
            if (esq == antigo) {
                esq = novo;
                
            }
        }

        private void setaPai(No este) throws Exception {
            pai = este;
            este.incluiFilho(this);
        }

        private void incluiFilho(No a) throws Exception {
            if (esq == null) {
                esq = a;
                
            } else //if(dir == null)
            {
                dir = a;
                //else
                //    throw new Exception("ferrow!");
                
            }
        }

        private void insereAbaixo(No este) throws Exception {
            incluiFilho(este);
            este.setaPai(this);
        }

        private void passa(ArrayList<No> chupinha) {
            No este = this;
            while (este.pai != null) {
                este = este.pai;
                
            }
            este.passaCada(chupinha);
        }

        private void passaCada(ArrayList<No> chupinha) {
            //System.out.println("Item passando " + item);
            chupinha.add(0, this);
            if (esq != null) {
                esq.passaCada(chupinha);

            }
            if (dir != null) {
                dir.passaCada(chupinha);
            }
        }
    }
    
    public class NoArvore extends No
    {
    	Arvore minhaArvore;
    	public NoArvore(Arvore arvore){
    		super(null);
    		minhaArvore = arvore;
    	}
    	
    	public String toString()
    	{
    		return minhaArvore.meDahPostFix().toString();
    	}
    }
    
    public void addNo(No no)
    {
    	itens.add(no.item);
    	if (no.item != null) {    		
    		System.out.println(no.item.toString());
    	}
    	No este = no;

        if (raiz == null) {
            raiz = este;
            ondeTah = raiz;
            return;
        }
        
        if (ehOperador(no)) {
            if (ehInteiro(ondeTah)) {

                No ondeVaiInserirAcima = ondeTah;

                // decide se vai subir
                while (ondeVaiInserirAcima.pai != null && ondeVaiInserirAcima.pai != null
                        && ehOperador(ondeVaiInserirAcima.pai) && maiorPrecedencia(ondeVaiInserirAcima.pai, este)) {
                    //System.out.println("subindo para" + ondeVaiInserirAcima.pai);
                    ondeVaiInserirAcima = ondeVaiInserirAcima.pai;
                }

                try {
					ondeVaiInserirAcima.insereAcima(este);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                ondeTah = este;
            }
            return;
        }

        if (ehInteiro(no)) {
            if (ehOperador(ondeTah)) {
                try {
					ondeTah.insereAbaixo(este);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                ondeTah = este;
            }
            return;
        }
        
    }
    
    public void add(Arvore outra){
    	System.out.println("outra arvore,manolo" + outra.itens.toString());
    	this.addNo(new NoArvore(outra));
    }
    

    public void add(Item item) {
        try {            
            expressao += item.getvalor();
            //System.out.println("ADICIONOU NA ARVORE " + item.toString());

            No este = new No(item);
            
            this.addNo(este);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Item> meDahPostFix() {
        //System.out.println(itens);
        //System.out.println(expressao);
        ArrayList<No> chupinha=new ArrayList<No>();

        if (raiz != null) {
            raiz.passa(chupinha);
        }
        ArrayList<Item> chupinha2=new ArrayList<Item>();
        for (No no : chupinha) {
        	if (no.item == null){
        		for (Item item : ((NoArvore)no).minhaArvore.meDahPostFix()) {
        			chupinha2.add(item);
				}        		
        	}else{
        		chupinha2.add(no.item);
        	}
			
		}
        return chupinha2;
    }
}
