package design.patterns.strategy.point.behavior.function.impl;

import org.springframework.stereotype.Component;

@Component
public class Employee extends AbstractFunction{

    public Employee() {this.setFunction( "Eu sou um (a) funcionário (a) normal");
    }

    @Override
	public void function() {
		System.out.println(this.getFunction());
	}

}
