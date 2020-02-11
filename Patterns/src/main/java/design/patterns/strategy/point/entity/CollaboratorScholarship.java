package design.patterns.strategy.point.entity;

import design.patterns.strategy.point.behavior.hitpoint.impl.NotHitPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import design.patterns.strategy.point.behavior.function.impl.Scholarship;
import design.patterns.strategy.point.behavior.servicehour.impl.OptionalTime;

@Component
public class CollaboratorScholarship extends Collaborator {

//    public CollaboratorScholarship() {
//        super();
//    }

    @Autowired
    public CollaboratorScholarship(Scholarship scholarship, OptionalTime optionalTime, NotHitPoint notHitPoint) {
        serviceHour = optionalTime;
        hitPoint = notHitPoint;
        function = scholarship;
    }

    @Override
    public void display() {
        System.out.println("Meu nome é " + this.getName());
        this.function.function();
        this.serviceHour.serviceHour();
        this.hitPoint.hitPoint();
        System.out.println("");
    }

}
