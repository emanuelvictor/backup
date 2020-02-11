package design.patterns.strategy.point.entity;

import design.patterns.strategy.point.behavior.function.impl.Trainee;
import design.patterns.strategy.point.behavior.hitpoint.impl.TwoPointsPerDay;
import design.patterns.strategy.point.behavior.servicehour.impl.TwoHoursServicePerDay;
import org.springframework.stereotype.Component;

@Component
public class CollaboratorTrainee extends Collaborator {
    //    @Autowired
    public CollaboratorTrainee() {
        this.setName("test");
        serviceHour = new TwoHoursServicePerDay();
        hitPoint = new TwoPointsPerDay();
        function = new Trainee();
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
