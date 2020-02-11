package br.com.emanuelvictor.controlf.web.domain.repository;

import br.com.emanuelvictor.controlf.web.domain.entity.master.client.Client;
import br.com.emanuelvictor.controlf.web.domain.entity.master.user.People;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by emanuelvictor on 05/04/16.
 */
public interface IClientRepository extends JpaRepository<Client, String> {

    Client findByClientId(String clientId);

}
