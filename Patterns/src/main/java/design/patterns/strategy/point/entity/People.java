package design.patterns.strategy.point.entity;

public abstract class People {
    protected String name = "User Name";

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }
}
