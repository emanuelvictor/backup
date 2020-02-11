package br.com.emanuelvictor.authorization_server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.overcoming.User;

import br.com.emanuelvictor.authorization_server.entity.user.UserAccount;
import br.com.emanuelvictor.authorization_server.service.UserAccountService;

@RestController
public class UserAccountController {

	@Autowired
	UserAccountService userAccountService;

	@RequestMapping(value = "/api/user")
	public User principal() {
		User user = new User();
		user.setUsername("admin");
		user.setUsername("adminpwd");
		return user;
	}

	@RequestMapping(value = "/api/accounts", method = RequestMethod.POST)
	public UserAccount postEmployee(@RequestBody UserAccount userAccount) {
		return this.userAccountService.save(userAccount);
	}

	@RequestMapping(value = "/api/accounts", method = RequestMethod.GET)
	public List<UserAccount> getEmployees() {
		// System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
		return this.userAccountService.find();
	}

	@RequestMapping(value = "/api/accounts/{email}", method = RequestMethod.GET)
	public UserAccount getEmployee(@PathVariable("email") String email) {
		return this.userAccountService.find(email);
	}

	@RequestMapping(value = "/api/accounts/{email}", method = RequestMethod.DELETE)
	public void deleteEmployee(@PathVariable("email") String email) {
		this.userAccountService.delete(email);
	}

	// //Using by authentication service
	// @RequestMapping(value = "/revoke", method = RequestMethod.GET)
	// public String user() {
	//
	//
	//
	// final OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
	//
	// if (accessToken.getRefreshToken() != null)
	// {
	// this.tokenStore.removeRefreshToken(accessToken.getRefreshToken());
	// }
	//
	// this.tokenStore.removeAccessToken(accessToken);
	//
	//// System.out.println("Deslogando " + authentication.getName());
	//
	// return SecurityContextHolder.getContext().getAuthentication().getName();
	// }

	@Autowired
	private TokenStore tokenStore;
	// /**
	// *
	// */
	// @Autowired
	// private TokenService tokenService;

	// LOGOUT
	/**
	 * @return
	 */
	@RequestMapping(value = "/api/revoke", method = RequestMethod.DELETE)
	public void logout(@RequestParam(required = true) String token, HttpServletRequest request,
			HttpServletResponse response) {

		// OAuth2Authentication oauth = tokenStore.readAuthentication(token);
		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication(); // concern
		// // you
		// SecurityContextLogoutHandler ctxLogOut = new
		// SecurityContextLogoutHandler(); // concern
		// ctxLogOut.logout(request, response, auth);
		// if (oauth != null)
		// {
		// new SecurityContextLogoutHandler().logout(request, response, oauth);
		// }
		// oauth.setAuthenticated(false);
		// final Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		// if (auth != null)
		// {
		// new SecurityContextLogoutHandler().logout(request, response, auth);
		// }
		// SecurityContextHolder.getContext().setAuthentication(null);
		// auth.setAuthenticated(false); // you

		final OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
		//
		// DefaultTokenServices defaultTokenServices = new
		// DefaultTokenServices();
		// defaultTokenServices.setTokenStore(tokenStore);
		// defaultTokenServices.revokeToken(token);
		// System.out.println("Token " + token + " revogado");
		//
		if (accessToken.getRefreshToken() != null) {
			this.tokenStore.removeRefreshToken(accessToken.getRefreshToken());
		}

		this.tokenStore.removeAccessToken(accessToken);
		// tokenService.revokeToken(token);
		// tokenService.revokeToken(token);
		// DefaultTokenServices defaultTokenServices = new
		// DefaultTokenServices();
		// defaultTokenServices.setTokenStore(tokenStore);
		// defaultTokenServices.revokeToken(token);
		// System.out.println("Token " + token + " revogado");
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "{'test':'test'}";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String helloWorld() {
		return "{'hello':'world'}";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SecurityContextLogoutHandler ctxLogOut = new SecurityContextLogoutHandler();
		ctxLogOut.logout(request, response, auth);
	}
	
	@RequestMapping(value="/invalidateToken", method= RequestMethod.POST)
    @ResponseBody
    public Map<String, String> logout(@RequestParam("access_token") String accessToken) {
        System.out.println("Invalidando token " + accessToken);
        
        OAuth2AccessToken accessTokenAsdf = tokenStore.readAccessToken(accessToken);
        tokenStore.removeAccessToken(accessTokenAsdf);
        		
        Map<String, String> ret = new HashMap<>();
        ret.put("access_token", accessToken);
        return ret;
    }

}
