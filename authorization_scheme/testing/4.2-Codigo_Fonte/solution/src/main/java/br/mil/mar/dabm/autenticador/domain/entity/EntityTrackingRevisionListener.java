package br.mil.mar.dabm.autenticador.domain.entity;

import java.io.Serializable;

import org.hibernate.envers.RevisionType;
import org.springframework.security.authentication.InsufficientAuthenticationException;

import br.mil.mar.dabm.autenticador.application.security.ContextHolder;
import br.mil.mar.dabm.autenticador.domain.entity.usuario.Usuario;

/**
 * @author rodrigo@eits.com.br
 * @since 06/12/2012
 * @version 1.0
 * @category Entity
 */
public class EntityTrackingRevisionListener implements org.hibernate.envers.EntityTrackingRevisionListener
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/

	/*-------------------------------------------------------------------
	 *				 		     BEHAVIORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Override
	public void newRevision( Object revisionEntity )
	{
		try
		{
			final Usuario usuario = ContextHolder.getAuthenticatedUser();
			( ( Revisao<?,?> ) revisionEntity ).setUserId( usuario.getId() );
		}
		catch ( InsufficientAuthenticationException e )
		{
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.hibernate.envers.EntityTrackingRevisionListener#entityChanged(java.lang.Class, java.lang.String, java.io.Serializable, org.hibernate.envers.RevisionType, java.lang.Object)
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public void entityChanged( Class entityClass, String entityName, Serializable entityId, RevisionType revisionType, Object revisionEntity )
	{
	}

	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
}