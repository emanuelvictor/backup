/**
 * 
 */
package br.mil.mar.dabm.autenticador.domain.service;

import java.util.Calendar;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.mil.mar.dabm.common.domain.entity.usuario.TokenRecuperacaoSenha;
import br.mil.mar.dabm.common.domain.entity.usuario.Usuario;

/**
 * @author emanuelvictor
 *
 */
@Service
@Transactional
public class PublicService extends SynchronizeService
{
	/**
	 * Serviço utilizado pelos usuários simples para alterar suas próprias senhas
	 * 
	 * @param usuarioId
	 * @param novaSenha
	 * @param confirmacao
	 */
	public Usuario redefineSenhaUsuario( final String login, final String novaSenha, final String confirmacao )
	{
		Assert.notNull( confirmacao, "A confirmação de nova senha não pode ser vazia" );
		Assert.notNull( novaSenha, "A nova senha não pode ser vazia" );
		Assert.isTrue( novaSenha.equals( confirmacao ), "A confirmação de senha não confere com a nova senha desejada, tente novamente" );

		Usuario usuario = this.usuarioRepository.findByLoginIgnoreCase( login );

		Assert.notNull( usuario, "Usuário não encontrado." );

		Assert.isTrue( usuario.getAlterarSenha() || !usuario.isCredentialsNonExpired(), "As credenciais do usuário não estão expiradas" );

		usuario.setAlterarSenha( false );

		this.usuarioRepository.save( this.changePassword( this.synchronizePessoa( usuario ), novaSenha ) );

		usuario.setDataExpiracaoSenha( this.resetDataExpiracao() );

		usuario.setPerfisUsuarioAplicativo( null );
		return usuario;
	}

	/**
	 * Serviço utilizado para os usuários que perderam (esqueceram) suas senhas, envia um token para o email do usuário
	 * 
	 * @param login
	 * @return
	 */
	public Usuario recoverySenhaUsuario( final String login )
	{

		Assert.notNull( login, "O login não pode ser vazio" );

		Usuario usuario = this.usuarioRepository.findByLoginIgnoreCase( login );

		Assert.notNull( usuario, "Usuário com NIP '" + login + "' não encontrado" );

		TokenRecuperacaoSenha tokenRecuperacaoSenha = iTokenRecuperacaoSenhaRepository.findByUsuarioLogin( login );

		if ( tokenRecuperacaoSenha == null  )
		{
			tokenRecuperacaoSenha = new TokenRecuperacaoSenha();
			tokenRecuperacaoSenha.setUsuario( usuario );
		}

		tokenRecuperacaoSenha.setToken( UUID.randomUUID().toString() );

		tokenRecuperacaoSenha = iTokenRecuperacaoSenhaRepository.save( tokenRecuperacaoSenha );

		this.enviarTokenViaEmail( this.synchronizePessoa( usuario ), tokenRecuperacaoSenha.getToken() );

		usuario.setPerfisUsuarioAplicativo( null );
		return this.synchronizePessoa( usuario );
	}

	/**
	 * Usuário já recebeu o email e acessou o mesmo para recuperar a senha
	 * 
	 * @param login
	 * @return
	 */
	public Usuario recoverySenhaUsuarioByToken( /* Usuario usuario, */final String token )
	{

		// Verifica se o token realmente existe
		TokenRecuperacaoSenha tokenRecuperacaoSenha = this.iTokenRecuperacaoSenhaRepository.findByToken( token );

		Assert.notNull( tokenRecuperacaoSenha, "token-invalido" );

		Usuario usuario = tokenRecuperacaoSenha.getUsuario();

		usuario.setAlterarSenha( true );
		return this.usuarioRepository.save( this.synchronizePessoa( tokenRecuperacaoSenha.getUsuario() ) );

	}

	/**
	 * Método auxiliar para alteração e envio de email, utilizado tanto pelo changeSenhaUsuario quanto pelo resetSenhaUsuario
	 * 
	 * @param usuarioId
	 * @param novaSenha
	 */
	private Usuario changePassword( final Usuario usuario, final String novaSenha )
	{
		Usuario.validatePassword( novaSenha );
		final String encodedPassword = this.passwordEncoder.encodePassword( novaSenha, saltSource.getSalt( usuario ) );
		usuario.setSenha( encodedPassword );

		usuario.setDataAlteracaoSenha( Calendar.getInstance() );
		this.enviarEmail( usuario );

		return usuario;
	}

	/**
	 * Reconfigura data para restaurar a data de expiração de senha do usuário
	 * 
	 * @return
	 */
	private Calendar resetDataExpiracao()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set( Calendar.DAY_OF_YEAR, Calendar.getInstance().get( Calendar.DAY_OF_YEAR ) + this.configuracaoRepository.getOne( 1L ).getDiasExpiracaoSenha() );
		return calendar;
	}

	/**
	 * 
	 * @param login
	 */
	private void enviarEmail( final Usuario usuario )
	{
		this.usuarioMailRepository.sendSenhaAlterada( usuario );
	}

	/**
	 * Método que envia token para o usuário via email
	 * 
	 * @param login
	 */
	private void enviarTokenViaEmail( final Usuario usuario, final String token )
	{
		this.usuarioMailRepository.sendTokenRecuperarSenha( usuario, token );
	}

}
