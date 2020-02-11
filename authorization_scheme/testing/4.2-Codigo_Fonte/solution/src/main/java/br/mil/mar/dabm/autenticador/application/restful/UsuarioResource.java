package br.mil.mar.dabm.autenticador.application.restful;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.mil.mar.dabm.autenticador.application.security.ContextHolder;
import br.mil.mar.dabm.autenticador.domain.entity.usuario.Usuario;

/**
 * 
 * @author rodrigo
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioResource
{
	/*-------------------------------------------------------------------
	 * 		 					CONTROLLERS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param principal
	 * @return
	 */
//	@RequestMapping("/principal")
//	public Principal principal( Principal principal )
//	{
//		return principal;
//	}
	
	/**
	 * 
	 * @return
	 */
	
	@RequestMapping("/principal")
	public Usuario principal()
	{
		return ContextHolder.getAuthenticatedUser();
	}
	
//	@RequestMapping("/principal")
//	public String principal()
//	{
//		ContextHolder.getAuthenticatedUser();
//		return "" ; 
//	}
}
