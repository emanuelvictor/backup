package design.patterns.strategy.point.behavior.hitpoint.impl;

import org.springframework.stereotype.Component;

@Component
public class FourPointsPerDay extends AbstractHitPoint {

    public FourPointsPerDay() {
        this.setPoints("Eu bato ponto quatro vezes por dia");
    }

    @Override
    public void hitPoint() {
        System.out.println(this.getPoints());
    }

}
