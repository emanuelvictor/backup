/**
 * 
 */
package br.com.emanuelvictor.funcionario2;

import br.com.emanuelvictor.funcionario2.service.ServiceApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author emanuelvictor --> Configuração do servidor de Autorização
 */

@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfiguration extends
		AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

    @Autowired
	private ServiceApplication serviceApplication;

	@Autowired
	private TokenStore tokenStore;// = new InMemoryTokenStore();

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception
	{
		endpoints.tokenStore(tokenStore).authenticationManager(
				this.authenticationManager);

	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception
	{

		clientDetailsServiceConfigurer.withClientDetails(serviceApplication);


//		clientDetailsServiceConfigurer.inMemory()
//			.withClient("funcionario2")
//			.authorizedGrantTypes("password", "refresh_token")
//            .redirectUris("http://0.0.0.0:9000/#/")
//
//			.scopes("perfis", "e", "permissões")
//    			.secret("123456")
//
//			.and()
//
//			.withClient("local1")
//			.authorizedGrantTypes("password","authorization_code", "refresh_token")
//
//			.scopes("perfis", "e", "permissões")
//			.secret("123456");

	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer authorizationServerSecurityConfigurer) throws Exception
	{

		authorizationServerSecurityConfigurer.checkTokenAccess("permitAll()");
	}

}
