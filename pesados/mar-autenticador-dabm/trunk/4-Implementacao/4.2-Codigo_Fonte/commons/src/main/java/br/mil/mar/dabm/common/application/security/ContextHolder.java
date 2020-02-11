/**
 * 
 */
package br.mil.mar.dabm.common.application.security;

import java.util.Collection;

import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import br.mil.mar.dabm.common.domain.entity.usuario.Usuario;

/**
 * @author emanuelvictor
 *
 */
public class ContextHolder
{
	/*-------------------------------------------------------------------
	 * 		 						BEHAVIORS
	 *-------------------------------------------------------------------*/
	/**
	 * O principal também pode ser um aplicativo alterar isso
	 * 
	 * @return
	 */
	public static Usuario getAuthenticatedUser()
	{

		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if ( authentication != null )
		{
			if ( authentication.getPrincipal() instanceof Usuario )
			{
				return ( Usuario ) authentication.getPrincipal();
			}

			if ( authentication.getPrincipal() instanceof String )
			{
				return new Usuario( null, null, ( String ) authentication.getPrincipal(), null );
			}
		}

		throw new InsufficientAuthenticationException( "There is no user authenticated." );
	}

	/**
	 * 
	 * @param usuario
	 * @param authorities
	 */
	public static void authenticate( Usuario usuario, Collection<? extends GrantedAuthority> authorities )
	{
		SecurityContextHolder.createEmptyContext();
		SecurityContextHolder.getContext().setAuthentication( new UsernamePasswordAuthenticationToken( usuario, authorities ) );
	}
}
