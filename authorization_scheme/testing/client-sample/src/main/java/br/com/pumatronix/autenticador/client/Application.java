package br.com.pumatronix.autenticador.client;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableOAuth2Client
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Value("${oauth.authorize:http://localhost:8080/auth-engine/oauth/authorize}")
	private String authorizeUrl;

	@Value("${oauth.token:http://localhost:8080/auth-engine/oauth/token}")
	private String tokenUrl;
	
	@Bean
	public OAuth2RestOperations restTemplate(OAuth2ClientContext oauth2ClientContext) {
		return new OAuth2RestTemplate(resource(), oauth2ClientContext);
	}

	@Bean
	protected OAuth2ProtectedResourceDetails resource() {
		AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
		//ImplicitResourceDetails resource = new ImplicitResourceDetails();
		resource.setAccessTokenUri(tokenUrl);
		resource.setUserAuthorizationUri(authorizeUrl);
		resource.setClientId("client1");
		resource.setClientSecret("123456");
		resource.setScope( Arrays.asList("ADMINISTRATOR", "PUBLIC") );
		return resource;
	}
	

	
//	@Bean
//	protected OAuth2ProtectedResourceDetails resource() {
//		
//		ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
//		//ImplicitResourceDetails resource = new ImplicitResourceDetails();
//		resource.setAccessTokenUri(tokenUrl);
//		
////		resource.setUserAuthorizationUri(authorizeUrl);
//		resource.setClientId("client1");
//		resource.setClientSecret("123456");
//		resource.setUsername("admin");
//		resource.setPassword("adminpwd");
//		resource.setScope( Arrays.asList("ADMINISTRATOR", "PUBLIC") );
//		
//		return resource;
//	}
	
//	@Bean
//	protected OAuth2ProtectedResourceDetails resource() {
//		
//		ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
//		//ImplicitResourceDetails resource = new ImplicitResourceDetails();
//		resource.setAccessTokenUri(tokenUrl);
////		resource.setUserAuthorizationUri(authorizeUrl);
//		resource.setClientId("client1");
//		resource.setClientSecret("123456");
//		resource.setScope( Arrays.asList("ADMINISTRATOR", "PUBLIC") );
//		return resource;
//	}
}
