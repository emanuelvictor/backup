package design.patterns.strategy.perfisusuarios.papeis.impl;

import org.springframework.stereotype.Component;

@Component
public class AlunoPerfil extends AbstractPerfil {

    public AlunoPerfil() {
        this.setPerfil("Eu sou um aluno");
    }

    @Override
	public void perfil() {
		System.out.println(this.getPerfil());
	}

}
