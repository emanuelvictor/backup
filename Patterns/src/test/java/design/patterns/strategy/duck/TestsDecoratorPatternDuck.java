package design.patterns.strategy.duck;

import design.patterns.strategy.duck.behavior.fly.NormalFlight;
import design.patterns.strategy.duck.behavior.fly.SuperFly;
import design.patterns.strategy.duck.behavior.quack.NormalQuack;
import design.patterns.strategy.duck.behavior.quack.SuperQuack;
import design.patterns.strategy.duck.behavior.swim.NormalSwim;
import design.patterns.strategy.duck.behavior.swim.SuperSwim;
import design.patterns.strategy.duck.entity.DecoyDuck;
import design.patterns.strategy.duck.entity.Duck;
import design.patterns.strategy.duck.entity.MallarDuck;
import design.patterns.strategy.duck.entity.SuperDuck;
import org.junit.Test;

/**
 *
 */
public class TestsDecoratorPatternDuck {

    @Test
    public void testsDuck() {
        System.out.println("SimUDuck");
        System.out.println();

        Duck mallardDuck = new MallarDuck();
        mallardDuck.display();

        System.out.println("");
        Duck decoyDuck = new DecoyDuck();
        decoyDuck.display();

        System.out.println("");
        Duck superDuck = new SuperDuck();
        superDuck.display();
        System.out.println("");
        System.out.println("Pato-real roubou os poderes do super pato");
        System.out.println("");
        mallardDuck.setFlyBehavior(new SuperFly());
        mallardDuck.setQuackBehavior(new SuperQuack());
        mallardDuck.setSwimBehavior(new SuperSwim());
        mallardDuck.display();
        System.out.println("");
        superDuck.setFlyBehavior(new NormalFlight());
        superDuck.setQuackBehavior(new NormalQuack());
        superDuck.setSwimBehavior(new NormalSwim());
        superDuck.display();
    }
}
