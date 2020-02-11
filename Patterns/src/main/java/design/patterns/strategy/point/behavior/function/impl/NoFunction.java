package design.patterns.strategy.point.behavior.function.impl;

import org.springframework.stereotype.Component;

@Component
public class NoFunction extends AbstractFunction {

    public NoFunction() {
        this.setFunction("Eu não tenho função :(");
    }

    @Override
    public void function() {
        System.out.println(this.getFunction());
    }

}
