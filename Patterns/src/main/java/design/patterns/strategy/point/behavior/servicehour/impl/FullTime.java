package design.patterns.strategy.point.behavior.servicehour.impl;

import org.springframework.stereotype.Component;

@Component
public class FullTime extends AbstractServiceHour {

    public FullTime() {
        this.setServiceHour("Eu trabalho em período integral");
    }

    @Override
	public void serviceHour() {
		System.out.println(this.getServiceHour());
	}

}
