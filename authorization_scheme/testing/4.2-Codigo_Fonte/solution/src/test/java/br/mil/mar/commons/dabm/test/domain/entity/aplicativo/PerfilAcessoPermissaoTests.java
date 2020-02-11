/**
 * 
 */
package br.mil.mar.commons.dabm.test.domain.entity.aplicativo;

import org.junit.Assert;
import org.junit.Test;

import br.mil.mar.dabm.autenticador.domain.entity.aplicativo.PerfilAcesso;
import br.mil.mar.dabm.autenticador.domain.entity.aplicativo.PerfilAcessoPermissao;
import br.mil.mar.dabm.autenticador.domain.entity.aplicativo.Permissao;

/**
 * @author emanuelvictor
 *
 */
public class PerfilAcessoPermissaoTests
{/*-------------------------------------------------------------------
 *				 		     UNIT TESTS
 *-------------------------------------------------------------------*/
	@Test
	public void instanceOfPerfilAcessoPermissao()
	{

		PerfilAcessoPermissao perfilAcessoPermissao = new PerfilAcessoPermissao();

		Assert.assertNotNull( perfilAcessoPermissao );
		Assert.assertNull( perfilAcessoPermissao.getId() );
		Assert.assertNull( perfilAcessoPermissao.getPerfilAcesso() );
		Assert.assertNull( perfilAcessoPermissao.getPermissao() );

	}

	@Test
	public void instanceOfPerfilAcessoPermissaoAndGetters()
	{
		PerfilAcessoPermissao perfilAcessoPermissao = new PerfilAcessoPermissao( 1L, new PerfilAcesso(), new Permissao());

		Assert.assertNotNull( perfilAcessoPermissao );
		Assert.assertNotNull( perfilAcessoPermissao.getId() );
		Assert.assertNotNull( perfilAcessoPermissao.getPerfilAcesso() );
		Assert.assertNotNull( perfilAcessoPermissao.getPermissao() );
	}


	/*-------------------------------------------------------------------
	 *				 		     INTEGRATION TESTS
	 *-------------------------------------------------------------------*/

}
