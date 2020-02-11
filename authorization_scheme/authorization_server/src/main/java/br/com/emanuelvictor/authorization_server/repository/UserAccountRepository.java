package br.com.emanuelvictor.authorization_server.repository;

import br.com.emanuelvictor.authorization_server.entity.user.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

	UserAccount findByEmailIgnoreCase(String email);
	
}
