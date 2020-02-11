/**
 * 
 */
package br.com.emanuelvictor.funcionario2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author emanuelvictor --> Configuração do servidor de recursos (cliente)
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	// Configura a permissão das requisições
	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/", "/app/**", "/bower_components/**", "/webjars/**").permitAll()
				.anyRequest().authenticated()
				.and().formLogin().loginPage("/login");
	}

}