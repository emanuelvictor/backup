package br.com.emanuelvictor.funcionario2.controller;

import br.com.emanuelvictor.funcionario2.entity.application.Application;
import br.com.emanuelvictor.funcionario2.service.ServiceApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerApplication {

    @Autowired
    ServiceApplication serviceApplication;

    @RequestMapping(value = "/applications", method = RequestMethod.POST)
    public
    @ResponseBody
    Application postApplication(@RequestBody Application application) {
        return this.serviceApplication.save(application);
    }

    @RequestMapping(value = "/applications", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Application> getApplications() {
        return this.serviceApplication.find();
    }

    @RequestMapping(value = "/applications/{clientId}", method = RequestMethod.GET)
    public
    @ResponseBody
    Application getApplication(@PathVariable("clientId") String clientId) {
        return this.serviceApplication.find(clientId);
    }

    @RequestMapping(value = "/applications/{clientId}", method = RequestMethod.DELETE)
    public void deleteApplication(@PathVariable("clientId") String clientId) {
        this.serviceApplication.delete(clientId);
    }

}
