package design.patterns.strategy.point.behavior.function.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Scholarship extends AbstractFunction {

    public Scholarship() {
        //NOTA: Veja que realmente o 'setPapel' é executado somente após a construção do objeto
        this.setFunction("Eu era somente um novo funcionário");
    }

    @Override
    public void function() {
        System.out.println(this.getFunction());
    }

    // NOTA: A função da classe 'Scholarship' é setada dando-se um @Autowired no 'set'
    // Deste forma o spring entende que este método deve ser executado logo após o processo de construção do objeto
    @Autowired
    public void setFunction() {
        this.setFunction("Eu sou um (a) bolsista");
    }

}
