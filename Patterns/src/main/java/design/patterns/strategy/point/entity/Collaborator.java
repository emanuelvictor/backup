package design.patterns.strategy.point.entity;

import design.patterns.strategy.point.behavior.function.Function;
import design.patterns.strategy.point.behavior.hitpoint.HitPoint;
import design.patterns.strategy.point.behavior.servicehour.ServiceHour;

// Classe mãe dos colaboradores, pode ser funcionário, estagiário, bolsista, executivo, dono, terceirizado. Et

public abstract class Collaborator extends People {

    protected HitPoint hitPoint;
    protected ServiceHour serviceHour;
    protected Function function;


    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ServiceHour getServiceHour() {
        return serviceHour;
    }

    public void setServiceHour(ServiceHour serviceHour) {
        this.serviceHour = serviceHour;
    }

    public HitPoint getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(HitPoint hitPoint) {
        this.hitPoint = hitPoint;
    }

    public abstract void display();

}
