package rites.sample.aserv;

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

@EnableAuthorizationServer
@Configuration
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public void configure(final ClientDetailsServiceConfigurer clients)
			throws Exception {
		clients.inMemory()
				.withClient("soncui")
				.secret("soncui")
				.autoApprove(true)
				.authorizedGrantTypes("authorization_code", "password", "refresh_token")
				.scopes(new String[] { "read", "write" });
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer)
			throws Exception {
		oauthServer
			.tokenKeyAccess("permitAll()")
			.checkTokenAccess("permitAll()");
	}
	
	@Bean
	public DefaultAccessTokenConverter defaultAccessTokenConverter() {
		return new DefaultAccessTokenConverter();
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints)
			throws Exception {
		endpoints.authenticationManager(authenticationManager).accessTokenConverter(
				defaultAccessTokenConverter());
	}
}
