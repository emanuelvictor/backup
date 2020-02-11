/**
 *
 */
package br.com.emanuelvictor.authorization_server.service;


import br.com.emanuelvictor.authorization_server.entity.client.Client;
import br.com.emanuelvictor.authorization_server.repository.ClientRepository;
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
public class ClientService implements ClientDetailsService {


    @Autowired
    ClientRepository clientRepository;

    public Client save(Client client) {
        return this.clientRepository.save(client);
    }

    public List<Client> find() {
        return this.clientRepository.findAll();
    }

    public Client find(String clientId) {
        return this.clientRepository.findOne(clientId);
    }

    public void delete(String clientId) {
        this.clientRepository.delete(clientId);
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        System.out.println("Find client by clientId " + clientId);
        ClientDetails client = clientRepository.findOne(clientId);
        if (client == null) {
            System.out.println("Client with clientId " + clientId + " not found") ;
            throw new UsernameNotFoundException(String.format("Client %s does not exist!", clientId));
        }
        return client;
    }


}
