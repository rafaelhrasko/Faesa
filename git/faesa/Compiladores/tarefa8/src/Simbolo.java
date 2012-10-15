public class Simbolo {
	private String nome;      // nome do identificador
	private char tipo;        // tipo da variável
	private int ref;
	private static int referencia = 1;   // é uma referência usada na geração do código destino
	
	public Simbolo(char tipo, String nome) {
		this.setNome(nome);
		this.setTipo(tipo);	
	}

	public String toString() {
		return "Nome:"+this.getNome() + "\t" + "Tipo:"+this.getTipo();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
	
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	
	public static int getReferencia() {
		return referencia;
	}
	
	public static void setReferencia(int referencia) {
		Simbolo.referencia = referencia;
	}
	
	/*
	public static int nextRef(char tp) {
		int ref =Simbolo.getReferencia(); 
		if(tp == 'i'){
			Simbolo.setReferencia(Simbolo.getReferencia()+2);
		}else if(tp == 's'){
			Simbolo.setReferencia(Simbolo.getReferencia()+1);
		}	
		return ref; 
	}*/
	
}
