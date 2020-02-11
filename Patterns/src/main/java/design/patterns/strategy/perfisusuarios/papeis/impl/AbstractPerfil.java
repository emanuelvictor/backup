package design.patterns.strategy.perfisusuarios.papeis.impl;

import design.patterns.strategy.perfisusuarios.papeis.Perfil;

/**
 * Created by emanuelvictor on 26/10/15.
 */
public abstract class AbstractPerfil implements Perfil {

    String perfil = "Eu sou um papel";

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
