package design.patterns.strategy.point.behavior.hitpoint.impl;

import design.patterns.strategy.point.behavior.hitpoint.HitPoint;

/**
 * Created by emanuelvictor on 26/10/15.
 */
public abstract class AbstractHitPoint implements HitPoint {

    String points = "Eu não preciso bater ponto";

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
