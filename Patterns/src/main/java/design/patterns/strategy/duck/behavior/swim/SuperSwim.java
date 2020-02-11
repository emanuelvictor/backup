package design.patterns.strategy.duck.behavior.swim;

import design.patterns.strategy.duck.behavior.swim.Swim;

public class SuperSwim implements Swim {

	@Override
	public void swim(String swim) {
		System.out.println("Eu posso nadar como o acquaman");
		
	}

}
