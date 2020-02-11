package design.patterns.strategy.duck.entity;

import design.patterns.strategy.duck.behavior.fly.SuperFly;
import design.patterns.strategy.duck.behavior.quack.SuperQuack;
import design.patterns.strategy.duck.behavior.swim.SuperSwim;

public class SuperDuck extends Duck{

	public SuperDuck() {
		flyBehavior = new SuperFly();
		quackBehavior = new SuperQuack();
		swimBehavior = new SuperSwim();
	}
	@Override
	public void display() {
		System.out.println("Eu sou um super pato");
		flyBehavior.fly();
		quackBehavior.quack();
		swimBehavior.swim(null);
	}

}
