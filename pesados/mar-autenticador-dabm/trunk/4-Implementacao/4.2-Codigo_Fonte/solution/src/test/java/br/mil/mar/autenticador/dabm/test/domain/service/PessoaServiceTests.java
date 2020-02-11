/**
 * 
 */
package br.mil.mar.autenticador.dabm.test.domain.service;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import br.mil.mar.dabm.autenticador.infrastructure.dao.dpmm.rh.DAOPessoa;
import br.mil.mar.dabm.autenticador.infrastructure.dao.dpmm.rh.impl.DAOPessoaImpl;
import br.mil.mar.dpmm.rh.domain.entity.Pessoa;

/**
 * @author Boz
 *
 */
public class PessoaServiceTests
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/

	private DAOPessoa daoPessoa = new DAOPessoaImpl();


	/*-------------------------------------------------------------------
	 *				 		      TESTS
	 *-------------------------------------------------------------------*/


	@Test
	public void findPessoaByIdMustPass()
	{
		final Pessoa pessoa= this.daoPessoa.findById( 1002L );
		Assert.assertNotNull( pessoa );
		Assert.assertNotNull( pessoa.getOrganizacaoMilitar() );
		Assert.assertNotNull( pessoa.getNomeCompleto() );
	}
	
	@Test
	public void findPessoaByNIPMustPass()
	{
		final Pessoa pessoa = this.daoPessoa.findByNIP( "test" );
		
		Assert.assertNotNull( pessoa );
		Assert.assertNull( pessoa.getOrganizacaoMilitar() );
		Assert.assertNotNull( pessoa.getNomeCompleto() );
	}
	
	@Test
	public void listPessoaByFiltersMustReturnAll()
	{
		final Set<Pessoa> pessoas = this.daoPessoa.listPessoaByFilters( "ASDF" );

		Assert.assertNotNull( pessoas );
	}
	
	/**
	 * 
	 */
	@Test
	public void listPessoaByFilters()
	{
		DAOPessoa daoPessoa = new DAOPessoaImpl();

		Set<Pessoa> pessoas = daoPessoa.listPessoaByFilters( "Primeiro Sargento,TestSynchronize@mailinator.com" );

		Assert.assertNotNull( pessoas );
		Assert.assertEquals( pessoas.size(), 2 );
	}
}
