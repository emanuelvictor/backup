/**
 * 
 */
package br.mil.mar.dabm.autenticador.domain.repository.aplicativo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.util.Assert;

import br.mil.mar.dabm.autenticador.domain.repository.aplicativo.IAplicativoRepository;
import br.mil.mar.dabm.common.domain.entity.aplicativo.Aplicativo;

/**
 * @author emanuelvictor
 *
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
	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.oauth2.provider.ClientDetailsService#loadClientByClientId(java.lang.String)
	 */
	@Override
	public ClientDetails loadClientByClientId( String clientId ) throws ClientRegistrationException
	{
		Aplicativo aplicativo = this.aplicativoRepository.findByIdentificador( clientId );

		Assert.notNull( aplicativo, "Aplicativo com o identificador '" + clientId + "' não encontrado" );

		if ( !aplicativo.getAtivo() )
		{
			throw new org.springframework.security.access.AccessDeniedException( aplicativo.getMensagemDesativacao() );
		}

//		Assert.isTrue( aplicativo.getAtivo(), aplicativo.getMensagemDesativacao() );

		return aplicativo;
	}
}
