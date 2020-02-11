/**
 * 
 */
package br.mil.mar.dabm.common.domain.entity.usuario;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import br.mil.mar.dabm.common.domain.entity.aplicativo.Aplicativo;
import br.mil.mar.dabm.common.domain.entity.aplicativo.PerfilAcesso;
import br.mil.mar.dabm.common.domain.entity.aplicativo.PerfilAcessoPermissao;
import br.mil.mar.dabm.common.domain.entity.aplicativo.Permissao;
import br.mil.mar.dabm.common.domain.entity.usuario.PerfilUsuarioAplicativo;
import br.mil.mar.dabm.common.domain.entity.usuario.Usuario;
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
	/**
	 * 
	 */
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

	/**
	 * 
	 */
	@Test
	public void instanceOfUsuarioAndSettersAndGetters()
	{
		Calendar dataBloqueio = Calendar.getInstance();
		Calendar dataDesbloqueio = Calendar.getInstance();

		dataBloqueio.set( Calendar.DAY_OF_YEAR, 02 );

		dataDesbloqueio.set( Calendar.DAY_OF_YEAR, 03 );

		Usuario usuario = new Usuario( 2L, null, "Nome completo", "login", "email@email", Calendar.getInstance() /* dataAlteracaoSenha */, dataBloqueio, dataDesbloqueio, Calendar.getInstance() /* dataExclusao */, Calendar.getInstance() /* dataExpiracaoSenha */, Calendar.getInstance() /* dataUltimoAcesso */, true );
		usuario.setSenha( "123456" );

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
		Assert.assertNull( usuario.getPessoa() );
		Assert.assertNotNull( usuario.getNomeCompleto() );
		Assert.assertNotNull( usuario.getAlterarSenha() );

		Assert.assertFalse( usuario.isEnabled() );
		Assert.assertFalse( usuario.isAccountNonExpired() );
		Assert.assertTrue( usuario.isAccountNonLocked() );

	}

	/**
	 * 
	 */
	@Test
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

		Usuario usuario = new Usuario( 2L, new Pessoa(), "Nome completo", "login", "email@email", null /* dataAlteracaoSenha */, dataBloqueio /* dataBloqueio */, dataDesbloqueio /* dataDesbloqueio */, null /* dataExclusao */, dataExpiracaoSenha /* dataExpiracaoSenha */, null /* dataUltimoAcesso */, null );
		Assert.assertNotNull( usuario );

		// A conta esta expirada
		Assert.assertTrue( usuario.isAccountNonExpired() );
		// A conta não esta expirada
		dataExpiracaoSenha.set( Calendar.DAY_OF_YEAR, 300 );
		usuario.setDataExpiracaoSenha( dataExpiracaoSenha );
		Assert.assertTrue( usuario.isAccountNonExpired() );

		// A conta esta desabilitada por estar expiradas
		dataExpiracaoSenha.set( Calendar.DAY_OF_YEAR, 03 );
		usuario.setDataExpiracaoSenha( dataExpiracaoSenha );
		Assert.assertTrue( usuario.isEnabled() );

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
		Assert.assertTrue( usuario.isEnabled() );

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
		Assert.assertTrue( usuario.isEnabled() );
		usuario.desbloquear();
		Assert.assertTrue( usuario.isEnabled() );
	}

	/*-------------------------------------------------------------------
	 *				 		     INTEGRATION TESTS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Test
	// Testa a sincronia do nome entre pessoa e usuário
	// Quando o nome é alterado em pessoa deve ser alterado em usuário
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
		Usuario usuario = new Usuario( 2L, null, nomeUsuario, "login", "email@email", Calendar.getInstance() /* dataAlteracaoSenha */, dataBloqueio, dataDesbloqueio, Calendar.getInstance() /* dataExclusao */, Calendar.getInstance() /* dataExpiracaoSenha */, Calendar.getInstance() /* dataUltimoAcesso */, null );
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

	}

	/**
	 * 
	 */
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
		Usuario usuario = new Usuario( 2L, null, "nome do Usuário", "login", "email@email", Calendar.getInstance() /* dataAlteracaoSenha */, dataBloqueio, dataDesbloqueio, Calendar.getInstance() /* dataExclusao */, Calendar.getInstance() /* dataExpiracaoSenha */, Calendar.getInstance() /* dataUltimoAcesso */, null );
		Assert.assertNotNull( usuario );
		Assert.assertNull( usuario.getOrganizacaoMilitar() );

		Pessoa pessoa = new Pessoa();

		usuario.setPessoa( pessoa );
		Assert.assertNull( usuario.getOrganizacaoMilitar() );

		pessoa.setOrganizacaoMilitar( new OrganizacaoMilitar() );

		usuario.setPessoa( pessoa );
		Assert.assertNotNull( usuario.getOrganizacaoMilitar() );

	}

	/**
	 * 
	 */
	@Test
	public void instanceOfUsuarioWithTwoAplicativosMustPass()
	{
//		Criando GOS
		Aplicativo gos = new Aplicativo();
		gos.setIdentificador( "GOS" );

		Permissao ordemServicoService = new Permissao();
		ordemServicoService.setAplicativo( gos );
		ordemServicoService.setIdentificador( "OrdemServicoService" );

		Permissao listOrdemServicoByFilters = new Permissao();
		listOrdemServicoByFilters.setAplicativo( gos );
		listOrdemServicoByFilters.setIdentificador( "listOrdemServicoByFilters" );
		listOrdemServicoByFilters.setPermissaoSuperior( ordemServicoService );

		Permissao insertOrdemServico = new Permissao();
		insertOrdemServico.setAplicativo( gos );
		insertOrdemServico.setIdentificador( "insertOrdemServico" );
		insertOrdemServico.setPermissaoSuperior( listOrdemServicoByFilters );

		Permissao updateOrdemServico = new Permissao();
		updateOrdemServico.setAplicativo( gos );
		updateOrdemServico.setIdentificador( "updateOrdemServico" );
		updateOrdemServico.setPermissaoSuperior( insertOrdemServico );

		Permissao permissaoEspecial = new Permissao();
		permissaoEspecial.setAplicativo( gos );
		permissaoEspecial.setIdentificador( "permissaoEspecial" );
		permissaoEspecial.setPermissaoSuperior( ordemServicoService );

		PerfilAcessoPermissao perfilAcessoPermissaoOrdemServicoService = new PerfilAcessoPermissao();
		perfilAcessoPermissaoOrdemServicoService.setPermissao( ordemServicoService );

		PerfilAcessoPermissao perfilAcessoPermissaoListOrdemServicoByFilters = new PerfilAcessoPermissao();
		perfilAcessoPermissaoListOrdemServicoByFilters.setPermissao( listOrdemServicoByFilters );

		PerfilAcessoPermissao perfilAcessoPermissaoInsertOrdemServico = new PerfilAcessoPermissao();
		perfilAcessoPermissaoInsertOrdemServico.setPermissao( insertOrdemServico );

		PerfilAcessoPermissao perfilAcessoPermissaoUpdateOrdemServico = new PerfilAcessoPermissao();
		perfilAcessoPermissaoUpdateOrdemServico.setPermissao( updateOrdemServico );

		PerfilAcessoPermissao perfilAcessoPermissaoEspecial = new PerfilAcessoPermissao();
		perfilAcessoPermissaoEspecial.setPermissao( permissaoEspecial );

		PerfilAcesso perfilAcessoTotalGOS = new PerfilAcesso();
		perfilAcessoTotalGOS.setPerfilAcessoPermissoes( new HashSet<PerfilAcessoPermissao>( Arrays.asList( perfilAcessoPermissaoOrdemServicoService, perfilAcessoPermissaoListOrdemServicoByFilters, perfilAcessoPermissaoInsertOrdemServico, perfilAcessoPermissaoUpdateOrdemServico, perfilAcessoPermissaoEspecial ) ) );

		PerfilUsuarioAplicativo perfilAcessoTotalUsuarioAplicativo = new PerfilUsuarioAplicativo();
		perfilAcessoTotalUsuarioAplicativo.setPerfilAcesso( perfilAcessoTotalGOS );

		PerfilAcesso perfilAcessoRestritoGOS = new PerfilAcesso();
		perfilAcessoRestritoGOS.setPerfilAcessoPermissoes( new HashSet<PerfilAcessoPermissao>( Arrays.asList( perfilAcessoPermissaoEspecial ) ) );

		PerfilUsuarioAplicativo perfilAcessoRestritoUsuarioAplicativo = new PerfilUsuarioAplicativo();
		perfilAcessoRestritoUsuarioAplicativo.setPerfilAcesso( perfilAcessoRestritoGOS );

		// Criando autenticador
		Aplicativo autenticador = new Aplicativo();
		autenticador.setIdentificador( "Autenticador" );

		Permissao usuarioService = new Permissao();
		usuarioService.setAplicativo( autenticador );
		usuarioService.setIdentificador( "UsuarioService" );

		Permissao insertUsuario = new Permissao();
		insertUsuario.setAplicativo( autenticador );
		insertUsuario.setIdentificador( "insertUsuario" );
		insertUsuario.setPermissaoSuperior( usuarioService );

		Permissao updateUsuario = new Permissao();
		updateUsuario.setAplicativo( autenticador );
		updateUsuario.setIdentificador( "updateUsuario" );
		updateUsuario.setPermissaoSuperior( insertUsuario );

		Permissao resetPassword = new Permissao();
		resetPassword.setAplicativo( autenticador );
		resetPassword.setIdentificador( "resetPassword" );
		resetPassword.setPermissaoSuperior( updateUsuario );

		Permissao changePassword = new Permissao();
		changePassword.setAplicativo( autenticador );
		changePassword.setIdentificador( "changePassword" );
		changePassword.setPermissaoSuperior( usuarioService );

		PerfilAcessoPermissao perfilAcessoPermissaoUsuarioService = new PerfilAcessoPermissao();
		perfilAcessoPermissaoUsuarioService.setPermissao( usuarioService );

		PerfilAcessoPermissao perfilAcessoPermissaoInsertUsuario = new PerfilAcessoPermissao();
		perfilAcessoPermissaoInsertUsuario.setPermissao( insertUsuario );

		PerfilAcessoPermissao perfilAcessoPermissaoUpdateUsuario = new PerfilAcessoPermissao();
		perfilAcessoPermissaoUpdateUsuario.setPermissao( updateUsuario );

		PerfilAcessoPermissao perfilAcessoPermissaoResetPassword = new PerfilAcessoPermissao();
		perfilAcessoPermissaoResetPassword.setPermissao( resetPassword );

		PerfilAcessoPermissao perfilAcessoPermissaoChangePassword = new PerfilAcessoPermissao();
		perfilAcessoPermissaoChangePassword.setPermissao( changePassword );

		PerfilAcesso perfilAcessoTotalAutenticador = new PerfilAcesso();
		perfilAcessoTotalAutenticador.setPerfilAcessoPermissoes( new HashSet<PerfilAcessoPermissao>( Arrays.asList( perfilAcessoPermissaoInsertUsuario, perfilAcessoPermissaoUpdateUsuario, perfilAcessoPermissaoResetPassword ) ) );

		PerfilUsuarioAplicativo perfilAcessoTotalAutenticadorUsuarioAplicativo = new PerfilUsuarioAplicativo();
		perfilAcessoTotalAutenticadorUsuarioAplicativo.setPerfilAcesso( perfilAcessoTotalAutenticador );

		PerfilAcesso perfilAcessoRestritoAutenticador = new PerfilAcesso();
		perfilAcessoRestritoAutenticador.setPerfilAcessoPermissoes( new HashSet<PerfilAcessoPermissao>( Arrays.asList( perfilAcessoPermissaoChangePassword ) ) );

		PerfilUsuarioAplicativo perfilAcessoRestritoAutenticadorUsuarioAplicativo = new PerfilUsuarioAplicativo();
		perfilAcessoRestritoAutenticadorUsuarioAplicativo.setPerfilAcesso( perfilAcessoRestritoAutenticador );

		// Criando um usuário com acesso total para o GOS
		Usuario usuarioPerfilAcessoTotal = new Usuario();
		usuarioPerfilAcessoTotal.setPerfisUsuarioAplicativo( new HashSet<PerfilUsuarioAplicativo>( Arrays.asList( perfilAcessoTotalAutenticadorUsuarioAplicativo, perfilAcessoTotalUsuarioAplicativo ) ) );

		// Criando um usuário com acesso total para o GOS
		Usuario usuarioPerfilAcessoRestrito = new Usuario();
		usuarioPerfilAcessoRestrito.setPerfisUsuarioAplicativo( new HashSet<PerfilUsuarioAplicativo>( Arrays.asList( perfilAcessoRestritoUsuarioAplicativo, perfilAcessoRestritoAutenticadorUsuarioAplicativo ) ) );

		Assert.assertNotNull( usuarioPerfilAcessoRestrito.getAuthorities() );
		Assert.assertNotNull( usuarioPerfilAcessoTotal.getAuthorities() );

		Assert.assertEquals( 1, usuarioPerfilAcessoRestrito.getAuthorities().size() );
		Assert.assertEquals( 1, usuarioPerfilAcessoTotal.getAuthorities().size() );

		/*
		 * Assert.assertEquals( "[Autenticador.UsuarioService.insertUsuario.updateUsuario.resetPassword, "
		 * + "GOS.OrdemServicoService.permissaoEspecial, GOS.OrdemServicoService.listOrdemServicoByFilters.insertOrdemServico, "
		 * + "Autenticador.UsuarioService.insertUsuario.updateUsuario, GOS.OrdemServicoService.listOrdemServicoByFilters,"
		 * + " Autenticador.UsuarioService.insertUsuario, GOS.OrdemServicoService,"
		 * + " GOS.OrdemServicoService.listOrdemServicoByFilters.insertOrdemServico.updateOrdemServico]",
		 * usuarioPerfilAcessoTotal.getAuthorities().toString() );
		 * Assert.assertEquals( "[GOS.OrdemServicoService.permissaoEspecial, Autenticador.UsuarioService.changePassword]", usuarioPerfilAcessoRestrito.getAuthorities().toString() );
		 */
	}

	/**
	 * 
	 */
	@Test
	public void creatingAleatoryPassword()
	{
		String bigHash = UUID.randomUUID().toString();

		bigHash = "!@#$" + bigHash + "!#$&*%*";

		int length = bigHash.length();

		Random random = new Random();

		String randomPassword = new String();
		for ( int i = 0; i < 8; i++ )
		{
			int index = random.nextInt( length );
			String subString = bigHash.substring( index, index + 1 );
			if ( subString.equals( "-" ) )
			{
				i--;
				continue;
			}
			else if ( Character.isLetter( subString.charAt( 0 ) ) && new Random().nextBoolean() )
			{
				randomPassword += bigHash.substring( index, index + 1 ).toUpperCase();
			}
			else
			{
				randomPassword += bigHash.substring( index, index + 1 );
			}
		}

		Assert.assertNotNull( randomPassword );
		Assert.assertEquals( 8, randomPassword.length() );
	}

	/**
	 * 
	 */
	@Test
	public void testNotifyBlockingUsuario()
	{

		Calendar dataDeExpiracao = Calendar.getInstance();
		dataDeExpiracao.set( Calendar.DAY_OF_YEAR, dataDeExpiracao.get( Calendar.DAY_OF_YEAR ) - 7 );

		Calendar hoje = Calendar.getInstance();
		hoje.set( Calendar.DAY_OF_YEAR, hoje.get( Calendar.DAY_OF_YEAR ) - 10 );

		// System.out.println( dataDeExpiracao.get( Calendar.DAY_OF_YEAR ) - hoje.get( Calendar.DAY_OF_YEAR ) );
//		Integer diasRestantes = hoje.get( Calendar.DAY_OF_YEAR - 15) - usuario.getDataExpiracaoSenha().get( Calendar.DAY_OF_YEAR - 15);

	}

}
