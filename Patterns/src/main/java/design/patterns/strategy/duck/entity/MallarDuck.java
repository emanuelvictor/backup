package design.patterns.strategy.duck.entity;

import design.patterns.strategy.duck.behavior.fly.NormalFlight;
import design.patterns.strategy.duck.behavior.quack.NormalQuack;
import design.patterns.strategy.duck.behavior.swim.NormalSwim;

public class MallarDuck extends Duck {
	public MallarDuck() {
		flyBehavior = new NormalFlight();
		quackBehavior = new NormalQuack();
		swimBehavior = new NormalSwim();
	}

	@Override
	public void display() {
		System.out.println("Eu sou um pato da raça pato-real (pato normal)");
		flyBehavior.fly();
		quackBehavior.quack();
		swimBehavior.swim(null);
	}
}
