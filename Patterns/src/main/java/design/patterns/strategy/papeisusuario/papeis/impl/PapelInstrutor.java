package design.patterns.strategy.papeisusuario.papeis.impl;

import org.springframework.stereotype.Component;

@Component
public class PapelInstrutor extends AbstractPapel {

    public PapelInstrutor() {
        this.setPerfil("Eu sou um instrutor");
    }

    @Override
    public void perfil() {
        System.out.println(this.getPerfil());
    }

}
