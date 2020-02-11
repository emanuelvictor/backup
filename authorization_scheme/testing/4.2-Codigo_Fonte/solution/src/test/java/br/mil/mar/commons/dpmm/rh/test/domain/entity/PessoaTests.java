/**
 * 
 */
package br.mil.mar.commons.dpmm.rh.test.domain.entity;

import org.junit.Assert;
import org.junit.Test;

import br.mil.mar.dabm.autenticador.domain.entity.usuario.Usuario;
import br.mil.mar.dabm.singra.domain.entity.OrganizacaoMilitar;
import br.mil.mar.dpmm.rh.domain.entity.Pessoa;

/**
 * @author emanuelvictor
 *
 */
public class PessoaTests
{

	/*-------------------------------------------------------------------
	 *				 		     UNIT TESTS
	 *-------------------------------------------------------------------*/
	@Test
	public void instanceOfPessoa()
	{
		Pessoa pessoa = new Pessoa();
		Assert.assertNotNull( pessoa );
		Assert.assertNull( pessoa.getId() );
		Assert.assertNull( pessoa.getOrganizacaoMilitar() );
		Assert.assertNull( pessoa.getCpf() );
		Assert.assertNull( pessoa.getNip() );
		Assert.assertNull( pessoa.getNomeCompleto() );
		Assert.assertNull( pessoa.getNomeGuerra() );
		Assert.assertNull( pessoa.getPostoGraduacao() );
		Assert.assertNull( pessoa.getUsuario() );
	}

	@Test
	public void instanceOfPessoaAndGetters()
	{
		Pessoa pessoa = new Pessoa( 1L, null, "070.747.627-11", "NúmeroNIP", "Fulano da Silva", "F. Silva", "Tenente", null );

		Assert.assertNotNull( pessoa );
		Assert.assertNotNull( pessoa.getId() );
		Assert.assertNull( pessoa.getOrganizacaoMilitar() );
		Assert.assertNotNull( pessoa.getCpf() );
		Assert.assertNotNull( pessoa.getNip() );
		Assert.assertNotNull( pessoa.getNomeCompleto() );
		Assert.assertNotNull( pessoa.getNomeGuerra() );
		Assert.assertNotNull( pessoa.getPostoGraduacao() );
		Assert.assertNull( pessoa.getUsuario() );
	}

	/*-------------------------------------------------------------------
	 *				 		     INTEGRATION TESTS
	 *-------------------------------------------------------------------*/

	@Test
	public void instanceOfPessoaWithOrganizacaoMilitarAndUsuario()
	{
		OrganizacaoMilitar organizacaoMilitar = new OrganizacaoMilitar();

		Usuario usuario = new Usuario();

		Pessoa pessoa = new Pessoa( 1L, organizacaoMilitar, "070.747.627-11", "NúmeroNIP", "Fulano da Silva", "F. Silva", "Tenente", usuario );

		Assert.assertNotNull( pessoa );
		Assert.assertNotNull( pessoa.getOrganizacaoMilitar() );
		Assert.assertNotNull( pessoa.getId() );
		Assert.assertNotNull( pessoa.getCpf() );
		Assert.assertNotNull( pessoa.getNip() );
		Assert.assertNotNull( pessoa.getNomeCompleto() );
		Assert.assertNotNull( pessoa.getNomeGuerra() );
		Assert.assertNotNull( pessoa.getPostoGraduacao() );
		Assert.assertNotNull( pessoa.getUsuario() );
	}
	
	
	
}
