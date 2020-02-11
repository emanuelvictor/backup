package br.com.emanuelvictor.funcionario2.repository;

import br.com.emanuelvictor.funcionario2.entity.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.transaction.Transactional;

@Transactional
public interface DAOApplication extends JpaRepository<Application, String> {

    ClientDetails findByClientId(String clientId);
//	@Transactional(readOnly = true)
//	public Employee findByEmailIgnoreCase(String email);
}
