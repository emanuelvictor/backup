package design.patterns.strategy.userdetails.entity;

import org.springframework.stereotype.Component;

@Component
public class Aluno extends Usuario {

    public Aluno() {
        this.perfil = Perfil.ALUNO;
    }

}
