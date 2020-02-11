package design.patterns.strategy.duck.entity;

import design.patterns.strategy.duck.behavior.quack.NoQuack;
import design.patterns.strategy.duck.behavior.fly.NoFly;
import design.patterns.strategy.duck.behavior.swim.NormalSwim;
import org.springframework.beans.factory.annotation.Autowired;

//Pato que é um chamariz, ele não voa, não nada,

public class DecoyDuck extends Duck {
	@Autowired
	public DecoyDuck() {
		flyBehavior = new NoFly();
		quackBehavior = new NoQuack();
		swimBehavior = new NormalSwim();
	}

	@Override
	public void display() {
		System.out.println("Eu sou uma isca (pato de madeira)");
		flyBehavior.fly();
		quackBehavior.quack();
		swimBehavior.swim("Eu não nado, apenas flutuo");

	}

}
