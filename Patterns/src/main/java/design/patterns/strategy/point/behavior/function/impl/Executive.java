package design.patterns.strategy.point.behavior.function.impl;

import org.springframework.stereotype.Component;

@Component
public class Executive extends AbstractFunction {

    public Executive() {
        this.setFunction("Eu sou um (a) executivo (a)");
    }

    @Override
    public void function() {
        System.out.println(this.getFunction());
    }

}
