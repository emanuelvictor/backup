package br.mil.mar.dabm.common.domain.entity.aplicativo;

import org.directwebremoting.annotations.DataTransferObject;

/**
 * 
 * @author rodrigo@eits.com.br
 */
@DataTransferObject
public enum TipoAcesso
{
	/*-------------------------------------------------------------------
	 *								ENUMS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	AUTHORIZATION_CODE ( "authorization_code" ),
	/**
	 * 
	 */
	IMPLICIT ( "token" ),
	/**
	 * 
	 */
	REFRESH_TOKEN ( "refresh_token" ),
	/**
	 * 
	 */
	PASSWORD ( "password" ),
	/**
	 * 
	 */
	CLIENT_CREDENTIALS ( "client_credentials" );

	/*-------------------------------------------------------------------
	 *								ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	private final String tipo;

	/*-------------------------------------------------------------------
	 *								CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	

	/**
	 * 
	 */
	private TipoAcesso( String tipo )
	{
		this.tipo = tipo;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo()
	{
		return tipo;
	}
}