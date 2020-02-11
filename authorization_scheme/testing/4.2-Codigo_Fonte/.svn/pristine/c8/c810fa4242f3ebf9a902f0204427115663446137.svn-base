/**
 * 
 */
package br.mil.mar.commons.dabm.test.domain.entity.usuario;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import br.mil.mar.dabm.autenticador.domain.entity.usuario.Usuario;
import br.mil.mar.dabm.singra.domain.entity.OrganizacaoMilitar;
import br.mil.mar.dpmm.rh.domain.entity.Pessoa;

/**
 * @author emanuelvictor
 *
 */
public class UsuarioTests
{
	/*-------------------------------------------------------------------
	 *				 		     UNIT TESTS
	 *-------------------------------------------------------------------*/
	@Test
	public void instanceOfUsuario()
	{

		Usuario usuario = new Usuario();

		Assert.assertNotNull( usuario );
		Assert.assertNull( usuario.getId() );
		Assert.assertNull( usuario.getEmail() );
		Assert.assertNull( usuario.getLogin() );
		Assert.assertNull( usuario.getNomeCompleto() );
		Assert.assertNull( usuario.getPassword() );
		Assert.assertNull( usuario.getDataAlteracaoSenha() );
		Assert.assertNull( usuario.getDataBloqueio() );
		Assert.assertNull( usuario.getDataDesbloqueio() );
		Assert.assertNull( usuario.getDataExclusao() );
		Assert.assertNull( usuario.getPessoa() );
		Assert.assertNull( usuario.getOrganizacaoMilitar() );
		Assert.assertNull( usuario.getDataExpiracaoSenha() );
		Assert.assertNull( usuario.getDataExpiracaoSenha() );
		Assert.assertTrue( usuario.isEnabled() );
		Assert.assertTrue( usuario.isAccountNonExpired() );
		Assert.assertTrue( usuario.isAccountNonLocked() );

	}

	@Test
	public void instanceOfUsuarioAndSettersAndGetters()
	{
		Calendar dataBloqueio = Calendar.getInstance();
		Calendar dataDesbloqueio = Calendar.getInstance();

		dataBloqueio.set( Calendar.DAY_OF_YEAR, 02 );

		dataDesbloqueio.set( Calendar.DAY_OF_YEAR, 03 );

		Usuario usuario = new Usuario( 2L, new Pessoa(), "Nome completo", "login", "email", Calendar.getInstance() /* dataAlteracaoSenha */, dataBloqueio, dataDesbloqueio, Calendar.getInstance() /* dataExclusao */, Calendar.getInstance() /* dataExpiracaoSenha */, Calendar.getInstance() /* dataUltimoAcesso */, true );
		usuario.setSenha( "senha" );

		Assert.assertNotNull( usuario );
		Assert.assertNotNull( usuario.getId() );
		Assert.assertNotNull( usuario.getEmail() );
		Assert.assertNotNull( usuario.getLogin() );
		Assert.assertNotNull( usuario.getPassword() );
		Assert.assertNotNull( usuario.getDataAlteracaoSenha() );
		Assert.assertNotNull( usuario.getDataBloqueio() );
		Assert.assertNotNull( usuario.getDataDesbloqueio() );
		Assert.assertNotNull( usuario.getDataExclusao() );
		Assert.assertNull( usuario.getOrganizacaoMilitar() );
		Assert.assertNotNull( usuario.getDataExpiracaoSenha() );
		Assert.assertNotNull( usuario.getDataExpiracaoSenha() );
		Assert.assertNotNull( usuario.getPessoa() );
		Assert.assertNotNull( usuario.getNomeCompleto() );
		Assert.assertNotNull( usuario.getAlterarSenha() );

		Assert.assertFalse( usuario.isEnabled() );
		Assert.assertFalse( usuario.isAccountNonExpired() );
		Assert.assertTrue( usuario.isAccountNonLocked() );

	}

