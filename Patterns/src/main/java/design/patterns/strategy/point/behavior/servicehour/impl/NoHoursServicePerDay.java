package design.patterns.strategy.point.behavior.servicehour.impl;

import org.springframework.stereotype.Component;

@Component
public class NoHoursServicePerDay extends AbstractServiceHour {


	@Override
	public void serviceHour() {
		System.out.println(this.getServiceHour());
	}

}
