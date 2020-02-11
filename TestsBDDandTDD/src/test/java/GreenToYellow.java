import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import semaphore.Semaphore;

/**
 * Created by emanuelvictor on 01/09/14.
 */
public class GreenToYellow {
    Semaphore semaphore;

    @Given("Que o semaforo esta funcionando e eu estou na frente dele")
    public void semaphoreIsOn() {
        semaphore = null;
    }

    @When("A lanterna verde estiver acesa $green")
    public void whenSemaphoreIsGreen(String green) {
        semaphore = new Semaphore(green);
    }

    @Then("A lanterna deve ficar amarela $yellow")
    public void thenSemaphoreWillYellow(String yellow) {
        Assert.assertEquals(semaphore.next(), yellow);
    }
}
