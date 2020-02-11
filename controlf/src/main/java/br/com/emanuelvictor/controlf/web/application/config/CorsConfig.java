package br.com.emanuelvictor.controlf.web.application.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by emanuelvictor on 05/04/16.
 */
@Component
public class CorsConfig implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,x-requested-with,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");

        if (((HttpServletRequest) req).getMethod()!="OPTIONS"){
            chain.doFilter(req, res);
        }

    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}

}