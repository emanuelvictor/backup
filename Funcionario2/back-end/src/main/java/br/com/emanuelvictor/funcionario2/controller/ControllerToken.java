package br.com.emanuelvictor.funcionario2.controller;

import br.com.emanuelvictor.funcionario2.entity.user.Employee;
import br.com.emanuelvictor.funcionario2.service.ServiceToken;
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
public class ControllerToken {

    @Autowired
    private ServiceToken serviceToken;

    @RequestMapping(value = "/oauth/revoke/{token}", method = RequestMethod.DELETE)
    public @ResponseBody Object revokeToken(@PathVariable("token") String token) throws InvalidClientException
    {
        return serviceToken.revokeToken(token);
    }

    @RequestMapping(value = "/oauth/principal/{token}", method = RequestMethod.GET)
    public @ResponseBody Employee getPrincipal(@PathVariable("token") String token) throws InvalidClientException
    {
        return serviceToken.getPrincipal(token);
    }
}
