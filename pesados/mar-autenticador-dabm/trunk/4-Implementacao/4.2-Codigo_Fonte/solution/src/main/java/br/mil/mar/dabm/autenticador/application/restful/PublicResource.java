/**
 * 
 */
package br.mil.mar.dabm.autenticador.application.restful;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.mil.mar.dabm.autenticador.domain.service.PublicService;
import br.mil.mar.dabm.common.domain.entity.usuario.Usuario;

/**
 * @author emanuelvictor
 *
 */
@Controller
@RequestMapping("/api")
public class PublicResource
{
	/**
	 * 
	 */
	@Autowired
	PublicService publicService;

	/**
	 * Serviço público para recuperação de senha
	 * 
	 * @param hashMap
	 * @return
	 */
	@RequestMapping(value = "/usuarios/redefinirSenha", method = RequestMethod.POST)
	public @ResponseBody Usuario redefineSenhaUsuario( @RequestBody
	final HashMap<String, String> hashMap )
	{
		return this.publicService.redefineSenhaUsuario( hashMap.get( "login" ), hashMap.get( "novaSenha" ), hashMap.get( "confirmacao" ) );
	}

	/**
	 * Serviço público para recuperação de senha
	 * 
	 * @param hashMap
	 * @return
	 */
	@RequestMapping(value = "/usuarios/recuperarSenha", method = RequestMethod.POST)
	public @ResponseBody Usuario recuperarSenhaUsuario( @RequestParam(value = "login" ) String login)
	{
		return this.publicService.recoverySenhaUsuario( login );
	}

}
