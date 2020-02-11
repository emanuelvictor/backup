package br.com.emanuelvictor.authorization_server;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import com.overcoming.GrantType;

@EnableAuthorizationServer
@Configuration
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {

	 @Autowired
	 private DataSource dataSource;

	@Bean
	public TokenStore tokenStore() {
		return /*new InMemoryTokenStore();*/ new JdbcTokenStore(dataSource);
	}

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public void configure(final ClientDetailsServiceConfigurer clients)
			throws Exception {
		clients.inMemory()
				.withClient("client1")
				.secret("123456")
				.autoApprove(true)
				.authorities( new String[] { "ADMINISTRATOR", "PUBLIC" } )
				.authorizedGrantTypes("authorization_code", /*"refresh_token", */GrantType.CLIENT_CREDENTIALS.getGrantType(), GrantType.PASSWORD.getGrantType())
				.scopes(new String[] { "ADMINISTRATOR", "PUBLIC" })
				.and().withClient("sonc")
				.secret("123456")
				.autoApprove(true)
				.authorities( new String[] { "ADMINISTRATOR", "PUBLIC" } )
				.authorizedGrantTypes("authorization_code", /*"refresh_token", */GrantType.CLIENT_CREDENTIALS.getGrantType(), GrantType.PASSWORD.getGrantType())
				.scopes(new String[] { "ADMINISTRATOR", "PUBLIC" });
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer authorizationServerSecurityConfigurer)
			throws Exception {
//		authorizationServerSecurityConfigurer.allowFormAuthenticationForClients();
		authorizationServerSecurityConfigurer
			.tokenKeyAccess("permitAll()")
			.checkTokenAccess("permitAll()");
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints)
			throws Exception {	
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore());
	}
	
	
}
