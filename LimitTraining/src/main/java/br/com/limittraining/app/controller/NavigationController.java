package br.com.limittraining.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by emanuelvictor on 04/02/15.
 */
@Controller
public class NavigationController {



    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public @ResponseBody StringBuffer index() {
        System.out.print("test");
        return new StringBuffer("app/index.html");
    }

}
