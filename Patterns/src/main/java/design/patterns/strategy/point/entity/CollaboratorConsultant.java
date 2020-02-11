package design.patterns.strategy.point.entity;

import org.springframework.stereotype.Component;

import design.patterns.strategy.point.behavior.function.impl.Consultant;
import design.patterns.strategy.point.behavior.hitpoint.impl.NotHitPoint;
import design.patterns.strategy.point.behavior.servicehour.impl.OptionalTime;

@Component
public class CollaboratorConsultant extends Collaborator {

    //	@Autowired
    public CollaboratorConsultant() {
        serviceHour = new OptionalTime();
        hitPoint = new NotHitPoint();
        function = new Consultant();
    }

    //@Autowired
    @Override
    public void display() {
        System.out.println("Meu nome é " + this.getName());
        this.function.function();
        this.serviceHour.serviceHour();
        this.hitPoint.hitPoint();
        System.out.println("");

    }

}
