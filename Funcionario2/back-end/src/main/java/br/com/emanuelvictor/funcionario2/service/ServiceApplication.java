/**
 *
 */
package br.com.emanuelvictor.funcionario2.service;


import br.com.emanuelvictor.funcionario2.entity.application.Application;
import br.com.emanuelvictor.funcionario2.repository.DAOApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author emanuelvictor --> Service que fará a autenticação
 */
@Service
public class ServiceApplication implements ClientDetailsService {


    @Autowired
    DAOApplication daoApplication;

    public Application save(Application application) {
        return this.daoApplication.save(application);
    }

    public List<Application> find() {
        return this.daoApplication.findAll();
    }

    public Application find(String clientId) {
        return this.daoApplication.findOne(clientId);
    }

    public void delete(String clientId) {
        this.daoApplication.delete(clientId);
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        System.out.println("Procurando por aplicativo " + clientId);
        ClientDetails client = daoApplication.findOne(clientId);
        if (client == null) {
            System.out.println("Aplicativo " + clientId + " não encontrado") ;
            throw new UsernameNotFoundException(String.format("User %s does not exist!", clientId));
        }
        return client;
    }


}
