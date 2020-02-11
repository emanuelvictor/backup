package design.patterns.strategy.userdetails.entity;

import org.springframework.stereotype.Component;

@Component
public class Administrador extends Instrutor {

    public Administrador() {
        this.perfil = Perfil.ADMINISTRADOR;
    }

}
