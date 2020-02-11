package br.mil.mar.dabm.autenticador.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


/**
 * 
 * @author rodrigo
 */
@Configuration
@EnableResourceServer
public class OAuth2ResourceConfig extends ResourceServerConfigurerAdapter
{
	
	/**
	 * 
	 * @return
	 */
	@Bean
	public JwtTokenStore tokenStore( JwtAccessTokenConverter tokenEnhancer )
	{
		return new JwtTokenStore( tokenEnhancer );
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	public JwtAccessTokenConverter tokenEnhancer()
	{
		final JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey( "marinhadobrasil" );
		return jwtAccessTokenConverter;
	}
	
	/**
	 * Use this to configure the access rules for secure resources. 
	 * By default all resources <i>not</i> in "/oauth/**" are protected (but no specific rules about scopes are given, for instance). 
	 * You also get an {@link OAuth2WebSecurityExpressionHandler} by default.
	 * 
	 * @param http the current http filter configuration
	 * @throws Exception if there is a problem
	 */
	@Override
    public void configure( HttpSecurity httpSecurity ) throws Exception 
	{
		httpSecurity.antMatcher( "/api/**" )
			.csrf()
				.disable()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			.and()
				.authorizeRequests().antMatchers("/api/usuarios/recuperarSenha**", "/api/usuarios/redefinirSenha/**").permitAll()
					.anyRequest()
						.authenticated();
    }

	/**
	 * Add resource-server specific properties (like a resource id). 
	 * The defaults should work for many applications, but you might want to change at least the resource id.
	 * 
	 * @param resources configurer for the resource server
	 * @throws Exception if there is a problem
	 */
    @Override
    public void configure( ResourceServerSecurityConfigurer resourceServerSecurityConfigurer ) throws Exception
    {
    	resourceServerSecurityConfigurer.resourceId( "autenticador-resource" );
    }
    
   
}