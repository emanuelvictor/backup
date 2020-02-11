package design.patterns.strategy.point.behavior.function.impl;

import design.patterns.strategy.point.behavior.function.Function;

/**
 * Created by emanuelvictor on 26/10/15.
 */
public abstract  class AbstractFunction implements Function {

    String function = "Eu sou um colaborador";

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
