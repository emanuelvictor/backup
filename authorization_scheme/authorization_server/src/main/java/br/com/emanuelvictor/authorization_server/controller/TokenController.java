package br.com.emanuelvictor.authorization_server.controller;

import br.com.emanuelvictor.authorization_server.entity.user.UserAccount;
import br.com.emanuelvictor.authorization_server.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by emanuelvictor on 08/04/15.
 */
@Controller
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/oauth/revoke/{token}", method = RequestMethod.DELETE)
    public @ResponseBody Object revokeToken(@PathVariable("token") String token) throws InvalidClientException
    {
        return tokenService.revokeToken(token);
    }

    @RequestMapping(value = "/oauth/principal/{token}", method = RequestMethod.GET)
    public @ResponseBody
    UserAccount getPrincipal(@PathVariable("token") String token) throws InvalidClientException
    {
        return tokenService.getPrincipal(token);
    }
}
