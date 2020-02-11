package design.patterns.strategy.point.behavior.motive.impl;

import design.patterns.strategy.point.behavior.motive.Motive;

/**
 * Created by emanuelvictor on 26/10/15.
 */
public abstract class AbstractMotive implements Motive {

    String motive;

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }
}
