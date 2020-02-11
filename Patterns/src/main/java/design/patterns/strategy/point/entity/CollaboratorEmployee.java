package design.patterns.strategy.point.entity;

import design.patterns.strategy.point.behavior.function.impl.Employee;
import design.patterns.strategy.point.behavior.servicehour.impl.FourHoursServicePerDay;
import org.springframework.stereotype.Component;

import design.patterns.strategy.point.behavior.hitpoint.impl.FourPointsPerDay;

@Component
public class CollaboratorEmployee extends Collaborator {

    //	@Autowired
    public CollaboratorEmployee() {
        serviceHour = new FourHoursServicePerDay();
        hitPoint = new FourPointsPerDay();
        function = new Employee();
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
