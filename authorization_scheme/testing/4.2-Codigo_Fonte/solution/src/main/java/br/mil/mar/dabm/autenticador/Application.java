package br.mil.mar.dabm.autenticador;

import org.directwebremoting.spring.DwrSpringServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.mil.mar.dabm.autenticador.application.config.OAuth2AuthorizationServerConfiguration;
import br.mil.mar.dabm.autenticador.application.config.WebSecurityConfiguration;

/**
 * 
 * @author rodrigo@eits.com.br
 */
@SpringBootApplication
@PropertySource({
	"classpath:/config/datasource.properties",
	"classpath:/config/jpa.properties",
	"classpath:/config/mail.properties",
	"classpath:/config/logging.properties",
})
@ImportResource({
	"classpath:/META-INF/spring/autenticador-context.xml",
})
@Configuration
@Import({OAuth2AuthorizationServerConfiguration.class, WebSecurityConfiguration.class})
//@EnableAutoConfiguration
public class Application /*extends SpringBootServletInitializer*/ extends WebMvcConfigurerAdapter 
{
	/**
	 * 
	 * @param args
	 */
	public static void main( String[] args )
	{
		SpringApplication.run( Application.class, args );
	}
	
	/*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/
	
	
//	public static final String AUDIT_SCHEMA = "auditoria";
	
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param args
	 */
//	@Override
//	protected SpringApplicationBuilder configure( SpringApplicationBuilder application )
//	{
//		return application.sources( Application.class );
//	}

	/*-------------------------------------------------------------------
	 * 		 					CONFIGURATIONS
	 *-------------------------------------------------------------------*/
//----------WEB
	/**
	 * 
	 * @return
	 */
	@Bean
	public ServletRegistrationBean dwrSpringServletRegistration()
	{
		final ServletRegistrationBean registration = new ServletRegistrationBean( new DwrSpringServlet(), "/broker/*" );
		registration.addInitParameter( "debug", "true" );
		registration.setName( "dwrSpringServlet" );
		return registration;
	}
}