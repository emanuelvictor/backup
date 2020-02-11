package design.patterns.strategy.duck.behavior.swim;

import design.patterns.strategy.duck.behavior.swim.Swim;

// Patos que não voam
public class NoSwim implements Swim {
	@Override
	public void swim(String swim) {
		System.out.println("Eu não nado");

	}

}
