package br.mil.mar.dabm.autenticador.application.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import br.mil.mar.dabm.autenticador.domain.repository.usuario.impl.UserDetailsServiceImpl;

/**
 * 
 * @author rodrigo
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	private AuthenticationManager authenticationManager;
	
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
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
			throws Exception {
		        
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setSaltSource(saltSource);
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder( passwordEncoder );
				
		authenticationManagerBuilder.authenticationProvider( authenticationProvider );
	}
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
			.addFilterAfter(new CsrfTokenGeneratorFilter(), CsrfFilter.class)
			.formLogin().loginPage("/authentication")
			.usernameParameter( "login" )
			.passwordParameter( "senha" ).permitAll()
		.and()
			.authorizeRequests().antMatchers( "/" , "/authentication", "/oauth/authorize", "/oauth/confirm_access").permitAll().and() //TODO lembrar de bloquear '/'
			.requestMatchers().antMatchers("/", "/authentication", "/oauth/authorize", "/oauth/confirm_access")
		.and()
			.authorizeRequests().anyRequest().authenticated();
	}
	
	public final class CsrfTokenGeneratorFilter extends OncePerRequestFilter {
	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
	        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
	 
	        // Spring Security will allow the Token to be included in this header name
	        response.setHeader("X-CSRF-HEADER", token.getHeaderName());
	 
	        // Spring Security will allow the token to be included in this parameter name
	        response.setHeader("X-CSRF-PARAM", token.getParameterName());
	 
	        // this is the value of the token to be included as either a header or an HTTP parameter
	        response.setHeader("X-CSRF-TOKEN", token.getToken());
	        
	        Cookie cookie = new Cookie("X-CSRF-TOKEN", token.getToken());
			
	        response.addCookie(cookie);
	 
	        filterChain.doFilter(request, response);
	    }
	}
}
