package design.patterns.strategy.point.entity;

import design.patterns.strategy.point.behavior.function.impl.Executive;
import design.patterns.strategy.point.behavior.hitpoint.impl.NotHitPoint;
import design.patterns.strategy.point.behavior.servicehour.impl.FullTime;
import org.springframework.stereotype.Component;

@Component
public class CollaboratorExecutive extends Collaborator {
//	@Autowired
	public CollaboratorExecutive() {
		serviceHour = new FullTime();
		hitPoint = new NotHitPoint();
		function = new Executive();
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
