package br.mil.mar.dabm.autenticador.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;

import br.mil.mar.dabm.autenticador.domain.repository.aplicativo.impl.ClientDetailsServiceImpl;

/**
 * 
 * @author rodrigo@eits.com.br
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter
{
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private ClientDetailsServiceImpl clientDetailsServiceImpl;
	
	@Override
	public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
		
//		clients.withClientDetails( clientDetailsServiceImpl );
		
//		clients.inMemory()
//				.withClient("201")
//				.secret("123456")
//				.autoApprove(true)
//				.authorizedGrantTypes("authorization_code", "password", "refresh_token")
//				.scopes(new String[] { "read", "write" });
		
		clients.inMemory()
		.withClient("201")
		.secret("b77c1257-6549-43dc-9696-a9018c92a227")
		.autoApprove(true)
		.authorizedGrantTypes("authorization_code", "password", "refresh_token")
		.scopes(new String[] { "ADMINISTRATOR", "PUBLIC" });
		
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer)
			throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()");
	}
	
	@Bean
	public DefaultAccessTokenConverter defaultAccessTokenConverter() {
		return new DefaultAccessTokenConverter();
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints)
			throws Exception {
		endpoints.authenticationManager(authenticationManager)
		.accessTokenConverter(defaultAccessTokenConverter());
	}
}