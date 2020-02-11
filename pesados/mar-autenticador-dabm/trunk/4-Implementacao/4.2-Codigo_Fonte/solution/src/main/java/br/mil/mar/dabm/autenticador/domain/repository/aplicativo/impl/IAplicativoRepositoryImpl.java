package br.mil.mar.dabm.autenticador.domain.repository.aplicativo.impl;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

import br.mil.mar.dabm.common.domain.entity.aplicativo.Aplicativo;

/**
 * 
 * @author rodrigo@eits.com.br 
 * @since 22/04/2014
 * @version 1.0
 * @category Repository
 */
public class IAplicativoRepositoryImpl implements ClientDetailsService
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private EntityManager entityManager;
	
	/*-------------------------------------------------------------------
	 *				 		     BEHAVIORS
	 *-------------------------------------------------------------------*/
	  /**
	   * Load a client by the client id. This method must not return null.
	   *
	   * @param clientId The client id.
	   * @return The client details (never null).
	   * @throws ClientRegistrationException If the client account is locked, expired, disabled, or invalid for any other reason.
	   */
	@Override
	public ClientDetails loadClientByClientId( String identificador ) throws ClientRegistrationException
	{
		try
		{
			System.out.println("Buscando aplicativo pelo código " + identificador);
			final String hql = "FROM Aplicativo aplicativo "
							+ "WHERE aplicativo.identificador = :identificador";
			
			final TypedQuery<Aplicativo> query = this.entityManager.createQuery( hql, Aplicativo.class );
			query.setParameter("identificador", identificador);
			return query.getSingleResult();
			
		}
		catch (NoResultException e)
		{
			throw new ClientRegistrationException("This Application code '"+identificador+"' was not found");
		}
	}
}
