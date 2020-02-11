/**
 * 
 */
package br.mil.mar.autenticador.dabm.test.domain.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.mil.mar.commons.dabm.test.domain.AbstractIntegrationTests;
import br.mil.mar.dabm.autenticador.domain.entity.usuario.Configuracao;
import br.mil.mar.dabm.autenticador.domain.service.ConfiguracaoService;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * @author Boz
 *
 */
public class ConfiguracaoServiceTests extends AbstractIntegrationTests
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private ConfiguracaoService configuracaoService;

	/*-------------------------------------------------------------------
	 *				 		      TESTS
	 *-------------------------------------------------------------------*/

	/**
	 * 
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value =
    { "/dataset/usuario/ConfiguracaoDataSet.xml", })
	public void updateDiasExpiracaoSenhaMustPass(){
		Configuracao configuracao = new Configuracao( 1L, 100 );
		Configuracao novosDiasDeExpiracao = this.configuracaoService.updateDiasExpiracaoSenha( configuracao );
		Assert.assertNotNull( novosDiasDeExpiracao );
		Assert.assertNotNull( novosDiasDeExpiracao.getDiasExpiracaoSenha() );
		
	}
	
}
