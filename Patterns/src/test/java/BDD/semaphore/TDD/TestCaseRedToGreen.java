package BDD.semaphore.TDD;

import BDD.semaphore.Semaphore;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by emanuelvictor on 01/09/14.
 */
public class TestCaseRedToGreen {

    private static final String testInitColor = "red";
    private static final String testFinalColor = "green";

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
