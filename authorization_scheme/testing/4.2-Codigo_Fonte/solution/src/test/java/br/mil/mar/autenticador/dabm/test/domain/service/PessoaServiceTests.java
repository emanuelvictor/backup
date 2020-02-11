/**
 * 
 */
package br.mil.mar.autenticador.dabm.test.domain.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import br.mil.mar.commons.dabm.test.domain.AbstractIntegrationTests;
import br.mil.mar.dabm.autenticador.domain.entity.usuario.Usuario;
import br.mil.mar.dabm.autenticador.domain.service.PessoaService;

/**
 * @author Boz
 *
 */
public class PessoaServiceTests extends AbstractIntegrationTests
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/

	@Autowired
	private PessoaService pessoaService;


	/*-------------------------------------------------------------------
	 *				 		      TESTS
	 *-------------------------------------------------------------------*/

	/**
	 */ 
	@Test
	public void findUsuarioByPessoaCPFMustPass()
	{
		final Usuario usuario= this.pessoaService.findUsuarioByPessoaCPF( "12312313" );
	
		Assert.assertNotNull( usuario );
		Assert.assertNotNull( usuario.getOrganizacaoMilitar() );
		Assert.assertNotNull( usuario.getNomeCompleto() );

	}
	@Test
	public void findUsuarioByPessoaIdMustPass()
	{
		final Usuario usuario= this.pessoaService.findUsuarioByPessoaCPF( "1" );
		Assert.assertNotNull( usuario );
		Assert.assertNotNull( usuario.getOrganizacaoMilitar() );
		Assert.assertNotNull( usuario.getNomeCompleto() );

	}
	@Test
	public void findUsuarioByPessoaNIPMustPass()
	{
		final Usuario usuario= this.pessoaService.findUsuarioByPessoaNIP( "12312313" );
		
		Assert.assertNotNull( usuario );
		Assert.assertNotNull( usuario.getOrganizacaoMilitar() );
		Assert.assertNotNull( usuario.getNomeCompleto() );

	}
	@Test
	public void listUsuarioByFiltersMustReturnAll()
	{
		final Page<Usuario> usuario = this.pessoaService.listUsuarioByFilters ( "", null );

		Assert.assertNotNull( usuario );
	}
}
