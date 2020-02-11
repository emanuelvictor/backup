/**
 *
 */
package br.com.emanuelvictor.authorization_server.service;

import br.com.emanuelvictor.authorization_server.entity.user.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

/**
 * @author emanuelvictor TODO não tem nada ainda
 */
@Service
public class TokenService {

    @Autowired
    private TokenStore tokenStore;

    //TODO retornar um redirect
    public StringBuilder revokeToken(String token) {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore);
        defaultTokenServices.revokeToken(token);
        System.out.println("Token " + token + " revogado");
        return new StringBuilder("Token " + token + " revogado");
    }

    public UserAccount getPrincipal(String token) {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore);
        OAuth2Authentication oAuth2Authentication = defaultTokenServices.loadAuthentication(token);

        UserAccount userAccount = (UserAccount) oAuth2Authentication.getUserAuthentication().getPrincipal();

        return (UserAccount) oAuth2Authentication.getUserAuthentication().getPrincipal();
    }
}
