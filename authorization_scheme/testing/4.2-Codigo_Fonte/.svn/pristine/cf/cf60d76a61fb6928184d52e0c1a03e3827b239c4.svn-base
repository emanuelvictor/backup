package br.mil.mar.dabm.autenticador.application.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 
 * @author rodrigo
 */
@Component("corsServletFilter")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSServletFilter implements Filter
{
	/**
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain ) throws IOException, ServletException
	{
		final HttpServletRequest httpRequest = ( HttpServletRequest ) servletRequest;
		final HttpServletResponse httpResponse = ( HttpServletResponse ) servletResponse;

		httpResponse.setHeader( "Access-Control-Allow-Origin", "*" );
		httpResponse.setHeader( "Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE" );
		httpResponse.setHeader( "Access-Control-Allow-Headers", "x-requested-with" );
		httpResponse.setHeader( "Access-Control-Max-Age", "3600" );

		if ( !httpRequest.getMethod().equals( "OPTIONS" ) )
		{
			chain.doFilter( servletRequest, servletResponse );
		}
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init( FilterConfig filterConfig ) throws ServletException
	{

	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy()
	{

	}
}
