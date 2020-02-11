package br.com.eits.oauth2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.eits.oauth2.entity.User;

@Controller
public class NavigationController {

	@ResponseBody
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Object index() {
		return new User();
	}

}
