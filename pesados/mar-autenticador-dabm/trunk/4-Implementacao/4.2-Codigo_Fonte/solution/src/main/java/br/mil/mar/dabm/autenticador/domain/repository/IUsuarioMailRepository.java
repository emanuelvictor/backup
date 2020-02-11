package br.mil.mar.dabm.autenticador.domain.repository;

import java.util.concurrent.Future;

import br.mil.mar.dabm.common.domain.entity.usuario.Usuario;

/**
 * Interface para o envio de e-mails
 *
 * @author rodrigo@eits.com.br
 * @since 02/10/2014
 * @version 1.0
 */
public interface IUsuarioMailRepository
{
	/*-------------------------------------------------------------------
	 *                          BEHAVIORS
	 *-------------------------------------------------------------------*/
	/**
	 * @param usuario
	 */
	public Future<Void> sendRedefinirSenha( Usuario usuario, String novaSenha );

	/**
	 * @param findByLoginIgnoreCase
	 * @param token
	 */
	public Future<Void> sendTokenRecuperarSenha( Usuario usuario, String token );

	/**
	 * @param findByLoginIgnoreCase
	 * 
	 */
	public Future<Void> sendSenhaAlterada( Usuario usuario );

	/**
	 * 
	 * @param usuario
	 * @param diasRestantes
	 * @return
	 */
	Future<Void> notifyBlockingUsuario( Usuario usuario, Integer diasRestantes );

	/**
	 * 
	 * @param usuario
	 * @param senhaAleatoria
	 * @return
	 */
	Future<Void> newUsuario( Usuario usuario, String senhaAleatoria );

}