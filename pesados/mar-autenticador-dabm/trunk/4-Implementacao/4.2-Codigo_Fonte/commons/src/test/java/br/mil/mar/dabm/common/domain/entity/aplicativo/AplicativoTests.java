/**
 * 
 */
package br.mil.mar.dabm.common.domain.entity.aplicativo;

import java.net.MalformedURLException;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import br.mil.mar.dabm.common.domain.entity.aplicativo.Aplicativo;

/**
 * @author emanuelvictor
 *
 */
public class AplicativoTests
{

	/*-------------------------------------------------------------------
	 *				 		     UNIT TESTS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Test
	public void instanceOfAplicativo() throws MalformedURLException
	{
		Aplicativo aplicativo = new Aplicativo();
		Assert.assertNotNull( aplicativo );
		Assert.assertNull( aplicativo.getId() );
		Assert.assertNull( aplicativo.getDescricao() );
		Assert.assertNull( aplicativo.getNome() );
		Assert.assertNull( aplicativo.getIdentificador() );
		Assert.assertNull( aplicativo.getVersao() );
		Assert.assertNull( aplicativo.getAtivo() );
		Assert.assertNull( aplicativo.getEndereco() );
		Assert.assertNull( aplicativo.getPerfisDinamicos() );
		Assert.assertNull( aplicativo.getMensagemDesativacao() );
		Assert.assertNull( aplicativo.getRefreshToken() );
	}

	/**
	 * @throws MalformedURLException
	 * 
	 */
	@Test
	public void instanceOfAplicativoAndGetters() throws MalformedURLException
	{
		Aplicativo aplicativo = new Aplicativo( 2L, "Nome do aplicativo", true, true, UUID.randomUUID().toString(), "Versão do aplicativo", "Descrição do aplicativo", null, "http://aplicativo/url/", "Mensagem desativação", "Token" );

		Assert.assertNotNull( aplicativo );
		Assert.assertNotNull( aplicativo.getId() );
		Assert.assertNotNull( aplicativo.getDescricao() );
		Assert.assertNotNull( aplicativo.getNome() );
		Assert.assertNotNull( aplicativo.getIdentificador() );
		Assert.assertNotNull( aplicativo.getVersao() );
		Assert.assertNotNull( aplicativo.getAtivo() );
		Assert.assertNotNull( aplicativo.getEndereco() );
		Assert.assertNull( aplicativo.getVersaoEstavel() );
		Assert.assertNotNull( aplicativo.getPerfisDinamicos() );
		Assert.assertNotNull( aplicativo.getMensagemDesativacao() );
		Assert.assertNotNull( aplicativo.getRefreshToken() );
	}

	/**
	 * 
	 * @throws MalformedURLException
	 */
	@Test
	public void instanceOfAplicativoWithVersaoEstavel() throws MalformedURLException
	{
		Aplicativo versaoEstavel = new Aplicativo();

		Aplicativo aplicativo = new Aplicativo( 2L, "Nome do aplicativo", true, true, UUID.randomUUID().toString(), "Versão do aplicativo", "Descrição do aplicativo", versaoEstavel, "http://aplicativo/url/", "Mensagem desativação", "Token" );

		Assert.assertNotNull( aplicativo );
		Assert.assertNotNull( aplicativo.getId() );
		Assert.assertNotNull( aplicativo.getDescricao() );
		Assert.assertNotNull( aplicativo.getNome() );
		Assert.assertNotNull( aplicativo.getIdentificador() );
		Assert.assertNotNull( aplicativo.getVersao() );
		Assert.assertNotNull( aplicativo.getAtivo() );
		Assert.assertNotNull( aplicativo.getEndereco() );
		Assert.assertNotNull( aplicativo.getVersaoEstavel() );
		Assert.assertNotNull( aplicativo.getPerfisDinamicos() );
		Assert.assertNotNull( aplicativo.getMensagemDesativacao() );
		Assert.assertNotNull( aplicativo.getRefreshToken() );
	}

	/**
	 * 
	 * @throws MalformedURLException
	 */
	@Test
	// Verificando comportamento de get icone
	public void instanceOfAplicativoWithVersaoEstavelAndIcone() throws MalformedURLException
	{
		Aplicativo versaoEstavel = new Aplicativo();

		Aplicativo aplicativo = new Aplicativo( 2L, "Nome do aplicativo", true, true, UUID.randomUUID().toString(), "Versão do aplicativo", "Descrição do aplicativo", versaoEstavel, "http://aplicativo/url/", "Mensagem desativação", "Token" );

		Assert.assertNotNull( aplicativo );
		Assert.assertNotNull( aplicativo.getId() );
		Assert.assertNotNull( aplicativo.getDescricao() );
		Assert.assertNotNull( aplicativo.getNome() );
		Assert.assertNotNull( aplicativo.getIdentificador() );
		Assert.assertNotNull( aplicativo.getVersao() );
		Assert.assertNotNull( aplicativo.getAtivo() );
		Assert.assertNotNull( aplicativo.getEndereco() );
		Assert.assertNotNull( aplicativo.getVersaoEstavel() );
		Assert.assertNotNull( aplicativo.getPerfisDinamicos() );
		Assert.assertNotNull( aplicativo.getMensagemDesativacao() );
		Assert.assertNotNull( aplicativo.getRefreshToken() );
	}

	/**
	 * 
	 * @throws MalformedURLException
	 */
	@Test(expected = java.lang.AssertionError.class)
	// Testa construtor com a versão estável sendo ele mesma
	public void instanceOfAplicativoWithOtherVersaoEstavelMustFail() throws MalformedURLException
	{
		Aplicativo aplicativo = new Aplicativo( 2L, "Nome do aplicativo", true, true, UUID.randomUUID().toString(), "Versão do aplicativo", "Descrição do aplicativo", null, "http://aplicativo/url/", "Mensagem desativação", "Token" );

		aplicativo.setVersaoEstavel( aplicativo );

		Assert.assertNotNull( aplicativo );
		Assert.assertNotNull( aplicativo.getId() );
		Assert.assertNotNull( aplicativo.getDescricao() );
		Assert.assertNotNull( aplicativo.getNome() );
		Assert.assertNotNull( aplicativo.getIdentificador() );
		Assert.assertNotNull( aplicativo.getVersao() );
		Assert.assertNotNull( aplicativo.getAtivo() );
		Assert.assertNotNull( aplicativo.getEndereco() );
		Assert.assertNull( aplicativo.getVersaoEstavel() );
		Assert.assertNotNull( aplicativo.getPerfisDinamicos() );
		Assert.assertNotNull( aplicativo.getMensagemDesativacao() );
		Assert.assertNotNull( aplicativo.getRefreshToken() );
	}
}
