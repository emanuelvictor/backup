package br.com.emanuelvictor.funcionario2;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.emanuelvictor.funcionario2.controller.ControllerNavigation;
import org.springframework.stereotype.Component;
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