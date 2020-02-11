package br.mil.mar.dabm.autenticador.client;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * 
 * @author rodrigo@eits.com.br
 */
@EnableOAuth2Client
@SpringBootApplication
@ComponentScan(basePackages={"br.mil.mar.dabm"})
public class AutenticadorClientSampleApplication extends SpringBootServletInitializer
{
	/**
	 * 
	 * @param args
	 */
	public static void main( String[] args )
	{
		SpringApplication.run( AutenticadorClientSampleApplication.class, args );
	}
	
	/*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/
	
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
		return application.sources( AutenticadorClientSampleApplication.class );
	}

	/*-------------------------------------------------------------------
	 * 		 					BEANS
	 *-------------------------------------------------------------------*/
	

	/*-------------------------------------------------------------------
	 * 		 					CONFIGURATIONS
	 *-------------------------------------------------------------------*/
	//---------
	// Web Config
	//---------
	@Configuration
	protected static class WebConfiguration extends WebMvcConfigurerAdapter
	{
		/**
		 * 
		 */
		@Value("${oauth.authorizeUrl:http://localhost:8080/oauth/authorize}")
		private String authorizeUrl;

		/**
		 * 
		 */
		@Value("${oauth.tokenUrl:http://localhost:8080/oauth/token}")
		private String tokenUrl;
				
		/**
		 * 
		 * @return
		 */
		@Bean
		public OAuth2RestOperations restTemplate( OAuth2ClientContext oauth2ClientContext ) 
		{
			return new OAuth2RestTemplate( this.authorizationCodeResourceDetails(), oauth2ClientContext );
		}

		/**
		 * 
		 * @return
		 */
		@Bean
		protected AuthorizationCodeResourceDetails authorizationCodeResourceDetails() 
		{
			final AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
			resource.setAccessTokenUri(this.tokenUrl);
			resource.setUserAuthorizationUri(this.authorizeUrl);
			resource.setClientId("GOS");
			resource.setScope( Arrays.asList("FULL") );
			return resource;
		}
	}
}