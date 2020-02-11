/**
 *
 */
package br.com.emanuelvictor.funcionario2.service;

import br.com.emanuelvictor.funcionario2.entity.user.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

/**
 * @author emanuelvictor TODO não tem nada ainda
 */
@Service
public class ServiceToken {

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

    public Employee getPrincipal(String token) {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore);
        OAuth2Authentication oAuth2Authentication = defaultTokenServices.loadAuthentication(token);

        Employee employee = (Employee) oAuth2Authentication.getUserAuthentication().getPrincipal();

        return (Employee) oAuth2Authentication.getUserAuthentication().getPrincipal();
    }
}
