package design.patterns.strategy.role;

import org.junit.Assert;
import org.junit.Test;

import design.patterns.strategy.userdetails.entity.Administrador;
import design.patterns.strategy.userdetails.entity.Aluno;
import design.patterns.strategy.userdetails.entity.Instrutor;
import design.patterns.strategy.userdetails.entity.Perfil;
import design.patterns.strategy.userdetails.entity.Usuario;

import java.util.Arrays;
import java.util.HashSet;

/**
 *
 */
public class TestStrategyPatternRole {

	// Testes sem o spring
	// NOTA : Observe o acoplamento
	/**
	 * 
	 */
	@Test
	public void testWithoutSpring() {

		System.out.println();
		System.out.println("Role Project");
		System.out.println();

		Usuario usuario = new Aluno();
		usuario.displayMe();
		usuario.displayRoles();
		Assert.assertEquals(usuario.getPerfil().getAuthorities(), new HashSet<>(Arrays.asList(new Perfil[] { Perfil.ALUNO})));
		
		usuario = new Instrutor();
		usuario.displayMe();
		usuario.displayRoles();


		Assert.assertEquals(usuario.getPerfil().getAuthorities(), new HashSet<>(Arrays.asList(new Perfil[] { Perfil.ALUNO, Perfil.INSTRUTOR })));
		
		usuario = new Administrador();
		usuario.displayMe();
		usuario.displayRoles();

		Assert.assertEquals(usuario.getPerfil().getAuthorities(), new HashSet<>(Arrays.asList(new Perfil[] { Perfil.ALUNO, Perfil.INSTRUTOR, Perfil.ADMINISTRADOR })));

	}

}
