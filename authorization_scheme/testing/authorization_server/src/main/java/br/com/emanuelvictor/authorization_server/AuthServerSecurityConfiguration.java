package br.com.emanuelvictor.authorization_server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class AuthServerSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationManager authenticationManager;

//	@Bean
//    public InMemoryTokenStore tokenStore() 
//	{		 
//        return new InMemoryTokenStore();
//    }
//	
//	@Autowired
//    public TokenStore tokenStore;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication()
			.withUser("adminClient1@email.com").password("123456").roles("ADMINISTRATOR","PUBLIC")
			.and()
			.withUser("admin").password("adminpwd").roles("ADMINISTRATOR","PUBLIC")
			/* FIXME : check_token api validates client credentials via basic authorization */
			.and()
			.withUser("soncrserv").password("soncrserv").roles("PUBLIC");

		auth.parentAuthenticationManager(authenticationManager);
	}
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
		.csrf()
			.disable()
//		.headers()
//			.frameOptions()
//				.disable()
		.authorizeRequests()
			.anyRequest()
				.authenticated()
			.and()
				.formLogin()
					.loginPage( "/login" )
//					.loginProcessingUrl( "/" )
					.permitAll()
			.and()
				.logout()
					.logoutUrl( "/logout" )
					.logoutSuccessUrl( "/login" )
			.and()
				.requestMatchers()
					.antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access");
		
		
//		http.antMatcher("/**")
//		.csrf().disable() /*.addFilterAfter(new CsrfTokenGeneratorFilter(), CsrfFilter.class)*/
//			.formLogin().loginPage("/login").permitAll()
//		.and()
//			.logout().logoutUrl("/logout").and()
//			.requestMatchers()
//				.antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access")
//		.and()
//			.authorizeRequests()
//				.antMatchers("/login", "/revoke").permitAll()
//				.anyRequest().authenticated();
		// @formatter:on
	}
}
