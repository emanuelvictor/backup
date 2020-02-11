/**
 * 
 */
package br.com.eits.oauth2.client;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author emanuelvictor
 *
 */
@RestController
//@Configuration
//@EnableWebSecurity
public class ResourceServer {

	@RequestMapping(value = "/resource", method = RequestMethod.GET)
	public Object resource() {
		return new StringBuffer("Sei lá rolou");
	}
	
	@RequestMapping(value = "/", 
			method = RequestMethod.GET)
	public Object main() {
		return new StringBuffer("Sei lá rolou");
	}
	
	
	
//	@Bean
//	public ResourceServerTokenServices tokenService() {
//	    RemoteTokenServices tokenServices = new RemoteTokenServices();
//	    tokenServices.setClientId("local1");
//	    tokenServices.setClientSecret("123456");
//	    tokenServices.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
//	    return tokenServices;
//	}
//
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//	    OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
//	    authenticationManager.setTokenServices(tokenService());
//	    return authenticationManager;
//	}
//	
//
//	@Configuration
//	@EnableResourceServer
//	protected static class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//		@Autowired
//		ResourceServerTokenServices tokenServices;
//		
//	    @Override
//	    public void configure(HttpSecurity http) throws Exception {
//	        http
//	            .requestMatchers()
//	            .antMatchers("/","/resource")
//	            .and()
//	            .authorizeRequests()
//	            .anyRequest().authenticated();
//	    }
//
//	    @Override
//	    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
////	        TokenStore tokenStore = new InMemoryTokenStore();
////	        resources.resourceId("Resource Server");
//	        resources.tokenServices(tokenServices);
//	    }
//	}
}
