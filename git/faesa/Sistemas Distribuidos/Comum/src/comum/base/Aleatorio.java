package comum.base;

import java.util.Random;

public class Aleatorio {

    static Random rand=new Random(System.currentTimeMillis());

    public static int numeroAleatorioAte(int quantosPedacos) {
        return rand.nextInt(quantosPedacos);
    }

}
