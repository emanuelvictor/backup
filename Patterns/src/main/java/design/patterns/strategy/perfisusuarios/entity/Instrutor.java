package design.patterns.strategy.perfisusuarios.entity;

import design.patterns.strategy.perfisusuarios.papeis.impl.InstrutorPerfil;
import org.springframework.stereotype.Component;

@Component
public class Instrutor extends Usuario {

    //	@Autowired
    public Instrutor() {
        perfil = new InstrutorPerfil();
    }

    @Override
    public void display() {
        System.out.println("Meu nome é " + this.getName());
        this.getPerfil().perfil();
    }

}
