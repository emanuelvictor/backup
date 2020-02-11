package design.patterns.strategy.papeisusuario.papeis.impl;

import org.springframework.stereotype.Component;

@Component
public class PapelAluno extends AbstractPapel {

    public PapelAluno() {
        this.setPerfil("Eu sou um aluno");
    }

    @Override
	public void perfil() {
		System.out.println(this.getPerfil());
	}

}
