/**
 * 
 */
package br.com.eits.oauth2.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author emanuelvictor
 *
 */
@Configuration
@EnableWebMvc
@EnableOAuth2Client
public class OAuth2ClientConfiguration extends WebMvcConfigurerAdapter {

	// private static final String RESOURCE = "http://localhost:8080/resource";

//	private static final String ACCESSTOKENURI = "http://localhost:8080/oauth/token";
//	private static final String USERAUTORIZATIONURI = "http://localhost:8080/oauth/authorize";

//	@Resource
//	private OAuth2ClientContext oAuth2ClientContext;
//
//	@Bean
//	public OAuth2RestTemplate resourceRestTemplate() {
//		return new OAuth2RestTemplate(resource(), oAuth2ClientContext);
//	}
//
//	@Bean
//	public OAuth2ProtectedResourceDetails resource() {
//		AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
//		// details.setId("sparklr/tonr");
//		details.setClientId("local1");
//		details.setClientSecret("123456");
//		details.setAccessTokenUri(ACCESSTOKENURI);
//		details.setUserAuthorizationUri(USERAUTORIZATIONURI);
//		details.setScope(new LinkedList<String>());
//		return details;
//	}
	
	@Bean
	public ResourceServerTokenServices tokenService() {
	    RemoteTokenServices tokenServices = new RemoteTokenServices();
	    tokenServices.setClientId("local1");
	    tokenServices.setClientSecret("123456");
	    tokenServices.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
//		tokenServices.
		return tokenServices;
	}
	
	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfig extends ResourceServerConfigurerAdapter {

		@Autowired
		ResourceServerTokenServices tokenServices;
		
	    @Override
	    public void configure(HttpSecurity http) throws Exception {
	        http
	            .requestMatchers()
	            .antMatchers("/","/resource")
	            .and()
	            .authorizeRequests()
	            .anyRequest().authenticated();
	    }

	    @Override
	    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//	        TokenStore tokenStore = new InMemoryTokenStore();
//	        resources.resourceId("Resource Server");
	        resources.tokenServices(tokenServices);
	    }
	}
}
