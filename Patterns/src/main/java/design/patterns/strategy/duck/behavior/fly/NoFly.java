package design.patterns.strategy.duck.behavior.fly;

import design.patterns.strategy.duck.behavior.fly.Fly;

// Patos que não voam
public class NoFly implements Fly {
	@Override
	public void fly() {
		System.out.println("Eu não posso voar");

	}

}
