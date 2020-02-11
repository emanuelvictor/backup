package br.com.pumatronix.autenticador.client.application.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.overcoming.User;

@RestController
public class NavigationController {
	
	@Value("${oauth.resource:http://localhost:8080/auth-engine}")
	private String baseUrl/* = "http://localhost:8082/client"*/;	
	
	@Autowired
	private OAuth2RestOperations restTemplate;
	
//	@RequestMapping("/")
//	public User home() 
//	{
//		return this.restTemplate.getForObject(baseUrl + "/api/user", User.class);
//	}

	@RequestMapping("/")
	public User home() 
	{
		return this.restTemplate.getForObject(baseUrl + "/api/user", User.class);
	}
	
	@RequestMapping("/api/now")
	public String now() 
	{
		return new Date().toString();
	}
	
	@RequestMapping("/api/user")
	public User user() 
	{
		User user = new User();
		user.setUsername("admin");
		user.setUsername("adminpwd");
		return user;
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
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/revoke")
	@PreAuthorize("hasRole('ROLE_PUBLIC') or hasRole('ROLE_ADMINISTRATOR')")
	public ModelAndView revoke(/*HttpServletRequest request, HttpServletResponse response*/) {

		
		final OAuth2RestTemplate template = new OAuth2RestTemplate( authorizationCodeResourceDetails, oAuth2ClientContext );
		template.delete( "http://localhost:8080/auth-engine/api/revoke?token={token}", template.getAccessToken().getValue() );
		return new ModelAndView("redirect:http://localhost:8080/auth-engine/login");
		
////		final OAuth2RestTemplate template = new OAuth2RestTemplate( authorizationCodeResourceDetails, oAuth2ClientContext );
//		
//		final OAuth2RestTemplate template = new OAuth2RestTemplate( authorizationCodeResourceDetails, oAuth2ClientContext );
//		template.delete( "http://localhost:8080/auth-engine/api/revoke?token={token}", template.getAccessToken().getValue() );
//		
////		SecurityContextHolder.clearContext();
//
//		System.out.println("Deslogando "/*+SecurityContextHolder.getContext().getAuthentication().getName()*/);
//
////		SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // concern you
//
////	    User currUser = user; // some of DAO or Service...
//
//	    SecurityContextLogoutHandler ctxLogOut = new SecurityContextLogoutHandler(); // concern you
//
////	    if( currUser == null ){
//	        ctxLogOut.logout(request, response, auth); // concern you
////	    }
//		
//		
////		HttpSession session= request.getSession(false);
////	    SecurityContextHolder.clearContext();
////	         session= request.getSession(false);
////	        if(session != null) {
////	            session.invalidate();
////	        }
////	        for(Cookie cookie : request.getCookies()) {
////	            cookie.setMaxAge(0);
////	        }
//
//		
//		return "deslogado";
	}
}
