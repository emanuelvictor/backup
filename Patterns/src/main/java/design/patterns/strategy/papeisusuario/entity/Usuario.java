package design.patterns.strategy.papeisusuario.entity;

import design.patterns.strategy.papeisusuario.papeis.Papel;

public abstract class Usuario extends Pessoa {

    protected Papel papel;


    public Papel getPapel() {
        return papel;
    }

    public void setPapel(Papel papel) {
        this.papel = papel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void display();

}
