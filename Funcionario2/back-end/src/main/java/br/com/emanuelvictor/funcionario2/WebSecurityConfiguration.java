package br.com.emanuelvictor.funcionario2;

import br.com.emanuelvictor.funcionario2.service.ServiceEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableWebSecurity
@EnableWebMvcSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
{

	@Autowired
	private ServiceEmployee userDetailsService;

	// @Bean TODO quando necessário definir esse método como Bean
	public DaoAuthenticationProvider daoAuthenticationProvider()
	{
		// Objeto do spring com a implementação do provider
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		// Setando o userDetailService no provider
		provider.setUserDetailsService( this.userDetailsService );
		// Setando o passowrd no provider
		provider.setPasswordEncoder( new ShaPasswordEncoder() );

		return provider;
	}

	//Configurando provider de autenticação (setando Sha)
	@Override
	protected void configure( AuthenticationManagerBuilder auth ) throws Exception
	{
		// Setando o provider no autenticationBuilder
		auth.authenticationProvider( this.daoAuthenticationProvider() );
	}

	// Configurando o authenticationManager
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}

	@Bean
	@Primary
	public TokenStore tokenStore()
	{
		TokenStore tokenStore = new InMemoryTokenStore();
		return tokenStore;
	}

	@Bean
	// @Primary TODO descobrir porque esse bean não funciona
	public DefaultTokenServices defaultTokenServices()
	{
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setSupportRefreshToken( true );
		defaultTokenServices.setTokenStore( this.tokenStore() );
		return defaultTokenServices;
	}
}
