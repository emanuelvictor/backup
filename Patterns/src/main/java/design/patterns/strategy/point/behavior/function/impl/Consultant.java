package design.patterns.strategy.point.behavior.function.impl;

import org.springframework.stereotype.Component;

@Component
public class Consultant extends AbstractFunction {

    public Consultant() {
        this.setFunction("Eu sou um (a) consultor (a)");
    }

    @Override
	public void function() {
		System.out.println(this.getFunction());
	}

}
