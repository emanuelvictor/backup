package br.com.emanuelvictor.authorization_server.controller;

import br.com.emanuelvictor.authorization_server.entity.user.UserAccount;
import br.com.emanuelvictor.authorization_server.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import java.security.Principal;
import java.util.List;

@RestController
public class UserAccountController {

    @Autowired
    UserAccountService userAccountService;

    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public UserAccount postEmployee(@RequestBody UserAccount userAccount) {
        return this.userAccountService.save(userAccount);
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public List<UserAccount> getEmployees() {
        return this.userAccountService.find();
    }

    @RequestMapping(value = "/accounts/{email}", method = RequestMethod.GET)
    public UserAccount getEmployee(@PathVariable("email") String email) {
        return this.userAccountService.find(email);
    }

    @RequestMapping(value = "/accounts/{email}", method = RequestMethod.DELETE)
    public void deleteEmployee(@PathVariable("email") String email) {
        this.userAccountService.delete(email);
    }


    //Using by authentication service
//    @RequestMapping("/user")
//    @ResponseBody
//    public Principal user(Principal user) {
//        return user;
//    }


}
