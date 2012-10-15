package comum.base;

import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;

public class Diretorio implements Serializable {

    public File f;
    public File arq[];
    public ArquivoQueOClienteInforma ar[];
    public Diretorio(File f) {
        this.f=f;
        arq=f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                if (name.indexOf(".")!=-1 && name.indexOf(".$temp")==-1)
                    return true;
                else
                    return false;
            }
        });

        Saida.escreverSemPularLinha("Meus arquivos sao esses ");
        for(File fl:arq)
            Saida.escreverSemPularLinha(fl.getName()+", ");
        Saida.escrever();

        ar=new ArquivoQueOClienteInforma[arq.length];
        for(int i=0;i<arq.length;i++) {
            try {
                ar[i] = new ArquivoQueOClienteInforma(arq[i]);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public String toString() {
        StringBuilder sb=new StringBuilder();

        for(ArquivoQueOClienteInforma fl:ar) {
            sb.append(fl);
            sb.append(", ");
        }
        return "Diretorio ["+sb.toString()+"]";
    }

}
