package br.mil.mar.dabm.autenticador.application.security;

import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.mil.mar.dabm.autenticador.domain.entity.usuario.Usuario;

/**
 *
 * @author rodrigo@eits.com.br
 * @since 24/07/2013
 * @version 1.0
 * @category
 */
public class ContextHolder
{
	/*-------------------------------------------------------------------
	 * 		 						BEHAVIORS
	 *-------------------------------------------------------------------*/
	/**
	 *
	 * @return
	 */
	public static Usuario getAuthenticatedUser()
	{
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if ( authentication != null && authentication.getPrincipal() instanceof Usuario )
		{
			return ( Usuario ) authentication.getPrincipal();
		}

		throw new InsufficientAuthenticationException( "There is no user authenticated." );
	}
}
