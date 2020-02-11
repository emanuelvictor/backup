package br.mil.mar.dabm.autenticador.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.mil.mar.dabm.autenticador.domain.repository.usuario.ITokenRecuperacaoSenhaRepository;
import br.mil.mar.dabm.autenticador.domain.service.PublicService;
import br.mil.mar.dabm.common.domain.entity.usuario.Usuario;

/**
 * 
 * @author rodrigo
 */
@Controller
public class NavigationController
{
	/**
	 * 
	 */
	@Autowired
	PublicService publicService;

	/**
	 * TODO colocar o repositório dentro do serviço
	 */
	@Autowired
	ITokenRecuperacaoSenhaRepository iTokenRecuperacaoSenhaRepository;

	/*-------------------------------------------------------------------
	 * 		 					CONTROLLERS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView home()
	{
		return new ModelAndView( "modules/autenticador/views/index.jsp" );
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/authentication")
	public ModelAndView authentication()
	{
		return new ModelAndView( "modules/autenticacao/views/index.jsp" );
	}

	/**
	 * 
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/usuarios/recuperarSenha")
	public String recuperarSenhaUsuarioWithToken( @RequestParam(value = "token" ) String token)
	{
		Usuario usuario = null;
		try
		{
			// Poe o usuário vinculado ao token para resetar no primeiro login
			usuario = this.publicService.recoverySenhaUsuarioByToken( token );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			// não encontrou token
			return "redirect:/authentication#/signin/login?erro=" + e.getMessage();
		}

		return "redirect:/authentication#/signin/redefinir-senha?nip=" + usuario.getLogin();

	}
}
