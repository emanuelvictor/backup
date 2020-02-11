package br.mil.mar.autenticador.dabm.test.domain.repository.aplicativo;

import java.net.MalformedURLException;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import br.mil.mar.autenticador.dabm.test.domain.AbstractIntegrationTests;
import br.mil.mar.dabm.autenticador.domain.repository.aplicativo.IAplicativoRepository;
import br.mil.mar.dabm.common.domain.entity.aplicativo.Aplicativo;

/**
 * 
 * @author rodrigo@eits.com.br
 * @since 09/05/2013
 * @version 1.0
 */
public class AplicativoRepositoryTests extends AbstractIntegrationTests
{
	/*-------------------------------------------------------------------
	 *                           ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
     * 
     */
	@Autowired
	private IAplicativoRepository aplicativoRepository;

	/*-------------------------------------------------------------------
	 *                           TESTS
	 *-------------------------------------------------------------------*/
	
	
	/**
	 * @throws MalformedURLException
	 * 
	 */
	@Test
	public void saveAplicativoMustPass() throws MalformedURLException
	{
		final Aplicativo aplicativo = new Aplicativo();
		aplicativo.setAtivo( true );
		aplicativo.setRefreshToken( UUID.randomUUID().toString() );
		aplicativo.setVersao( "1.0.0-RELEASE" );
		aplicativo.setPerfisDinamicos( false );
		aplicativo.setEndereco(  "http://localhost:8081/client"  );
		aplicativo.setIdentificador( UUID.randomUUID().toString() );
		aplicativo.setNome( "App Test" );

		this.aplicativoRepository.save( aplicativo );

		this.aplicativoRepository.findOne( aplicativo.getId() );

		Assert.assertNotNull( aplicativo );
	}
	
	/**
	 * @throws MalformedURLException
	 * 
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value =
	{ "/dataset/aplicativo/AplicativoDataSet.xml" })
	public void updateAplicativoMustPass() throws MalformedURLException
	{
		Aplicativo aplicativoSuperior = new Aplicativo();
		aplicativoSuperior.setAtivo( true );
		aplicativoSuperior.setRefreshToken( UUID.randomUUID().toString() );
		aplicativoSuperior.setVersao( "1.0.0-RELEASE" );
		aplicativoSuperior.setPerfisDinamicos( false );
		aplicativoSuperior.setEndereco( "http://localhost:8081/client" );
		aplicativoSuperior.setIdentificador( UUID.randomUUID().toString() );
		aplicativoSuperior.setNome( "App Test" );
		aplicativoSuperior.setVersaoEstavel( new Aplicativo( 3L ) );
		
		aplicativoSuperior = this.aplicativoRepository.save( aplicativoSuperior );

		Aplicativo aplicativo = this.aplicativoRepository.findOne( 1L );
		
		aplicativo.setVersaoEstavel( aplicativoSuperior );

		this.aplicativoRepository.save( aplicativo );
		
		Assert.assertNotNull( aplicativoSuperior );
	}
	
	/**
	 * @throws MalformedURLException
	 * 
	 */
	@Test
	public void saveAplicativoWithGrantTypeMustPass() throws MalformedURLException
	{
		final Aplicativo aplicativo = new Aplicativo();
		aplicativo.setAtivo( true );
		aplicativo.setRefreshToken( "appTestRefreshToken" );
		aplicativo.setVersao( "1.0.0-RELEASE" );
		aplicativo.setPerfisDinamicos( false );
		aplicativo.setEndereco(  "http://localhost:8081/client"  );
		aplicativo.setIdentificador( "appTestAccessToken" );
		aplicativo.setNome( "App Test" );

		this.aplicativoRepository.save( aplicativo );

		this.aplicativoRepository.findOne( aplicativo.getId() );

		Assert.assertNotNull( aplicativo );
	}

}
