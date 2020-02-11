package design.patterns.strategy.papeisusuario.entity;

import design.patterns.strategy.papeisusuario.papeis.impl.PapelAluno;
import org.springframework.stereotype.Component;

@Component
public class Aluno extends Usuario {

    //	@Autowired
    public Aluno() {
        papel = new PapelAluno();
    }

    //@Autowired
    @Override
    public void display() {
        System.out.println("Meu nome é " + this.getName());
        this.papel.perfil();
        System.out.println("");

    }

}
