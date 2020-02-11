/**
 * 
 */
package br.mil.mar.commons.dabm.test.domain.entity.usuario;

import org.junit.Assert;
import org.junit.Test;

import br.mil.mar.dabm.autenticador.domain.entity.usuario.Configuracao;

/**
 * @author emanuelvictor
 *
 */
public class ConfiguracaoTests
{
	
	/*-------------------------------------------------------------------
	 *				 		     UNIT TESTS
	 *-------------------------------------------------------------------*/
	@Test
	public void instanceOfConfiguracao()
	{
		Configuracao configuracao = new Configuracao();
		Assert.assertNotNull( configuracao );
		Assert.assertNull( configuracao.getId() );
		Assert.assertNull( configuracao.getDiasExpiracaoSenha() );
	}

	@Test
	public void instanceOfConfiguracaoAndGetters()
	{
		Configuracao configuracao = new Configuracao( 1L, 90  );

		Assert.assertNotNull( configuracao );
		Assert.assertNotNull( configuracao.getId() );
		Assert.assertNotNull( configuracao.getDiasExpiracaoSenha() );
		
	}

	@Test // Testa se os dias de expiração de senha não são negativos
	public void instanceOfConfiguracaoWithDiasAndSetterExpiracaoSenhaPositive()
	{
		Configuracao configuracao = new Configuracao( 1L, - 20 );

		Assert.assertNotNull( configuracao );
		Assert.assertNotNull( configuracao.getId() );
		Assert.assertNull( configuracao.getDiasExpiracaoSenha() );	
	}
	
	/*-------------------------------------------------------------------
	 *				 		     INTEGRATION TESTS
	 *-------------------------------------------------------------------*/
}
