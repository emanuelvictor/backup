/**
 * 
 */
package br.mil.mar.dabm.common.domain.entity.usuario;

import org.junit.Assert;
import org.junit.Test;

import br.mil.mar.dabm.common.domain.entity.aplicativo.PerfilAcesso;
import br.mil.mar.dabm.common.domain.entity.usuario.PerfilUsuarioAplicativo;
import br.mil.mar.dabm.common.domain.entity.usuario.Usuario;

/**
 * @author emanuelvictor
 *
 */
public class PerfilUsuarioAplicativoTests
{
	/*-------------------------------------------------------------------
	 *				 		     UNIT TESTS
	 *-------------------------------------------------------------------*/
	
	/**
	 * 
	 */
	@Test
	public void instanceOfPerfilUsuarioAplicativo()
	{
		PerfilUsuarioAplicativo perfilUsuarioAplicativo = new PerfilUsuarioAplicativo();
		Assert.assertNotNull( perfilUsuarioAplicativo );
		Assert.assertNull( perfilUsuarioAplicativo.getId() );
		Assert.assertNull( perfilUsuarioAplicativo.getPerfilAcesso() );
	}

	/**
	 * 
	 */
	@Test
	public void instanceOfPerfilUsuarioAplicativoAndGetters()
	{
		PerfilUsuarioAplicativo perfilUsuarioAplicativo = new PerfilUsuarioAplicativo( 1L,  new Usuario() ,new PerfilAcesso() );

		Assert.assertNotNull( perfilUsuarioAplicativo );
		Assert.assertNotNull( perfilUsuarioAplicativo.getId() );
		Assert.assertNotNull( perfilUsuarioAplicativo.getPerfilAcesso() );

	}

	/*-------------------------------------------------------------------
	 *				 		     INTEGRATION TESTS
	 *-------------------------------------------------------------------*/
}
