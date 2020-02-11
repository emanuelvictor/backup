package design.patterns.strategy.duck.behavior.fly;

import design.patterns.strategy.duck.behavior.fly.Fly;

public class SuperFly implements Fly {

	@Override
	public void fly() {
		System.out.println("Eu posso voar como um foguete");

	}

}
