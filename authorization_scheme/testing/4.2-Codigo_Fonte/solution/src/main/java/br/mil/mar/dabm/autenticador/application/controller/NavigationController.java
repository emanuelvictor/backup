package br.mil.mar.dabm.autenticador.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author rodrigo
 */
@Controller
public class NavigationController
{
	/*-------------------------------------------------------------------
	 * 		 					CONTROLLERS
	 *-------------------------------------------------------------------*/
//	/**
//	 * 
//	 * @return
//	 */
//	@RequestMapping("/")
//	@ResponseBody public Object /*ModelAndView*/ home()
//	{
//		return new StringBuffer("Sei lá rolou"); //new ModelAndView( "modules/autenticador/views/index.jsp" );
//	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/")
	@ResponseBody public ModelAndView home()
	{
		return new ModelAndView( "modules/autenticador/views/index.jsp" );
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/authentication")
	public ModelAndView authentication()
	{
		return new ModelAndView( "modules/autenticacao/views/login.jsp" );
	}
}
