package design.patterns.strategy.point.behavior.servicehour.impl;

import design.patterns.strategy.point.behavior.servicehour.ServiceHour;

/**
 * Created by emanuelvictor on 26/10/15.
 */
public abstract class AbstractServiceHour implements ServiceHour {

    String serviceHour = "Eu trabalho 0 horas por dia :(";

    public String getServiceHour() {
        return serviceHour;
    }

    public void setServiceHour(String serviceHour) {
        this.serviceHour = serviceHour;
    }
}
