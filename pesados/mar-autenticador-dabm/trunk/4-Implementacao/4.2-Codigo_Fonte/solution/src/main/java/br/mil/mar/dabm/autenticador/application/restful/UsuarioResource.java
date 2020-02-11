package br.mil.mar.dabm.autenticador.application.restful;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.mil.mar.dabm.autenticador.domain.service.UsuarioService;
import br.mil.mar.dabm.common.application.security.ContextHolder;
import br.mil.mar.dabm.common.domain.entity.usuario.Usuario;

/**
 * 
 * @author rodrigo
 */
@RestController
@RequestMapping("/api")
public class UsuarioResource
{
	/*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	UsuarioService usuarioService;

	/*-------------------------------------------------------------------
	 * 		 					CONTROLLERS
	 *-------------------------------------------------------------------*/

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/principal")
	public Usuario principal()
	{
		return this.usuarioService.findUsuarioByLogin( ContextHolder.getAuthenticatedUser().getLogin() ).prepareSerializable();
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/usuarios/{id}/foto", method = RequestMethod.GET)
	public ByteArrayResource findFotoUsuario( @PathVariable Long id )
	{
		Usuario usuario = this.usuarioService.findUsuarioById( id ).prepareSerializable();
		try
		{
			return new ByteArrayResource( IOUtils.toByteArray( this.usuarioService.findFotoUsuarioById( usuario.getId() ).getInputStream() ) );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return null;
//			throw new RuntimeException( "Não foi possível realizar a requisição da foto do usuário" );
		}
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET)
	public Usuario findUsuarioById( @PathVariable Long id )
	{
		return this.usuarioService.findUsuarioById( id );
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/usuarios", method = RequestMethod.GET)
	public List<Usuario> listUsuariosByFilters( HttpServletRequest request, @RequestParam(required = false ) String filters, @RequestParam(required = false) String perfil, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "100") int size)
	{
		return this.usuarioService.listUsuariosByFilters( filters, perfil, request.getServerName(), new PageRequest( page, size ) ).getContent();
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/usuarios/bloqueados", method = RequestMethod.GET)
	public List<Usuario> listUsersByFiltersAndBloqueados( HttpServletRequest request, @RequestParam(required = false ) String filters, @RequestParam(required = false) String perfil, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "100") int size)
	{
		return this.usuarioService.listUsuariosByFiltersAndBloqueados( filters, perfil, request.getServerName(), new PageRequest( page, size ) ).getContent();
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/usuarios/excluidos", method = RequestMethod.GET)
	public List<Usuario> listUsersByFiltersAndExcluidos( HttpServletRequest request, @RequestParam(required = false ) String filters, @RequestParam(required = false) String perfil, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "100") int size)
	{
		return this.usuarioService.listUsuariosByFiltersAndExcluidos( filters, perfil, request.getServerName(), new PageRequest( page, size ) ).getContent();
	}

}
