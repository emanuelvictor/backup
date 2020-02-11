package design.patterns.strategy.duck.behavior.quack;

import design.patterns.strategy.duck.behavior.quack.Quack;

public class NoQuack implements Quack{

	@Override
	public void quack() {
		System.out.println("Eu não posso grasnar");
		
	}

}
