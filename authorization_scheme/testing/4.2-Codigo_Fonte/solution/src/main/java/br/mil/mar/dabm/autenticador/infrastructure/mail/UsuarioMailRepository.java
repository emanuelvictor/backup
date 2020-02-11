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

import br.mil.mar.dabm.autenticador.domain.entity.usuario.Usuario;
import br.mil.mar.dabm.autenticador.domain.repository.IUsuarioMailRepository;

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
	public Future<Void> sendRecuperarSenha( final Usuario usuario )
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
				model.put( "usuario", usuario );

				final String content = VelocityEngineUtils.mergeTemplateIntoString( velocityEngine, "mail/recuperar-senha.html", StandardCharsets.UTF_8.toString(), model );
				message.setText( content, true );
			}
		};

		this.mailSender.send( preparator );
		return new AsyncResult<Void>( null );
	}
}