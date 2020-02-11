package br.com.emanuelvictor.authorization_server.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.emanuelvictor.authorization_server.entity.client.Client;

@Transactional
public interface ClientRepository extends JpaRepository<Client, String> {

}
