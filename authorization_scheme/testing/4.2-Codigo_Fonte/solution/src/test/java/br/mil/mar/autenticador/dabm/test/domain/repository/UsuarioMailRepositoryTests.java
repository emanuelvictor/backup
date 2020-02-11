package br.mil.mar.autenticador.dabm.test.domain.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.mil.mar.commons.dabm.test.domain.AbstractIntegrationTests;
import br.mil.mar.dabm.autenticador.domain.entity.usuario.Usuario;
import br.mil.mar.dabm.autenticador.domain.repository.IUsuarioMailRepository;

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
	private IUsuarioMailRepository usuarioMailRepository;

	/*-------------------------------------------------------------------
	 *                           TESTS
	 *-------------------------------------------------------------------*/
	/**
     * 
     */
	@Test
	public void sendRecuperarSenha()
	{
		final Usuario user = new Usuario();
		user.setEmail( "lucas.boz@eits.com.br" );
		user.setNomeCompleto( "Suporte da eits" );

		this.usuarioMailRepository.sendRecuperarSenha( user );
	}
}
