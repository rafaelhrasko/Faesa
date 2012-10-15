package comum.keep_alive;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cerebro {

    public Cerebro(final Corpo cli,final Alma a) {
        new Thread() {
            @Override
            public void run() {
                while(true) {
                    try {
                        GregorianCalendar agoraMenos30=new GregorianCalendar();
                        agoraMenos30.add(Calendar.SECOND, -30);
                        if (agoraMenos30.after(cli.ultimoHeartBeat)) {
                            a.morre(cli);
                            try {
                                finalize();
                            } catch (Throwable ex) {
                                ex.printStackTrace();
                            }
                        }
                        Thread.sleep(30000);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }.start();
    }

}
