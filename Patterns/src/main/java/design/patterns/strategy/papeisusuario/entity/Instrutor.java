package design.patterns.strategy.papeisusuario.entity;

import design.patterns.strategy.papeisusuario.papeis.impl.PapelInstrutor;
import org.springframework.stereotype.Component;

@Component
public class Instrutor extends Usuario {

    //	@Autowired
    public Instrutor() {
        papel = new PapelInstrutor();
    }

    @Override
    public void display() {
        System.out.println("Meu nome é " + this.getName());
        this.papel.perfil();
        System.out.println("");

    }

}
