package br.mil.mar.autenticador.dabm.test.domain.repository.usuario;

import java.net.MalformedURLException;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.mil.mar.commons.dabm.test.domain.AbstractIntegrationTests;
import br.mil.mar.dabm.autenticador.domain.entity.usuario.Usuario;
import br.mil.mar.dabm.autenticador.domain.repository.usuario.IUsuarioRepository;

/**
 * 
 * @author rodrigo@eits.com.br
 * @since 09/05/2013
 * @version 1.0
 */
public class UsuarioRepositoryTests extends AbstractIntegrationTests
{
	/*-------------------------------------------------------------------
	 *                           ATTRIBUTES
	 *-------------------------------------------------------------------*/

	@Autowired
	private IUsuarioRepository usuarioRepository;

	/*-------------------------------------------------------------------
	 *                           TESTS
	 *-------------------------------------------------------------------*/
	/**
	 * @throws MalformedURLException
	 * 
	 */
	@Test
	public void saveUsuario()
	{
		final Usuario usuario = new Usuario( null, null, "nomeCompleto", "login", "email@email", Calendar.getInstance(), null, null, null, null, null, false );
		usuario.setSenha(  "a31a310a741322d0a88aa40c13dfe4a83ed58024" );
		this.usuarioRepository.save( usuario );

		Assert.assertNotNull( usuario );
	}

}
