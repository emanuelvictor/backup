package design.patterns.strategy.perfisusuarios.entity;


import design.patterns.strategy.perfisusuarios.papeis.Perfil;

public abstract class Usuario {

    protected Perfil perfil;

    protected String name = "User Name";

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void display();

}
