package br.com.emanuelvictor.controlf.web.application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by emanuelvictor on 05/04/16.
 */
@RestController
public class HomeController {

    /**
     *
     * @return
     */
    @RequestMapping("/")
    public @ResponseBody StringBuffer helloWorld(){
        return new StringBuffer("Hello world");
    }
}
