package br.mil.mar.dabm.autenticador.domain.repository.aplicativo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

import br.mil.mar.dabm.autenticador.domain.entity.aplicativo.Aplicativo;
import br.mil.mar.dabm.autenticador.domain.repository.aplicativo.IAplicativoRepository;

/**
 * 
 * @author rodrigo@eits.com.br
 * @since 22/04/2014
 * @version 1.0
 * @category Repository
 */
public class ClientDetailsServiceImpl implements ClientDetailsService
{

	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private IAplicativoRepository aplicativoRepository;

	/*-------------------------------------------------------------------
	 *				 		     BEHAVIORS
	 *-------------------------------------------------------------------*/
	@Override
	public ClientDetails loadClientByClientId( String clientId ) throws ClientRegistrationException
	{
		System.out.println( clientId );
		Aplicativo aplicativo = this.aplicativoRepository.findOne( Long.parseLong( clientId ) );
		if ( aplicativo == null )
		{
			throw new UsernameNotFoundException( "This clientId '" + clientId + "' was not found" );
		}
		else
		{
			return aplicativo;
		}
	}
}
