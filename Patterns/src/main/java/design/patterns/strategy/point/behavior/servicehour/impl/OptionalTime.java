package design.patterns.strategy.point.behavior.servicehour.impl;

import org.springframework.stereotype.Component;

@Component
public class OptionalTime extends AbstractServiceHour {

    public OptionalTime() {
        this.setServiceHour("Eu trabalho a quantidade de horas que quiser");
    }

    @Override
	public void serviceHour() {
		System.out.println(this.getServiceHour());
	}

}
