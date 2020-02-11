/**
 * 
 */
package br.mil.mar.dabm.common.infrastructure.delegate;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageableImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.stereotype.Component;

import br.mil.mar.dabm.common.domain.entity.usuario.Usuario;

/**
 * @author emanuelvictor
 *
 */
@Component
public class UsuarioResourceDelegate extends AbstractResourceDelegate
{
	/*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private OAuth2ClientContext oAuth2ClientContext;
	/**
	 * 
	 */
	@Autowired
	private AuthorizationCodeResourceDetails authorizationCodeResourceDetails;

	/*-------------------------------------------------------------------
	 *				 		     SERVICES
	 *-------------------------------------------------------------------*/

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Usuario findUsuarioById( Long id )
	{
		return this.restTemplate.getForObject( this.baseUrl + "/api/usuarios/{id}", Usuario.class, id );
	}

	/**
	 * 
	 * @return
	 */
	public ByteArrayResource findFotoUsuario()
	{
		Usuario usuario = this.findPrincipal();
		return this.restTemplate.getForObject( this.baseUrl + "/api/usuarios/{id}/foto", ByteArrayResource.class, usuario.getId() );
	}

	/**
	 * 
	 * @return
	 */
	public Usuario findPrincipal()
	{
		final Authentication authenticatedUse = SecurityContextHolder.getContext().getAuthentication();
		final OAuth2RestTemplate template = new OAuth2RestTemplate( authorizationCodeResourceDetails, oAuth2ClientContext );
		Usuario user = new Usuario();
		if ( authenticatedUse != null )
		{
			user = ( Usuario ) authenticatedUse.getPrincipal();
		}
		else
		{
			user = template.getForObject( this.baseUrl + "/api/principal", Usuario.class );
		}
		return user;
	}

	/**
	 * 
	 * @param filters
	 * @param pageRequest
	 * @return
	 */
	public PageImpl<Usuario> listUsuariosByFilters( String filters, String perfil, PageRequest pageRequest )
	{
		Usuario[] usuarios = null;

		if ( pageRequest == null )
		{
			usuarios = this.restTemplate.getForObject( this.baseUrl + "/api/usuarios?filters={filters}&perfil={perfil}", Usuario[].class, filters, perfil );
			return new PageImpl<Usuario>( Arrays.asList( usuarios ) );
		}
		else
		{
			usuarios = this.restTemplate.getForObject( this.baseUrl + "/api/usuarios?filters={filters}&perfil={perfil}&page={page}&size={size}", Usuario[].class, filters, perfil, pageRequest.getPageNumber(), pageRequest.getPageSize() );
			pageRequest.setSize( usuarios.length );
			return new PageImpl<Usuario>( Arrays.asList( usuarios ), new PageableImpl( pageRequest ), usuarios.length );
		}

	}

	/**
	 * 
	 * @param filters
	 * @param pageRequest
	 * @return
	 */
	public Page<Usuario> listUsuariosByFiltersAndBloqueados( String filters, String perfil, PageRequest pageRequest )
	{

		Usuario[] usuarios = null;

		if ( pageRequest == null )
		{
			usuarios = this.restTemplate.getForObject( this.baseUrl + "/api/usuarios/bloqueados?filters={filters}&perfil={perfil}", Usuario[].class, filters, perfil );
			return new PageImpl<Usuario>( Arrays.asList( usuarios ) );
		}
		else
		{
			usuarios = this.restTemplate.getForObject( this.baseUrl + "/api/usuarios/bloqueados?filters={filters}&perfil={perfil}&page={page}&size={size}", Usuario[].class, filters, perfil, pageRequest.getPageNumber(), pageRequest.getPageSize() );
			pageRequest.setSize( usuarios.length );
			return new PageImpl<Usuario>( Arrays.asList( usuarios ), new PageableImpl( pageRequest ), usuarios.length );
		}

	}

	/**
	 * 
	 * @param filters
	 * @param perfil
	 * @param pageRequest
	 * @return
	 */
	public Page<Usuario> listUsuariosByFiltersAndExcluidos( String filters, String perfil, PageRequest pageRequest )
	{
		Usuario[] usuarios = null;

		if ( pageRequest == null )
		{
			usuarios = this.restTemplate.getForObject( this.baseUrl + "/api/usuarios/excluidos?filters={filters}&perfil={perfil}", Usuario[].class, filters, perfil );
			return new PageImpl<Usuario>( Arrays.asList( usuarios ) );
		}
		else
		{
			usuarios = this.restTemplate.getForObject( this.baseUrl + "/api/usuarios/excluidos?filters={filters}&perfil={perfil}&page={page}&size={size}", Usuario[].class, filters, perfil, pageRequest.getPageNumber(), pageRequest.getPageSize() );
			pageRequest.setSize( usuarios.length );
			return new PageImpl<Usuario>( Arrays.asList( usuarios ), new PageableImpl( pageRequest ), usuarios.length );
		}
	}
}