	@Test
	/**
	 * 
	 */
	public void isAccountNonExpiredMustPass()
	{
		Calendar dataBloqueio = Calendar.getInstance();
		Calendar dataDesbloqueio = Calendar.getInstance();
		Usuario usuario = new Usuario();

		dataBloqueio.set( Calendar.DAY_OF_YEAR, Calendar.DATE - 2 );
		dataDesbloqueio.set( Calendar.DAY_OF_YEAR, Calendar.DATE - 1 );
		usuario.setDataBloqueio( dataBloqueio );
		usuario.setDataDesbloqueio( dataDesbloqueio );
		// Duas datas antes de hoje DESBLOQUEADO
		Assert.assertTrue( usuario.isAccountNonExpired() );

		dataBloqueio.set( Calendar.DAY_OF_YEAR, Calendar.DATE + 2 );
		dataDesbloqueio.set( Calendar.DAY_OF_YEAR, Calendar.DATE + 3 );
		usuario.setDataBloqueio( dataBloqueio );
		usuario.setDataDesbloqueio( dataDesbloqueio );
		// Duas datas depois de hoje DESBLOQUEADO
		Assert.assertTrue( usuario.isAccountNonExpired() );

		dataBloqueio.set( Calendar.DAY_OF_YEAR, Calendar.DATE - 2 );
		dataDesbloqueio.set( Calendar.DAY_OF_YEAR, Calendar.DATE + 2 );
		usuario.setDataBloqueio( dataBloqueio );
		usuario.setDataDesbloqueio( dataDesbloqueio );
		// Uma data antes e uma depois BLOQUEADO
		/**
		 * Assert.assertFalse( usuario.isAccountNonExpired());
		 */
		dataBloqueio.set( Calendar.DAY_OF_YEAR, 1 );
		dataDesbloqueio.set( Calendar.DAY_OF_YEAR, 360 );
		usuario.setDataBloqueio( dataBloqueio );
		usuario.setDataDesbloqueio( dataDesbloqueio );
		// Duas datas depois de hoje DESBLOQUEADO
		Assert.assertTrue( usuario.isAccountNonExpired() );
	}

	/**
	 * 
	 */
	@Test
	public void instanceOfUsuarioAndBehaviorDatesAndDesbloquear()
	{
		Calendar dataBloqueio = Calendar.getInstance();
		Calendar dataDesbloqueio = Calendar.getInstance();
		Calendar dataExpiracaoSenha = Calendar.getInstance();

		dataExpiracaoSenha.set( Calendar.DAY_OF_YEAR, 3 );

		dataBloqueio.set( Calendar.DAY_OF_YEAR, 350 );

		dataDesbloqueio.set( Calendar.DAY_OF_YEAR, 355 );

		Usuario usuario = new Usuario( 2L, new Pessoa(), "Nome completo", "login", "email", null /* dataAlteracaoSenha */, dataBloqueio /* dataBloqueio */, dataDesbloqueio /* dataDesbloqueio */, null /* dataExclusao */, dataExpiracaoSenha /* dataExpiracaoSenha */, null /* dataUltimoAcesso */, null );
		Assert.assertNotNull( usuario );

		// A conta esta expirada
		Assert.assertFalse( usuario.isAccountNonExpired() );
		// A conta não esta expirada
		dataExpiracaoSenha.set( Calendar.DAY_OF_YEAR, 300 );
		usuario.setDataExpiracaoSenha( dataExpiracaoSenha );
		Assert.assertTrue( usuario.isAccountNonExpired() );

		// A conta esta desabilitada por estar expiradas
		dataExpiracaoSenha.set( Calendar.DAY_OF_YEAR, 03 );
		usuario.setDataExpiracaoSenha( dataExpiracaoSenha );
		Assert.assertFalse( usuario.isEnabled() );

		// A conta não esta expirada mas esta em período de bloqueio
		dataExpiracaoSenha.set( Calendar.DAY_OF_YEAR, 300 );
		usuario.setDataExpiracaoSenha( dataExpiracaoSenha );
		dataBloqueio.set( Calendar.DAY_OF_YEAR, 03 );
		usuario.setDataBloqueio( dataBloqueio );
		// Verifica não esta expirada
		Assert.assertTrue( usuario.isAccountNonExpired() );
		// Verifica não esta bloqueada
		Assert.assertFalse( usuario.isAccountNonLocked() );
		// Esta bloqueada por tempo indeterminado
		usuario.setDataDesbloqueio( null );
		Assert.assertFalse( usuario.isAccountNonLocked() );

		// Esta inativa porque esta bloqueada
		Assert.assertFalse( usuario.isEnabled() );
		// A conta esta desbloqueada mas esta inativa porque esta expirada
		usuario.setDataBloqueio( null );
		dataExpiracaoSenha.set( Calendar.DAY_OF_YEAR, 05 );
		usuario.setDataExpiracaoSenha( dataExpiracaoSenha );
		Assert.assertFalse( usuario.isEnabled() );

		// A conta esta ativa porque a data de hoje é posterior a data de desbloqueio
		usuario.setDataExpiracaoSenha( null );
		dataBloqueio.set( Calendar.DAY_OF_YEAR, 03 );
		usuario.setDataBloqueio( dataBloqueio );
		dataDesbloqueio.set( Calendar.DAY_OF_YEAR, 05 );
		usuario.setDataDesbloqueio( dataDesbloqueio );
		Assert.assertTrue( usuario.isEnabled() );

		// A conta esta expirada e bloqueada, testar comportamento de desbloqueio (método 'desbloquear()')
		dataBloqueio.set( Calendar.DAY_OF_YEAR, 5 );
		dataDesbloqueio.set( Calendar.DAY_OF_YEAR, 285 );
		dataExpiracaoSenha.set( Calendar.DAY_OF_YEAR, 5 );
		usuario.setDataBloqueio( dataBloqueio );
		usuario.setDataDesbloqueio( dataDesbloqueio );
		usuario.setDataExpiracaoSenha( dataExpiracaoSenha );
		Assert.assertFalse( usuario.isEnabled() );
		usuario.desbloquear();
		Assert.assertTrue( usuario.isEnabled() );
	}

