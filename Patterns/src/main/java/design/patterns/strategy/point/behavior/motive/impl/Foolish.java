package design.patterns.strategy.point.behavior.motive.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Foolish extends AbstractMotive {

    @Autowired
    public void setMotive() {
        this.motive = "Corte no orçamento";
    }

    @Override
    public void motive() {
        System.out.println(this.getMotive());
    }

}
