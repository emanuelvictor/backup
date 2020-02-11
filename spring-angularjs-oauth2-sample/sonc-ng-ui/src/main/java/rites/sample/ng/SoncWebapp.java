package rites.sample.ng;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.security.oauth2.sso.EnableOAuth2Sso;
import org.springframework.cloud.security.oauth2.sso.OAuth2SsoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

@Configuration
@EnableAutoConfiguration
//@RestController
@EnableOAuth2Sso
//@EnableOAuth2Client
@EnableGlobalMethodSecurity(prePostEnabled = true/*, proxyTargetClass = true*/)
public class SoncWebapp extends GlobalMethodSecurityConfiguration {

	public static void main(String[] args) {
		SpringApplication.run(SoncWebapp.class, args);
	}
	
	@Configuration
	protected static class SecurityConfiguration extends OAuth2SsoConfigurerAdapter {

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.logout().and()
					.antMatcher("/**").authorizeRequests()
					.antMatchers("/index.html", "/home.html", "/", "/login").permitAll()
					.and().csrf()
					.csrfTokenRepository(csrfTokenRepository()).and()
					.addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
		}

		private Filter csrfHeaderFilter() {
			return new OncePerRequestFilter() {
				@Override
				protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

					CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
					if (csrf != null) {
						Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
						String token = csrf.getToken();
						if (cookie == null || token != null && !token.equals(cookie.getValue())) {
							cookie = new Cookie("XSRF-TOKEN", token);
							cookie.setPath("/");
							response.addCookie(cookie);
						}
					}
					filterChain.doFilter(request, response);
				}
			};

		}

		@Bean
		public AccessTokenConverter accessTokenConverter() {
			return new DefaultAccessTokenConverter();
		}

		@Bean
		public RemoteTokenServices remoteTokenServices(final @Value("http://localhost:9080/auth-service/oauth/check_token/") String checkTokenUrl,
													   final @Value("soncrserv") String clientId,
													   final @Value("soncrserv") String clientSecret) {
			final RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
			remoteTokenServices.setCheckTokenEndpointUrl(checkTokenUrl);
			remoteTokenServices.setClientId(clientId);
			remoteTokenServices.setClientSecret(clientSecret);
			remoteTokenServices.setAccessTokenConverter(accessTokenConverter());
			return remoteTokenServices;
		}

		private CsrfTokenRepository csrfTokenRepository() {
			HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
			repository.setHeaderName("X-XSRF-TOKEN");
			return repository;
		}


	}

	@RestController
	protected static class Controller {

//		@RequestMapping("/")
//		public String home() {
//			return "home";
//		}
//
//		@RequestMapping("/login")
//		public String login() {
//			return "login";
//		}

		@RequestMapping("/user")
		@ResponseBody
		public Principal user(Principal user) {
            return user;
		}
//
//		@RequestMapping("/adminresource")
//		@PreAuthorize("hasRole('ROLE_ADMIN') and #oauth2.hasScope('read') or (!#oauth2.isOAuth() and hasRole('ROLE_ADMIN'))")
//		public String adminResource(Principal user) {
//			return "{\"id\":\"" + user.getName() + "\",\"content\":\"Hello World\"}";
//		}
//
//		@RequestMapping(value = "/userresource", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
//		@PreAuthorize("hasRole('ROLE_USER') and #oauth2.hasScope('read') or (!#oauth2.isOAuth() and hasRole('ROLE_USER'))")
//		public String userResource(Principal user) {
//			return "{\"id\":\"" + user.getName() + "\",\"content\":\"Hello World\"}";
//		}
	}

	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		return new OAuth2MethodSecurityExpressionHandler();
	}
}
