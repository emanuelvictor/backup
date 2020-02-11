/**
 * 
 */
package br.com.eits.oauth2.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;


/**
 * @author emanuelvictor
 *
 */
@Configuration
@EnableWebSecurity

public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	ResourceServerTokenServices tokenServices;

	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
	    authenticationManager.setTokenServices(tokenServices);
	    return authenticationManager;
	}
}
