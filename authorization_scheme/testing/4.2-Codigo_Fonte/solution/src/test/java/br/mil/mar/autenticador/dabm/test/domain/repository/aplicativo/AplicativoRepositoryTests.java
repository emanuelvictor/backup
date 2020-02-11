package br.mil.mar.autenticador.dabm.test.domain.repository.aplicativo;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.mil.mar.commons.dabm.test.domain.AbstractIntegrationTests;
import br.mil.mar.dabm.autenticador.domain.entity.aplicativo.Aplicativo;
import br.mil.mar.dabm.autenticador.domain.entity.aplicativo.TipoAcesso;
import br.mil.mar.dabm.autenticador.domain.repository.aplicativo.IAplicativoRepository;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

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
		aplicativo.setCodigo( UUID.randomUUID().toString() );
		aplicativo.setNome( "App Test" );
		aplicativo.getTiposAcessoAplicativo().add( TipoAcesso.PASSWORD );

		this.aplicativoRepository.save( aplicativo );

		this.aplicativoRepository.findOne( aplicativo.getId() );

		Assert.assertNotNull( aplicativo );
		System.out.println(aplicativo.getVersaoEstavel());
		System.out.println("TESTE @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
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
		aplicativoSuperior.setCodigo( UUID.randomUUID().toString() );
		aplicativoSuperior.setNome( "App Test" );
		aplicativoSuperior.getTiposAcessoAplicativo().add( TipoAcesso.PASSWORD );
		aplicativoSuperior.setVersaoEstavel( new Aplicativo( 3L ) );
		
		aplicativoSuperior = this.aplicativoRepository.save( aplicativoSuperior );

		Aplicativo aplicativo = this.aplicativoRepository.findOne( 1L );
		
		aplicativo.setVersaoEstavel( aplicativoSuperior );

		this.aplicativoRepository.save( aplicativo );
		
		Assert.assertNotNull( aplicativoSuperior );
//		
//		final Aplicativo aplicativo = new Aplicativo();
//		aplicativo.setAtivo( true );
//		aplicativo.setRefreshToken( UUID.randomUUID().toString() );
//		aplicativo.setVersao( "1.0.0-SNAPSHOT" );
//		aplicativo.setPerfisDinamicos( false );
//		aplicativo.setEndereco( new URL( "http://localhost:8081/client-snapshot" ) );
//		aplicativo.setCodigo( UUID.randomUUID().toString() );
//		aplicativo.setNome( "App Test snapshot" );
//		aplicativo.getTiposAcessoAplicativo().add( TipoAcesso.PASSWORD );
//		aplicativo.setVersaoEstavel( aplicativoSuperior);
//
//		this.aplicativoRepository.save( aplicativo );
//
//		this.aplicativoRepository.findOne( aplicativo.getId() );
//
//		Assert.assertNotNull( aplicativo );
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
		aplicativo.setCodigo( "appTestAccessToken" );
		aplicativo.setNome( "App Test" );
		aplicativo.setTiposAcessoAplicativo( ( Set<TipoAcesso> ) Arrays.asList( TipoAcesso.PASSWORD, TipoAcesso.AUTHORIZATION_CODE, TipoAcesso.CLIENT_CREDENTIALS, TipoAcesso.IMPLICIT, TipoAcesso.REFRESH_TOKEN  ) );
//		aplicativo.getTiposAcessoAplicativo().add( TipoAcesso.PASSWORD, TipoAcesso.AUTHORIZATION_CODE, TipoAcesso.CLIENT_CREDENTIALS, TipoAcesso.IMPLICIT, TipoAcesso.REFRESH_TOKEN );

		this.aplicativoRepository.save( aplicativo );

		this.aplicativoRepository.findOne( aplicativo.getId() );

		Assert.assertNotNull( aplicativo );
		System.out.println(aplicativo.getVersaoEstavel());
		System.out.println("TESTE @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	}

}
