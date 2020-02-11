package br.mil.mar.dabm.autenticador.infrastructure.mail;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import br.mil.mar.dabm.autenticador.domain.repository.IUsuarioMailRepository;
import br.mil.mar.dabm.common.domain.entity.usuario.Usuario;

/**
 * @author rodrigo@eits.com.br
 * @since 03/08/2012
 * @version 1.0
 */
@Component
public class UsuarioMailRepository implements IUsuarioMailRepository
{
	/*-------------------------------------------------------------------
	 *                          ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
     *
     */
	@Autowired
	private JavaMailSender mailSender;
	/**
     *
     */
	@Autowired
	private VelocityEngine velocityEngine;
	/**
     *
     */
	@Value("${spring.mail.from}")
	private String mailFrom;

	/*-------------------------------------------------------------------
	 *                          BEHAVIORS
	 *-------------------------------------------------------------------*/
	/**
	 *
	 * @param usuario
	 */
	@Async
	@Override
	public Future<Void> sendRedefinirSenha( final Usuario usuario , final String novaSenha)
	{
		final MimeMessagePreparator preparator = new MimeMessagePreparator()
		{
			public void prepare( MimeMessage mimeMessage ) throws Exception
			{
				final MimeMessageHelper message = new MimeMessageHelper( mimeMessage );
				message.setSubject( "Redefinição de senha" );
				message.setTo( usuario.getEmail() );
				message.setFrom( mailFrom );

				final Map<String, Object> model = new HashMap<String, Object>();
				model.put( "senha", novaSenha );
				model.put( "usuario", usuario );
				

				final String content = VelocityEngineUtils.mergeTemplateIntoString( velocityEngine, "mail/redefinir-senha.html", StandardCharsets.UTF_8.toString(), model );
				message.setText( content, true );
			}
		};

		this.mailSender.send( preparator );
		return new AsyncResult<Void>( null );
	}

	
	/**
	 * Recuperar senha - esqueci minha senha
	 */ 
	@Async
	@Override
	public Future<Void> sendTokenRecuperarSenha( Usuario usuario, String token )
	{
		final MimeMessagePreparator preparator = new MimeMessagePreparator()
		{
			public void prepare( MimeMessage mimeMessage ) throws Exception
			{
				final MimeMessageHelper message = new MimeMessageHelper( mimeMessage );
				message.setSubject( "Recuperação de senha" );
				message.setTo( usuario.getEmail() );
				message.setFrom( mailFrom );

				final Map<String, Object> model = new HashMap<String, Object>();
				model.put( "usuario", usuario );
				model.put( "token", token );

				final String content = VelocityEngineUtils.mergeTemplateIntoString( velocityEngine, "mail/recuperar-senha.html", StandardCharsets.UTF_8.toString(), model );
				message.setText( content, true );
			}
		};

		this.mailSender.send( preparator );
		return new AsyncResult<Void>( null );
	}
	
	
	/**
	 * Enviado quando é alterada a senha do usuário,no minha conta e no redefinir senha
	 * 
	 */
	@Async
	@Override
	public Future<Void> sendSenhaAlterada( Usuario usuario )
	{
		final MimeMessagePreparator preparator = new MimeMessagePreparator()
		{
			public void prepare( MimeMessage mimeMessage ) throws Exception
			{
				final MimeMessageHelper message = new MimeMessageHelper( mimeMessage );
				message.setSubject( "Senha alterada" );
				message.setTo( usuario.getEmail() );
				message.setFrom( mailFrom );

				final Map<String, Object> model = new HashMap<String, Object>();
				model.put( "usuario", usuario );

				final String content = VelocityEngineUtils.mergeTemplateIntoString( velocityEngine, "mail/senha-alterada.html", StandardCharsets.UTF_8.toString(), model );
				message.setText( content, true );
			}
		};

		this.mailSender.send( preparator );
		return new AsyncResult<Void>( null );
	}

	
	/**
	 * Email para o usuário de 15 dias antes da expiração de sua senha
	 *  
	 */
	@Override
	@Async
	public Future<Void> notifyBlockingUsuario( Usuario usuario, Integer diasRestantes )
	{
		final MimeMessagePreparator preparator = new MimeMessagePreparator()
		{
			public void prepare( MimeMessage mimeMessage ) throws Exception
			{
				final MimeMessageHelper message = new MimeMessageHelper( mimeMessage );
				message.setSubject( "Aviso bloqueio de conta" );
				message.setTo( usuario.getEmail() );
				message.setFrom( mailFrom );

				final Map<String, Object> model = new HashMap<String, Object>();
				model.put( "usuario", usuario );
				model.put( "diasRestantes", diasRestantes );

				final String content = VelocityEngineUtils.mergeTemplateIntoString( velocityEngine, "mail/notify-blocking-usuario.html", StandardCharsets.UTF_8.toString(), model );
				message.setText( content, true );
			}
		};

		this.mailSender.send( preparator );
		return new AsyncResult<Void>( null );
	}

	
	@Override
	@Async
	public Future<Void> newUsuario( Usuario usuario, String senhaAleatoria )
	{	
		final MimeMessagePreparator preparator = new MimeMessagePreparator()
		{
			public void prepare( MimeMessage mimeMessage ) throws Exception
			{
				final MimeMessageHelper message = new MimeMessageHelper( mimeMessage );
				message.setSubject( "Novo usuário do autenticador DAbM" );
				message.setTo( usuario.getEmail() );
				message.setFrom( mailFrom );

				final Map<String, Object> model = new HashMap<String, Object>();
				model.put( "usuario", usuario );
				model.put( "senhaAleatoria", senhaAleatoria );

				final String content = VelocityEngineUtils.mergeTemplateIntoString( velocityEngine, "mail/new-usuario.html", StandardCharsets.UTF_8.toString(), model );
				message.setText( content, true );
			}
		};

		this.mailSender.send( preparator );
		return new AsyncResult<Void>( null );
	}
}