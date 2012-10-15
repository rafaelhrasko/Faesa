package comum.base;

import comum.valor.ClienteAtivo;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;

public class ArquivoQueOClienteInforma implements Serializable {

    protected File f;
    public int tamanho;
    public long hash;
    public String chave;

    ArquivoQueOClienteInforma(ArquivoQueOClienteInforma original) {
        f=original.f;
        tamanho=original.tamanho;
        hash=original.hash;
        chave=original.chave;
    }
    
    ArquivoQueOClienteInforma(File file) throws Exception {
        f=file;
        BufferedInputStream b=new BufferedInputStream(new FileInputStream(f));
        tamanho=b.available();
        int i;
        do {
            i=b.read();
            hash+=i;
        } while (i!=-1);

        chave=f.getName()+"|"+tamanho+"|"+hash;

        Saida.escrever("Definido arquivo COMPLETO "+this);
    }

    public String toString() {
        return "Arquivo ["+chave+"]";
    }

    public boolean bate(String nome) {
        return f.getName().toLowerCase().indexOf(nome.toLowerCase())!=-1;
    }
}
