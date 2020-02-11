package br.com.emanuelvictor.controlf.web.domain.service;

import br.com.emanuelvictor.controlf.web.domain.repository.IClientRepository;
import com.sun.deploy.util.SessionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * TODO service example
 * Created by emanuelvictor on 05/04/16.
 */
@Service
public class ClientService implements ClientDetailsService {

    @Autowired
    IClientRepository clientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        System.out.print("ASDFASDF");
        return clientRepository.findByClientId(clientId);
    }
}
