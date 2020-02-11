package br.com.emanuelvictor.controlf.web.application.config.multitenant;

import br.com.emanuelvictor.controlf.web.domain.entity.master.user.People;
import br.com.emanuelvictor.controlf.web.domain.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Created by emanuelvictor on 06/04/16.
 */

@Component
public class TenantIdentifierInterceptorAdapter extends HandlerInterceptorAdapter {

    @Autowired
    private PeopleService peopleService;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        if (req.getUserPrincipal() != null) {
            People people = (People) peopleService.loadUserByUsername(req.getUserPrincipal().getName());

                // Set the company key as tenant identifier
                req.setAttribute("X-TenantID", "schema_" + people.getId());
        }
        return true;
    }
}
