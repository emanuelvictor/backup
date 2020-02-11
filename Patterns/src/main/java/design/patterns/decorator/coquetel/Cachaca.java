package design.patterns.decorator.coquetel;

import org.springframework.stereotype.Component;

/**
 * Created by emanuelvictor on 27/10/15.
 */
@Component
public class Cachaca extends Coquetel {


    public Cachaca(){
        nome = "Cachaça";
        preco = 1.5;
    }
}
