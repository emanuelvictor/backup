package design.patterns.strategy.duck.behavior.swim;

import design.patterns.strategy.duck.behavior.swim.Swim;

// COmportamento de nadar
public class NormalSwim implements Swim {

	@Override
	public void swim(String swim) {
		if (swim != null) {
			System.out.println(swim);
		} else {
			System.out.println("Eu posso nadar");
		}
	}

}
