/**
 * 
 */
package br.mil.mar.dabm.common.domain.entity.aplicativo;

import org.junit.Assert;
import org.junit.Test;

import br.mil.mar.dabm.common.domain.entity.aplicativo.Aplicativo;
import br.mil.mar.dabm.common.domain.entity.aplicativo.Permissao;

/**
 * @author emanuelvictor
 *
 */
public class PermissaoTests
{
	/*-------------------------------------------------------------------
	 *				 		     UNIT TESTS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Test
	public void instanceOfPermissao()
	{

		Permissao permissao = new Permissao();

		Assert.assertNotNull( permissao );
		Assert.assertNull( permissao.getId() );
		Assert.assertNull( permissao.getIdentificador() );
		Assert.assertNull( permissao.getAplicativo() );
		Assert.assertNull( permissao.getPermissaoSuperior() );

	}

	/**
	 * 
	 */
	@Test
	public void instanceOfPermissaoAndGetters()
	{
		Permissao permissao = new Permissao( 1L, "Identificador", new Permissao(), new Aplicativo() );

		Assert.assertNotNull( permissao );
		Assert.assertNotNull( permissao.getId() );
		Assert.assertNotNull( permissao.getIdentificador() );
		Assert.assertNotNull( permissao.getAplicativo() );
		Assert.assertNotNull( permissao.getPermissaoSuperior() );
	}

	/*-------------------------------------------------------------------
	 *				 		     INTEGRATION TESTS
	 *-------------------------------------------------------------------*/

	/**
	 * Test do getIdentificador da permissao. Testa o loop recursivo para pegar todas as regras em uma só String
	 */
	@Test
	public void instanceOfPermissaoAndLoopRecursiveMustPass()
	{
		Aplicativo aplicativo = new Aplicativo();
		aplicativo.setIdentificador( "GOS" );

		Permissao permissao1 = new Permissao();
		permissao1.setAplicativo( aplicativo );
		permissao1.setIdentificador( "OrdemServicoService" );

		Permissao permissao2 = new Permissao();
		permissao2.setAplicativo( aplicativo );
		permissao2.setIdentificador( "listOrdemServicoByFilters" );
		permissao2.setPermissaoSuperior( permissao1 );

		Permissao permissao3 = new Permissao();
		permissao3.setAplicativo( aplicativo );
		permissao3.setIdentificador( "insertOrdemServico" );
		permissao3.setPermissaoSuperior( permissao2 );

		Permissao permissao4 = new Permissao();
		permissao4.setAplicativo( aplicativo );
		permissao4.setIdentificador( "updateOrdemServico" );
		permissao4.setPermissaoSuperior( permissao3 );

		Assert.assertNotNull( permissao4.getAuthority() );
		Assert.assertNotNull( permissao3.getAuthority() );
		Assert.assertNotNull( permissao2.getAuthority() );
		Assert.assertNotNull( permissao1.getAuthority() );
		Assert.assertEquals( "GOS.OrdemServicoService.listOrdemServicoByFilters.insertOrdemServico.updateOrdemServico", permissao4.getAuthority() );
		Assert.assertEquals( "GOS.OrdemServicoService.listOrdemServicoByFilters.insertOrdemServico", permissao3.getAuthority() );
		Assert.assertEquals( "GOS.OrdemServicoService.listOrdemServicoByFilters", permissao2.getAuthority() );
		Assert.assertEquals( "GOS.OrdemServicoService", permissao1.getAuthority() );

	}
}
