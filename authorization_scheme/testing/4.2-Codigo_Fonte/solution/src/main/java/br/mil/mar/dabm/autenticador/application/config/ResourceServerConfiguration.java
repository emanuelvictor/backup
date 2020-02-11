package br.mil.mar.dabm.autenticador.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;

import br.mil.mar.dabm.autenticador.application.security.AuthenticationFailureHandler;


/**
 * 
 * @author rodrigo
 */
//@Configuration
//@EnableResourceServer
public class ResourceServerConfiguration// extends ResourceServerConfigurerAdapter
{	
//	@Override
//    public void configure( HttpSecurity httpSecurity ) throws Exception 
//	{
//		httpSecurity
//		.formLogin()
//				.usernameParameter( "login" )
//				.passwordParameter( "senha" )
//				.loginPage( "/authentication" )
//				.loginProcessingUrl( "/authenticate" )
//				.failureHandler( new AuthenticationFailureHandler() )
//				.permitAll()
//		.and()
//			.logout()
//				.logoutUrl( "/logout" )
//				.logoutSuccessUrl( "/authenticate" )
//		.and()
//			.requestMatchers().antMatchers("/authentication", "/oauth/authorize", "/oauth/confirm_access")
//		.and()
//			.authorizeRequests().anyRequest().authenticated();
//    }
}