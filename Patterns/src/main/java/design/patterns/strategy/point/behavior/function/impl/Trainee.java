package design.patterns.strategy.point.behavior.function.impl;

import org.springframework.stereotype.Component;

@Component
public class Trainee extends AbstractFunction {


    public Trainee() {
        this.setFunction("Eu sou um (a) estagiário (a)");
    }

    @Override
    public void function() {
        System.out.println(this.getFunction());
    }

}
