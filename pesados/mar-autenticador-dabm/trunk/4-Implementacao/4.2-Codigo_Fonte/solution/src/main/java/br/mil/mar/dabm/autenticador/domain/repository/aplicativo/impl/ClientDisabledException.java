/**
 * 
 */
package br.mil.mar.dabm.autenticador.domain.repository.aplicativo.impl;

/**
 * @author emanuelvictor
 *
 */
public class ClientDisabledException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param mensagemDesativacao
	 */
	public ClientDisabledException( String mensagemDesativacao )
	{
		super( mensagemDesativacao );
	}

}
