package comum.keep_alive;

import java.util.GregorianCalendar;

public abstract class Corpo {
    public GregorianCalendar ultimoHeartBeat=new GregorianCalendar();

    public void estaVivo(GregorianCalendar d) {
        ultimoHeartBeat=d;
    }
}
