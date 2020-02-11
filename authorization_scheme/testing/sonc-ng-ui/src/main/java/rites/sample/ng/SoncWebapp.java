package rites.sample.ng;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.security.oauth2.sso.EnableOAuth2Sso;
import org.springframework.cloud.security.oauth2.sso.OAuth2SsoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.ModelAndView;
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
	
//	@Component
//	@Qualifier("mySsoLogoutHandler")
	public class MySsoLogoutHandler implements LogoutHandler {

	    @Value("${my.oauth.server.schema}://${my.oauth.server.host}:${my.oauth.server.port}/oauth2AuthorizationServer/invalidateToken")
	    String logoutUrl;

	    @Override
	    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {

	        System.out.println("executing MySsoLogoutHandler.logout");
	        Object details = authentication.getDetails();
	        if (details.getClass().isAssignableFrom(OAuth2AuthenticationDetails.class)) {

	            String accessToken = ((OAuth2AuthenticationDetails)details).getTokenValue();
	            System.out.println("token: {} " + accessToken);

	            RestTemplate restTemplate = new RestTemplate();

	            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	            params.add("access_token", accessToken);

	            HttpHeaders headers = new HttpHeaders();
	            headers.add("Authorization", "bearer " + accessToken);

	            HttpEntity<String> request = new HttpEntity(params, headers);

	            HttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
	            HttpMessageConverter stringHttpMessageConverternew = new StringHttpMessageConverter();
	            restTemplate.setMessageConverters(Arrays.asList(new HttpMessageConverter[]{formHttpMessageConverter, stringHttpMessageConverternew}));
	            try {
	                ResponseEntity<String> response = restTemplate.exchange(logoutUrl, HttpMethod.POST, request, String.class);
	            } catch(HttpClientErrorException e) {
	            	System.err.println("HttpClientErrorException invalidating token with SSO authorization server. response.status code: {}, server URL: {}" + e.getStatusCode() + logoutUrl);
	            }
	        }


	    }
	}
	
	@Configuration
	protected static class SecurityConfiguration extends OAuth2SsoConfigurerAdapter {

		
//		@Component
//		@Qualifier("mySsoLogoutHandler")
		public class MySsoLogoutHandler implements LogoutHandler {

//		    @Value("${my.oauth.server.schema}://${my.oauth.server.host}:${my.oauth.server.port}/oauth2AuthorizationServer/invalidateToken")
		    String logoutUrl = "http://localhost:8080/auth-engine/invalidateToken";

		    @Override
		    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {

		        System.out.println("executing MySsoLogoutHandler.logout");
		        Object details = authentication.getDetails();
		        if (details.getClass().isAssignableFrom(OAuth2AuthenticationDetails.class)) {

		            String accessToken = ((OAuth2AuthenticationDetails)details).getTokenValue();
		            System.out.println("token: {} " + accessToken);

		            RestTemplate restTemplate = new RestTemplate();

		            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		            params.add("access_token", accessToken);

		            HttpHeaders headers = new HttpHeaders();
		            headers.add("Authorization", "bearer " + accessToken);

		            HttpEntity<String> request = new HttpEntity(params, headers);

		            HttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
		            HttpMessageConverter stringHttpMessageConverternew = new StringHttpMessageConverter();
		            restTemplate.setMessageConverters(Arrays.asList(new HttpMessageConverter[]{formHttpMessageConverter, stringHttpMessageConverternew}));
		            try {
		                ResponseEntity<String> response = restTemplate.exchange(logoutUrl, HttpMethod.POST, request, String.class);
		            } catch(HttpClientErrorException e) {
		            	System.err.println("HttpClientErrorException invalidating token with SSO authorization server. response.status code: {}, server URL: {}" + e.getStatusCode() + logoutUrl);
		            }
		        }


		    }
		}
		
		
		
//		@Autowired
//		MySsoLogoutHandler mySsoLogoutHandler;

		@Override
		public void configure(HttpSecurity http) throws Exception {
			
		
			http/*.sessionManagement()..logout().addLogoutHandler(new LogoutHandler() {
				@Override
				public void logout(HttpServletRequest request,o
								   HttpServletResponse response,
								   Authentication authentication) {
					authentication.setAuthenticated(false);
					authentication = null;
				}
			}).and()*/.logout()
            .logoutSuccessUrl("/")
            // using this antmatcher allows /logout from GET without csrf as indicated in
            // https://docs.spring.io/spring-security/site/docs/current/reference/html/csrf.html#csrf-logout
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            // this LogoutHandler invalidate user token from SSO
            .addLogoutHandler(new MySsoLogoutHandler())
    .and().
					antMatcher("/**").
				authorizeRequests().
				antMatchers("/index.html", "/home.html", "/","/login").
					permitAll().
				and().
					csrf().disable() /*.csrfTokenRepository(csrfTokenRepository()).and()*/;
//				csrfTokenRepository(csrfTokenRepository()).
//				and().addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
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
							cookie.setHttpOnly(false);
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
		public RemoteTokenServices remoteTokenServices(final @Value("http://localhost:8080/auth-engine/oauth/check_token/") String checkTokenUrl,
													   final @Value("sonc") String clientId,
													   final @Value("123456") String clientSecret) {
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

		
		@RequestMapping("/login")
		public String login() {
			return "login";
		}

		@RequestMapping("/user")
		@ResponseBody
		public Principal user(Principal user) {
			return user;
		}

		@RequestMapping("/adminresource")
		@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
		public Object adminResource(Principal user) {
			return new StringBuffer("Your user is " + user.getName() + " and you have the authority 'ADMINISTRATOR'");
		}

		@RequestMapping(value = "/userresource")
		@PreAuthorize("hasRole('ROLE_PUBLIC') or hasRole('ROLE_ADMINISTRATOR')")
		public Object userResource(Principal user) {
			return new StringBuffer("Your user is " + user.getName() + " and you have the authority 'PUBLIC'");
		}
		 /**
		 * 
		 */
		@Autowired
		private OAuth2ClientContext oAuth2ClientContext;

		/**
		 * 
		 */
		@Autowired
		private AuthorizationCodeResourceDetails authorizationCodeResourceDetails;
		
//		@Autowired
//		private ClientCredentialsResourceDetails clientCredentialsResourceDetails;
		
		//TODO será logout
		@RequestMapping(value = "/revoke")
		@PreAuthorize("hasRole('ROLE_PUBLIC') or hasRole('ROLE_ADMINISTRATOR')")
		public ModelAndView revoke(HttpServletRequest request, HttpServletResponse response) {

			
			final OAuth2RestTemplate template = new OAuth2RestTemplate( authorizationCodeResourceDetails, oAuth2ClientContext );
			template.delete( "http://localhost:8080/auth-engine/api/revoke?token={token}", template.getAccessToken().getValue() );
			return new ModelAndView("redirect:http://localhost:8080/auth-engine/login");
			
////			final OAuth2RestTemplate template = new OAuth2RestTemplate( authorizationCodeResourceDetails, oAuth2ClientContext );
//			
//			final OAuth2RestTemplate template = new OAuth2RestTemplate( authorizationCodeResourceDetails, oAuth2ClientContext );
//			template.delete( "http://localhost:8080/auth-engine/api/revoke?token={token}", template.getAccessToken().getValue() );
//			
////			SecurityContextHolder.clearContext();
//
//			System.out.println("Deslogando "/*+SecurityContextHolder.getContext().getAuthentication().getName()*/);
//
////			SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // concern you
//
////		    User currUser = user; // some of DAO or Service...
//
//		    SecurityContextLogoutHandler ctxLogOut = new SecurityContextLogoutHandler(); // concern you
//
////		    if( currUser == null ){
//		        ctxLogOut.logout(request, response, auth); // concern you
////		    }
//			
//			
////			HttpSession session= request.getSession(false);
////		    SecurityContextHolder.clearContext();
////		         session= request.getSession(false);
////		        if(session != null) {
////		            session.invalidate();
////		        }
////		        for(Cookie cookie : request.getCookies()) {
////		            cookie.setMaxAge(0);
////		        }
//
//			
//			return "deslogado";
		}
//
//		@Bean
//		public OAuth2RestOperations restTemplate(OAuth2ClientContext oauth2ClientContext) {
//			return new OAuth2RestTemplate(resource(), oauth2ClientContext);
//		}
//
//		@Bean
//		protected OAuth2ProtectedResourceDetails resource() {
//			AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
//			resource.setAccessTokenUri("http://localhost:8080/oauth/token");
//			resource.setUserAuthorizationUri("http://localhost:8080/oauth/authorize");
//			resource.setClientId("client1");
//			resource.setClientSecret("123456");
//			resource.setScope(Arrays.asList("ADMINISTRATOR", "PUBLIC"));
//			return resource;
//		}


	}

	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		return new OAuth2MethodSecurityExpressionHandler();
	}


}
