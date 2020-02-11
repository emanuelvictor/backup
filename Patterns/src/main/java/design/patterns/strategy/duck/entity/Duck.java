package design.patterns.strategy.duck.entity;

import design.patterns.strategy.duck.behavior.fly.Fly;
import design.patterns.strategy.duck.behavior.quack.Quack;
import design.patterns.strategy.duck.behavior.swim.Swim;

public abstract class Duck {
	Fly flyBehavior;
	Quack quackBehavior;
	Swim swimBehavior;

	public Fly getFlyBehavior() {
		return flyBehavior;
	}

	public void setFlyBehavior(Fly flyBehavior) {
		this.flyBehavior = flyBehavior;
	}

	public Quack getQuackBehavior() {
		return quackBehavior;
	}

	public void setQuackBehavior(Quack quackBehavior) {
		this.quackBehavior = quackBehavior;
	}

	public Swim getSwimBehavior() {
		return swimBehavior;
	}

	public void setSwimBehavior(Swim swimBehavior) {
		this.swimBehavior = swimBehavior;
	}

	public abstract void display();

	// Comportamento de voar
	public void performFly() {
		flyBehavior.fly();
	}

	// Comportamento de grasnar
	public void performQuack() {
		quackBehavior.quack();
	}

	// Nadar
	public void performSwim() {
		swimBehavior.swim(null);
		// System.out.println("Todos os patos nadam");
	}

}
