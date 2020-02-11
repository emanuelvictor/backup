package design.patterns.strategy.duck.behavior.fly;

import design.patterns.strategy.duck.behavior.fly.Fly;

// Patos que voam
public class NormalFlight implements Fly {

	@Override
	public void fly() {
		System.out.println("Eu posso voar");

	}

}
