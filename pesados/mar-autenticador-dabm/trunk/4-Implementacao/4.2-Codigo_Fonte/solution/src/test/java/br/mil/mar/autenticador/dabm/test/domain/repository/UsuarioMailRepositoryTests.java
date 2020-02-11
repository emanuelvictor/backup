package br.mil.mar.autenticador.dabm.test.domain.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import br.mil.mar.autenticador.dabm.test.domain.AbstractIntegrationTests;
import br.mil.mar.dabm.autenticador.domain.repository.IUsuarioMailRepository;
import br.mil.mar.dabm.autenticador.domain.service.PublicService;
import br.mil.mar.dabm.common.domain.entity.usuario.Usuario;

/**
 * 
 * @author rodrigo@eits.com.br
 * @since 09/05/2013
 * @version 1.0
 */
public class UsuarioMailRepositoryTests extends AbstractIntegrationTests
{
	/*-------------------------------------------------------------------
	 *                           ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private PublicService publicService;
	/**
	 * 
	 */
	@Autowired
	private IUsuarioMailRepository usuarioMailRepository;

	/*-------------------------------------------------------------------
	 *                           TESTS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Test
	public void sendRedefineSenha()
	{
		final Usuario user = new Usuario();
//		return this.pessoa.getOrganizacaoMilitar().getAdministrador().getEmail(); TODO refazer testes
		user.setNomeCompleto( "Suporte da eits" );

		this.usuarioMailRepository.sendRedefinirSenha( user, "novaSenha" );
	}

	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value =
	{ "/dataset/usuario/UsuarioDataSet.xml" })
	public void sendTokenRecoverySenha()
	{

		this.publicService.recoverySenhaUsuario( "user01");
//		this.usuarioMailRepository.sendRecuperarSenha( user );
	}


}
