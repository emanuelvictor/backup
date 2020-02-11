package design.patterns.strategy.perfisusuarios.entity;

import design.patterns.strategy.perfisusuarios.papeis.impl.AlunoPerfil;
import org.springframework.stereotype.Component;

@Component
public class Aluno extends Usuario {

    //	@Autowired
    public Aluno() {
        perfil = new AlunoPerfil();
    }

    //@Autowired
    @Override
    public void display() {
        System.out.println("Meu nome é " + this.getName());
        this.getPerfil().perfil();
    }

}
