package design.patterns.strategy.point.behavior.servicehour.impl;

import org.springframework.stereotype.Component;

@Component
public class TwoHoursServicePerDay extends AbstractServiceHour {

    public TwoHoursServicePerDay() {
        this.setServiceHour("Eu trabalho quatro horas por dia");
    }

    @Override
    public void serviceHour() {
        System.out.println(this.getServiceHour());
    }

}
