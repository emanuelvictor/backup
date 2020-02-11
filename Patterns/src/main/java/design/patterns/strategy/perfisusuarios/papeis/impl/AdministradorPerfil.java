package design.patterns.strategy.perfisusuarios.papeis.impl;

import org.springframework.stereotype.Component;

@Component
public class AdministradorPerfil extends AbstractPerfil {

    public AdministradorPerfil() {
        this.setPerfil("Eu sou um administrador");
    }

    @Override
    public void perfil() {
        System.out.println(this.getPerfil());
    }

}
