package comum.valor.implementacoes.download;

import comum.base.ArquivoQueOServidorRetorna;
import comum.base.Padroes;
import comum.base.Saida;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class DownloadsAtivos implements Serializable {

    static ArrayList<DownloadArquivo> downloads=new ArrayList<DownloadArquivo>();

    static void finaliza(DownloadArquivo aThis) {
        Saida.escrever("finalizou download "+aThis);
        downloads.remove(aThis);
        salva();
    }

    static void inclui(DownloadArquivo aThis) {
        Saida.escrever("iniciou download "+aThis);
        downloads.add(aThis);
        salva();
    }

    static synchronized void salva() {
        try {
            File f=new File(Padroes.pasta+"\\temp\\downloads.dat");
            
            if (f.exists()) {
                f.delete();
            }

            if (downloads.size()==0) {
                return;
            }
            
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(downloads);
            oos.close();
            Saida.escrever("salvou downloads "+downloads);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static synchronized void carrega() {
        try {
            File f=new File(Padroes.pasta+"\\temp\\downloads.dat");
            if (!f.exists()) {
                Saida.escrever("não vai carregar arquivo de downloads por que não existe.");
                return;
            }
            
            ObjectInputStream oos=new ObjectInputStream(new FileInputStream(f));
            downloads=(ArrayList<DownloadArquivo>) oos.readObject();
            oos.close();

            Saida.escrever("carregou downloads: "+downloads);
            for (DownloadArquivo d:downloads)
                d.reiniciar();
        } catch (Exception ex) {
            Saida.escrever("Não conseguiu abrir status de downloads.");
            ex.printStackTrace();
        }
    }

    public static DownloadArquivo obterDownload(ArquivoQueOServidorRetorna vaiBaixar) throws Exception {
        for (DownloadArquivo down:downloads) {
            if (down.vaiBaixar.chave.equals(vaiBaixar.chave))
                return down;
        }
        throw new Exception("Tentando identificar o download de um arquivo "+vaiBaixar.chave+" que não está sendo baixado.");
    }

}
