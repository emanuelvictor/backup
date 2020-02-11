package design.patterns.strategy.point.behavior.hitpoint.impl;

import org.springframework.stereotype.Component;

@Component
public class TwoPointsPerDay extends AbstractHitPoint {

    public TwoPointsPerDay() {
        this.setPoints("Eu bato o ponto 2 vezes por dia");
    }

    @Override
    public void hitPoint() {
        System.out.println(this.getPoints());
    }

}
