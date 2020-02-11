/**
 *
 */
package br.com.emanuelvictor.authorization_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author emanuelvictor
 *
 */
@Controller
public class NavigationController {

    @RequestMapping(value = "/login")
    public ModelAndView login()
    {
        return new ModelAndView( "/webapp/WEB-INF/jsp/login.jsp" );
    }

}
