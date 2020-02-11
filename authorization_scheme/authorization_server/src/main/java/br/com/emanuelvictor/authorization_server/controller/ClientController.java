package br.com.emanuelvictor.authorization_server.controller;

import br.com.emanuelvictor.authorization_server.entity.client.Client;
import br.com.emanuelvictor.authorization_server.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public Client postApplication(@RequestBody Client client) {
        return this.clientService.save(client);
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public List<Client> getApplications() {
        return this.clientService.find();
    }

    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.GET)
    public
    @ResponseBody
    Client getApplication(@PathVariable("clientId") String clientId) {
        return this.clientService.find(clientId);
    }

    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.DELETE)
    public void deleteApplication(@PathVariable("clientId") String clientId) {
        this.clientService.delete(clientId);
    }

}
