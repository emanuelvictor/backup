package design.patterns.strategy.point.behavior.hitpoint.impl;

import org.springframework.stereotype.Component;

@Component
public class NotHitPoint extends AbstractHitPoint {
    //A definição de que não precisa bater ponto esta na classe mãe
    @Override
    public void hitPoint() {
        System.out.println(this.getPoints());
    }

}