	/*-------------------------------------------------------------------
	 *				 		     INTEGRATION TESTS
	 *-------------------------------------------------------------------*/

	@Test
	// Testa a sincronia do nome entre pessoa e usuário
	// Quando o nome é alterado em pessoa deve ser alterado em usuário, quando é alterado em usuário deve ser alterado em pessoa
	public void instanceOfUsuarioAndSettersAndGettersAndSynchronizingPessoa()
	{

		Calendar dataBloqueio = Calendar.getInstance();
		Calendar dataDesbloqueio = Calendar.getInstance();
		Calendar dataExpiracaoSenha = Calendar.getInstance();

		dataExpiracaoSenha.set( Calendar.DAY_OF_YEAR, 3 );

		dataBloqueio.set( Calendar.DAY_OF_YEAR, 350 );

		dataDesbloqueio.set( Calendar.DAY_OF_YEAR, 355 );

		// Variáveis String que armazenarão os nomes de pessoa e usuário (são dois nomes diferentes
		String nomeUsuario = new String( "Nome usuario" );
		String nomePessoa = new String( "Nome pessoa" );

		// Testa se o nome do usuário é igual a váriavel nomeUsuario (Comportamento de usuário sem pessoa)
		Usuario usuario = new Usuario( 2L, null, nomeUsuario, "login", "email", Calendar.getInstance() /* dataAlteracaoSenha */, dataBloqueio, dataDesbloqueio, Calendar.getInstance() /* dataExclusao */, Calendar.getInstance() /* dataExpiracaoSenha */, Calendar.getInstance() /* dataUltimoAcesso */, null );
		Assert.assertNotNull( usuario );
		Assert.assertNotNull( usuario.getId() );
		Assert.assertNotNull( usuario.getNomeCompleto() );
		Assert.assertEquals( nomeUsuario, usuario.getNomeCompleto() );

		// Comportamento quando o usuário recebe uma pessoa com um nome diferente, o nome do usuário deve se tornar o nome da pessoa
		Pessoa pessoa = new Pessoa();
		pessoa.setNomeCompleto( nomePessoa );
		usuario.setPessoa( pessoa );
		Assert.assertNotEquals( nomeUsuario, usuario.getNomeCompleto() );
		Assert.assertEquals( nomePessoa, usuario.getNomeCompleto() );

		// Comportamento inverso, ao atualizar o nome do usuário o nome da pessoa deve ser atualizado
		usuario.setNomeCompleto( nomeUsuario );
		Assert.assertNotEquals( nomePessoa, usuario.getPessoa().getNomeCompleto() );
		Assert.assertEquals( nomeUsuario, usuario.getPessoa().getNomeCompleto() );

	}

	@Test
	public void instanceOfUsuarioAndOrganizacaoMilitar()
	{

		Calendar dataBloqueio = Calendar.getInstance();
		Calendar dataDesbloqueio = Calendar.getInstance();
		Calendar dataExpiracaoSenha = Calendar.getInstance();

		dataExpiracaoSenha.set( Calendar.DAY_OF_YEAR, 3 );

		dataBloqueio.set( Calendar.DAY_OF_YEAR, 350 );

		dataDesbloqueio.set( Calendar.DAY_OF_YEAR, 355 );

		// Testa se o nome do usuário é igual a váriavel nomeUsuario (Comportamento de usuário sem pessoa)
		Usuario usuario = new Usuario( 2L, null, "nome do Usuário", "login", "email", Calendar.getInstance() /* dataAlteracaoSenha */, dataBloqueio, dataDesbloqueio, Calendar.getInstance() /* dataExclusao */, Calendar.getInstance() /* dataExpiracaoSenha */, Calendar.getInstance() /* dataUltimoAcesso */, null );
		Assert.assertNotNull( usuario );
		Assert.assertNull( usuario.getOrganizacaoMilitar() );

		Pessoa pessoa = new Pessoa();

		usuario.setPessoa( pessoa );
		Assert.assertNull( usuario.getOrganizacaoMilitar() );

		pessoa.setOrganizacaoMilitar( new OrganizacaoMilitar() );

		usuario.setPessoa( pessoa );
		Assert.assertNotNull( usuario.getOrganizacaoMilitar() );

	}

}
