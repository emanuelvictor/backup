package br.mil.mar.dabm.autenticador.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.mil.mar.dabm.common.application.security.ContextHolder;
import br.mil.mar.dabm.common.domain.entity.usuario.Usuario;
import br.mil.mar.dabm.common.infrastructure.delegate.UsuarioResourceDelegate;

@Controller
public class NavigationController {
	/*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private UsuarioResourceDelegate usuarioResourceDelegate;

	/*-------------------------------------------------------------------
	 * 		 					CONTROLLERS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public @ResponseBody StringBuffer home() {
		this.authenticate();
		return new StringBuffer("ROLOU");
	}

	@RequestMapping("/test")
	public @ResponseBody StringBuffer test() {
		return new StringBuffer("TESTE");
	}

	/**
	 * 
	 * @param customer
	 * @return
	 */
	private void authenticate() {
		final Usuario usuario = this.usuarioResourceDelegate.findPrincipal();

		if (usuario.getAuthorities() == null || usuario.getAuthorities().equals(null)
				|| usuario.getAuthorities().size() == 0) {
			throw new AccessDeniedException("Usuário não vinculado ao sistema CLIENTE");
		} else {
			ContextHolder.authenticate(usuario, usuario.getAuthorities());
		}
	}

}
