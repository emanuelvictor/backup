package design.patterns.strategy.duck.behavior.quack;

import design.patterns.strategy.duck.behavior.quack.Quack;

public class SuperQuack implements Quack {

	@Override
	public void quack() {
		System.out.println("Eu posso grasnar muito alto");

	}

}
