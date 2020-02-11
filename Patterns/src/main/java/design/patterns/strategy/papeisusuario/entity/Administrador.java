package design.patterns.strategy.papeisusuario.entity;

import design.patterns.strategy.papeisusuario.papeis.impl.PapelAdministrador;
import org.springframework.stereotype.Component;

@Component
public class Administrador extends Usuario {
//	@Autowired
	public Administrador() {
		papel = new PapelAdministrador();
	}

	@Override
	public void display() {
		System.out.println("Meu nome é " + this.getName());
		this.papel.perfil();
		System.out.println("");

	}

}
