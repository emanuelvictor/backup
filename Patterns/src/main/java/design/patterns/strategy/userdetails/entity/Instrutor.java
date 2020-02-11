package design.patterns.strategy.userdetails.entity;

import org.springframework.stereotype.Component;

@Component
public class Instrutor extends Aluno {

    public Instrutor() {
        this.perfil = Perfil.INSTRUTOR;
    }

}
