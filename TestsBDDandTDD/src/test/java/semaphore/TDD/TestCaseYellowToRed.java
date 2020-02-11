package semaphore.TDD;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import semaphore.Semaphore;

/**
 * Created by emanuelvictor on 01/09/14.
 */
public class TestCaseYellowToRed {

    private static final String testInitColor = "yellow";
    private static final String testFinalColor = "red";

    private Semaphore semaphore;
    @Before // Acontece antes de realizar os testes, configurando semaforo
    public void setUp(){
        System.out.println("Inicializando teste");
        System.out.println("---> Se o semáforo esta "+testInitColor+" e deve ir para "+testFinalColor+ " <---");
        semaphore = new Semaphore(testInitColor);
    }

    @After //Acontece depois de realizar os testes, desligando semaforo
    public void tearDown(){
        System.out.println("Finalizando teste");
        System.out.println("---> A cor do semáforo é " + semaphore.getColor()+" <---");
        semaphore = null;
    }

    @Test
    public void test(){
        Assert.assertEquals(semaphore.next(), testFinalColor);
    }
}
