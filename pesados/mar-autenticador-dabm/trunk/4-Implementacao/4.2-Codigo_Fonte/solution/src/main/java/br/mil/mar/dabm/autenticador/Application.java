package br.mil.mar.dabm.autenticador;

import org.directwebremoting.spring.DwrSpringServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 * @author rodrigo@eits.com.br
 */
@EnableAsync
@SpringBootApplication
@EntityScan("br.mil.mar.dabm.common.domain.entity")
@PropertySource(
{ "classpath:/config/datasource.properties", "classpath:/config/jpa.properties", "classpath:/config/mail.properties", "classpath:/config/logging.properties", })
@ImportResource(
{ "classpath:/META-INF/spring/autenticador-context.xml", })
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class Application extends SpringBootServletInitializer
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
	/**
	 * 
	 */
	public static final String AUDIT_SCHEMA = "auditoria";

	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param args
	 */
	@Override
	protected SpringApplicationBuilder configure( SpringApplicationBuilder application )
	{
		return application.sources( Application.class );
	}

	/*-------------------------------------------------------------------
	 * 		 					CONFIGURATIONS
	 *-------------------------------------------------------------------*/

	// ---------
	// Web Config
	// ---------
	@Configuration
	protected static class WebConfiguration extends WebMvcConfigurerAdapter
	{
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

}