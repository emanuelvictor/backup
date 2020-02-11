/**
 * 
 */
package br.mil.mar.singra;

import org.junit.Assert;
import org.junit.Test;

import br.mil.mar.dabm.singra.domain.entity.OrganizacaoMilitar;
import br.mil.mar.dpmm.rh.domain.entity.Pessoa;

/**
 * @author emanuelvictor
 *
 */
public class OrganizacaoMilitarTests
{

	/*-------------------------------------------------------------------
	 *				 		     UNIT TESTS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Test
	public void instanceOfOrganizacaoMilitar()
	{
		OrganizacaoMilitar organizacaoMilitar = new OrganizacaoMilitar();
		Assert.assertNotNull( organizacaoMilitar );
		Assert.assertNull( organizacaoMilitar.getId() );
		Assert.assertNull( organizacaoMilitar.getOrganizacaoMilitarSuperior() );
		Assert.assertNull( organizacaoMilitar.getAdministrador() );
		Assert.assertNull( organizacaoMilitar.getNome() );
		Assert.assertNull( organizacaoMilitar.getSigla() );
	}

	/**
	 * 
	 */
	@Test
	public void instanceOfOrganizacaoMilitarAndGetters()
	{
		OrganizacaoMilitar organizacaoMilitar = new OrganizacaoMilitar( 1L, "Nome da organização militar", null, null, "Sigla organização militar", "(45) 3577-6654" );

		Assert.assertNotNull( organizacaoMilitar );
		Assert.assertNotNull( organizacaoMilitar.getId() );
		Assert.assertNull( organizacaoMilitar.getOrganizacaoMilitarSuperior() );
		Assert.assertNull( organizacaoMilitar.getAdministrador() );
		Assert.assertNotNull( organizacaoMilitar.getNome() );
		Assert.assertNotNull( organizacaoMilitar.getSigla() );

	}

	/**
	 * 
	 */
	@Test // Testa criando a organização militar passando organização militar superior pelo construtor
	public void instanceOfOrganizacaoMilitarWithOrganizacaoMilitarSuperior()
	{
		OrganizacaoMilitar organizacaoMilitarSuperior = new OrganizacaoMilitar();

		OrganizacaoMilitar organizacaoMilitar = new OrganizacaoMilitar( 1L, "Nome da organização militar", organizacaoMilitarSuperior, null, "Sigla organização militar", "(45) 3577-6654" );

		Assert.assertNotNull( organizacaoMilitar );
		Assert.assertNotNull( organizacaoMilitar.getId() );
		Assert.assertNotNull( organizacaoMilitar.getOrganizacaoMilitarSuperior() );
		Assert.assertNull( organizacaoMilitar.getAdministrador() );
		Assert.assertNotNull( organizacaoMilitar.getNome() );
		Assert.assertNotNull( organizacaoMilitar.getSigla() );
	}

	/**
	 * 
	 */
	@Test // Testa construtor com a organização militar superior não sendo ela mesma
	public void instanceOfOrganizacaoMilitarWithOtherOrganizacaoMilitarSuperiorAndAdministrador()
	{

		OrganizacaoMilitar organizacaoMilitar = new OrganizacaoMilitar( 1L, "Nome da organização militar", null, null, "Sigla organização militar", "(45) 3577-6654" );

		organizacaoMilitar.setOrganizacaoMilitarSuperior( organizacaoMilitar );

		Assert.assertNotNull( organizacaoMilitar );
		Assert.assertNotNull( organizacaoMilitar.getId() );
		Assert.assertNotNull( organizacaoMilitar.getOrganizacaoMilitarSuperior() );
		Assert.assertNull( organizacaoMilitar.getAdministrador() );
		Assert.assertNotNull( organizacaoMilitar.getNome() );
		Assert.assertNotNull( organizacaoMilitar.getSigla() );

	}

	/*-------------------------------------------------------------------
	 *				 		     INTEGRATION TESTS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Test // Testa criando a organização militar passando o administrador e a organização militar superior no construtor
	public void instanceOfOrganizacaoMilitarWithOrganizacaoMilitarSuperiorAndAdministrador()
	{
		OrganizacaoMilitar organizacaoMilitarSuperior = new OrganizacaoMilitar();

		Pessoa pessoa = new Pessoa();

		OrganizacaoMilitar organizacaoMilitar = new OrganizacaoMilitar( 1L, "Nome da organização militar", organizacaoMilitarSuperior, pessoa, "Sigla organização militar", "(45) 3577-6654" );

		Assert.assertNotNull( organizacaoMilitar );
		Assert.assertNotNull( organizacaoMilitar.getId() );
		Assert.assertNotNull( organizacaoMilitar.getOrganizacaoMilitarSuperior() );
		Assert.assertNull( organizacaoMilitar.getAdministrador() );
		Assert.assertNotNull( organizacaoMilitar.getNome() );
		Assert.assertNotNull( organizacaoMilitar.getSigla() );
	}

	/**
	 * 
	 */
	@Test // Retornando o usuário administrador da Organização Militar que esta acima da Organização Militar Superior
	public void instanceOfOrganizacaoMilitarWithOtherOrganizacaoMilitarSuperiorAndOtherAdministrador()
	{

		Pessoa pessoa = new Pessoa();

		OrganizacaoMilitar organizacaoMilitarSuperior = new OrganizacaoMilitar( 1L, "Nome da organização militar superior", null, pessoa, "Sigla organização militar superior", "(45) 3577-6654" );

		OrganizacaoMilitar organizacaoMilitar = new OrganizacaoMilitar( 2L, "Nome da organização militar", organizacaoMilitarSuperior, null, "Sigla organização militar", "(45) 3577-6654" );

		organizacaoMilitar.setOrganizacaoMilitarSuperior( organizacaoMilitar );

		Assert.assertNotNull( organizacaoMilitar );
		Assert.assertNotNull( organizacaoMilitar.getId() );
		Assert.assertNotNull( organizacaoMilitar.getOrganizacaoMilitarSuperior() );
		Assert.assertNull( organizacaoMilitar.getAdministrador() );
		Assert.assertNotEquals( pessoa, organizacaoMilitar.getAdministrador() );

		Assert.assertNotNull( organizacaoMilitar.getNome() );
		Assert.assertNotNull( organizacaoMilitar.getSigla() );

	}
}
