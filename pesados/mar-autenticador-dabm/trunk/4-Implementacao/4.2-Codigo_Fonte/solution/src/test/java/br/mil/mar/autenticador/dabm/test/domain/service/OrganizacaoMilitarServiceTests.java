/**
 * 
 */
package br.mil.mar.autenticador.dabm.test.domain.service;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.mil.mar.autenticador.dabm.test.domain.AbstractIntegrationTests;
import br.mil.mar.dabm.autenticador.domain.service.OrganizacaoMilitarService;
import br.mil.mar.dabm.singra.domain.entity.OrganizacaoMilitar;

/**
 * @author Boz
 *
 */
public class OrganizacaoMilitarServiceTests extends AbstractIntegrationTests
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/

	@Autowired
	private OrganizacaoMilitarService organizacaoMilitarService;


	/*-------------------------------------------------------------------
	 *				 		      TESTS
	 *-------------------------------------------------------------------*/

	/**
	 */ 
	@Test
	public void findOrganizacaoMilitarByIdMustPass()
	{
		final OrganizacaoMilitar organizacaoMilitar = this.organizacaoMilitarService.findOrganizacaoMilitarById( 2001L );
					
		Assert.assertNotNull( organizacaoMilitar );
		Assert.assertNotNull( organizacaoMilitar.getId() );
		Assert.assertNotNull( organizacaoMilitar.getNome() );
		Assert.assertNotNull( organizacaoMilitar.getSigla() );
		Assert.assertNotNull( organizacaoMilitar.getAdministrador() );

	}
	@Test
	public void listOrganizacaoMilitarByFiltersMustReturnAll()
	{
		final Set<OrganizacaoMilitar> organizacaoMilitar = this.organizacaoMilitarService.listOrganizacaoMilitarByFilters( "", null );

		Assert.assertNotNull( organizacaoMilitar );
	}
}
