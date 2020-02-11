/**
 * 
 */
package br.mil.mar.commons.dabm.test.domain.entity.aplicativo;

import org.junit.Assert;
import org.junit.Test;

import br.mil.mar.dabm.autenticador.domain.entity.aplicativo.Aplicativo;
import br.mil.mar.dabm.autenticador.domain.entity.aplicativo.Permissao;

/**
 * @author emanuelvictor
 *
 */
public class PermissaoTests
{
	/*-------------------------------------------------------------------
	 *				 		     UNIT TESTS
	 *-------------------------------------------------------------------*/
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

	@Test
	public void instanceOfPermissaoAndGetters()
	{
		Permissao permissao = new Permissao(1L, "Identificador", new Permissao(), new Aplicativo());

		Assert.assertNotNull( permissao );
		Assert.assertNotNull( permissao.getId() );
		Assert.assertNotNull( permissao.getIdentificador() );
		Assert.assertNotNull( permissao.getAplicativo() );
		Assert.assertNotNull( permissao.getPermissaoSuperior() );
	}
	
	
	/*-------------------------------------------------------------------
	 *				 		     INTEGRATION TESTS
	 *-------------------------------------------------------------------*/

}
