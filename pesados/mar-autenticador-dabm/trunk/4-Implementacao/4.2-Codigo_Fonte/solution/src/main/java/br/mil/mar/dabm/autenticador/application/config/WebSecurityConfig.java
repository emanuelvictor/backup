package br.mil.mar.dabm.autenticador.application.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

import br.mil.mar.dabm.autenticador.application.security.AuthenticationFailureHandler;
import br.mil.mar.dabm.autenticador.domain.repository.usuario.impl.UserDetailsServiceImpl;

/**
 * 
 * @author rodrigo
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableGlobalMethodSecurity/*(prePostEnabled = false )*/
public class WebSecurityConfig extends GlobalMethodSecurityConfiguration
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/

	/*-------------------------------------------------------------------
	 * 							BEANS
	 *-------------------------------------------------------------------*/
    /**
     *	We override here just to avoid the spring auto boot.
     *
     * @return authenticationManager
     */
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception
    {
        return super.authenticationManager();
    }
	
    /**
     * Provide a {@link OAuth2MethodSecurityExpressionHandler}
     *
     * @return
     */
	@Override
    protected MethodSecurityExpressionHandler createExpressionHandler() 
	{
        return new OAuth2MethodSecurityExpressionHandler();
    }
	
	/*-------------------------------------------------------------------
	 *-------------------------------------------------------------------*/
	/**
	 * Default Web Form Security Configurer
	 * 
	 * @author rodrigo
	 */
	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER-1)
    public static class DefaultFormWebSecurityConfigurer extends WebSecurityConfigurerAdapter 
    {
		/**
		 * 
		 */
		@Autowired
		private UserDetailsServiceImpl userDetailsService;
		/**
		 * 
		 */
		@Autowired
		private ShaPasswordEncoder passwordEncoder;
		/**
		 * 
		 */
		@Autowired
		private SaltSource saltSource;
		
	    /**
	     * 
	     */
		@Override
		protected void configure( AuthenticationManagerBuilder authenticationManagerBuilder ) throws Exception 
		{
			final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
			authenticationProvider.setSaltSource(saltSource);
			authenticationProvider.setUserDetailsService(userDetailsService);
			authenticationProvider.setPasswordEncoder( passwordEncoder );
		    
			authenticationManagerBuilder.authenticationProvider( authenticationProvider );
	    }
		
		/**
		 * 
		 */
		@Override
		protected void configure( HttpSecurity httpSecurity ) throws Exception
		{
			httpSecurity
				.csrf()
					.disable()
				.headers()
					.frameOptions()
						.disable()
				.authorizeRequests()
					.antMatchers( "/usuarios/recuperarSenha**", "/authentication")
						.permitAll()
					.anyRequest()
						.authenticated()
					.and()
						.formLogin()
							.usernameParameter( "login" )
							.passwordParameter( "senha" )
							.loginPage( "/authentication" )
							.loginProcessingUrl( "/authenticate" )
							.failureHandler( new AuthenticationFailureHandler())
							.permitAll()
					.and()
						.logout()
							.logoutUrl( "/logout" )
							.logoutSuccessUrl( "/authentication" );
		}
	}	
}