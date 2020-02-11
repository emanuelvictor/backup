package design.patterns.strategy.perfisusuarios.papeis.impl;

import org.springframework.stereotype.Component;

@Component
public class InstrutorPerfil extends AbstractPerfil {

    public InstrutorPerfil() {
        this.setPerfil("Eu sou um instrutor");
    }

    @Override
    public void perfil() {
        System.out.println(this.getPerfil());
    }

}
