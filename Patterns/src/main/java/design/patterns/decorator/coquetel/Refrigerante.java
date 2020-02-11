package design.patterns.decorator.coquetel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by emanuelvictor on 27/10/15.
 */
@Component
public class Refrigerante extends CoquetelDecorator {

    public Refrigerante() {
    }

    //    @Autowired
    public Refrigerante(Coquetel coquetel) {
        super(coquetel);
        this.nome = "Refrigerante";
        this.preco = 1.0;
    }

    @Autowired
    public void setCoquetel(){
        this.nome = "Refrigerante";
        this.preco = 1.0;
    }
}
