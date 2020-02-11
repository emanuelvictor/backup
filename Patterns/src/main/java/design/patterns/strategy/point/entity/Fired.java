package design.patterns.strategy.point.entity;

import design.patterns.strategy.point.behavior.hitpoint.impl.NotHitPoint;
import design.patterns.strategy.point.behavior.motive.Motive;
import org.springframework.stereotype.Component;

import design.patterns.strategy.point.behavior.function.impl.NoFunction;
import design.patterns.strategy.point.behavior.motive.impl.Foolish;
import design.patterns.strategy.point.behavior.servicehour.impl.NoHoursServicePerDay;
import design.patterns.strategy.point.behavior.function.Function;
import design.patterns.strategy.point.behavior.hitpoint.HitPoint;
import design.patterns.strategy.point.behavior.servicehour.ServiceHour;

@Component
public class Fired extends Collaborator {

    protected Motive motive;

    public Motive getMotive() {
        return motive;
    }

    public void setMotive(Motive motive) {
        this.motive = motive;
    }

    public void setFired(ServiceHour serviceHour, HitPoint hitPoint,
                         Function function) {
        this.serviceHour = serviceHour;
        this.hitPoint = hitPoint;
        this.function = function;
    }

    public Fired() {
        serviceHour = new NoHoursServicePerDay();
        hitPoint = new NotHitPoint();
        function = new NoFunction();
        motive = new Foolish();
    }

    public Fired(String name, Motive motive) {
        this.setName(name);
        this.serviceHour = new NoHoursServicePerDay();
        this.hitPoint = new NotHitPoint();
        this.function = new NoFunction();
        this.setMotive(motive);
    }

    @Override
    public void display() {
        System.out.println("Meu nome é " + this.getName());
        this.motive.motive();
        this.function.function();
        this.serviceHour.serviceHour();
        this.hitPoint.hitPoint();
        System.out.println("");

    }

}
