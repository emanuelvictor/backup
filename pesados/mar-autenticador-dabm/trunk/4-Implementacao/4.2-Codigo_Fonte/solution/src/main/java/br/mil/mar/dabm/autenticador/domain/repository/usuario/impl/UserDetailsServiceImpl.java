/**
 * 
 */
package br.mil.mar.dabm.autenticador.domain.repository.usuario.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.mil.mar.dabm.autenticador.domain.repository.IUsuarioMailRepository;
import br.mil.mar.dabm.autenticador.domain.repository.usuario.IUsuarioRepository;
import br.mil.mar.dabm.common.domain.entity.usuario.Usuario;

/**
 * @author emanuelvictor
 *
 */
public class UserDetailsServiceImpl implements UserDetailsService
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private IUsuarioRepository usuarioRepository;

	/**
	 * serviço para enviar e-mail para o usuário
	 */
	@Autowired
	private IUsuarioMailRepository usuarioMailRepository;

	/*-------------------------------------------------------------------
	 *				 		     BEHAVIORS
	 *-------------------------------------------------------------------*/
	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername( String login ) throws UsernameNotFoundException
	{
		Usuario usuario = this.usuarioRepository.findByLoginIgnoreCase( login );

		verifyAndNotifyBlockingUsuario( usuario );

		if ( usuario == null )
		{
			throw new UsernameNotFoundException( "This login '" + login + "' was not found" );
		}
		else
		{
			usuario.setDataUltimoAcesso( Calendar.getInstance() );
			return this.usuarioRepository.save( usuario );
		}
	}

	/**
	 * Verifica se o usuário esta dentro da faixa de 15 dias de expiração de senha e envia um email
	 * 
	 * @param usuario
	 */
	private void verifyAndNotifyBlockingUsuario( Usuario usuario )
	{
		if ( usuario != null && !usuario.equals( null ) )
		{
			Calendar hoje = Calendar.getInstance();

			if ( hoje.before( usuario.getDataExpiracaoSenha() ) )
			{
				Integer diasRestantes = usuario.getDataExpiracaoSenha().get( Calendar.DAY_OF_YEAR) - hoje.get(Calendar.DAY_OF_YEAR);
				if ( diasRestantes < 15 && diasRestantes > 0)
				{
					this.usuarioMailRepository.notifyBlockingUsuario( usuario, diasRestantes );
				}
			}
		}
	}
}
