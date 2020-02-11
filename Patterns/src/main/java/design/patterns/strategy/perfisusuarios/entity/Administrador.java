package design.patterns.strategy.perfisusuarios.entity;

import design.patterns.strategy.perfisusuarios.papeis.impl.AdministradorPerfil;
import org.springframework.stereotype.Component;

@Component
public class Administrador extends Usuario {
//	@Autowired
	public Administrador() {
		perfil = new AdministradorPerfil();
	}

	@Override
	public void display() {
		System.out.println("Meu nome é " + this.getName());
		this.getPerfil().perfil();
	}

}
