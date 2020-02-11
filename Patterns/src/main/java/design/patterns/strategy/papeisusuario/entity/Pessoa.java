package design.patterns.strategy.papeisusuario.entity;

public abstract class Pessoa {
    protected String name = "User Name";

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }
}
