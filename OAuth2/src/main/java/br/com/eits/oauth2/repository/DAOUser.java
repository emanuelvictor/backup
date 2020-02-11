package br.com.eits.oauth2.repository;

import br.com.eits.oauth2.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DAOUser extends JpaRepository<User, Long> {

	@Transactional(readOnly = true)
	public User findByEmailIgnoreCase(String email);


}