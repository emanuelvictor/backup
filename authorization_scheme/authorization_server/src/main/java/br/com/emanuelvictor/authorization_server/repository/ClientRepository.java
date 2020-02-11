package br.com.emanuelvictor.authorization_server.repository;

import br.com.emanuelvictor.authorization_server.entity.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.transaction.Transactional;

@Transactional
public interface ClientRepository extends JpaRepository<Client, String> {

}
