package design.patterns.strategy.papeisusuario.papeis.impl;

import design.patterns.strategy.papeisusuario.papeis.Papel;

/**
 * Created by emanuelvictor on 26/10/15.
 */
public abstract  class AbstractPapel implements Papel {

    String perfil = "Eu sou um papel";

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
