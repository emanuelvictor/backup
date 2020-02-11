package design.patterns.strategy.papeisusuario.papeis.impl;

import org.springframework.stereotype.Component;

@Component
public class PapelAdministrador extends AbstractPapel {

    public PapelAdministrador() {
        this.setPerfil("Eu sou um administrador");
    }

    @Override
    public void perfil() {
        System.out.println(this.getPerfil());
    }

}
