package design.patterns.strategy.point.behavior.servicehour.impl;

import org.springframework.stereotype.Component;

@Component
public class FourHoursServicePerDay extends AbstractServiceHour {

    public FourHoursServicePerDay() {
        this.setServiceHour("Eu trabalho 8 horas por dia");
    }

    @Override
	public void serviceHour() {
		System.out.println(this.getServiceHour());
	}

	
}
