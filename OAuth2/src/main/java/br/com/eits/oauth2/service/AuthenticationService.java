package br.com.eits.oauth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.eits.oauth2.entity.User;
import br.com.eits.oauth2.repository.DAOUser;


@Component
public class AuthenticationService implements UserDetailsService {

	@Autowired
	DAOUser daoUser;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {

		User user = this.daoUser.findByEmailIgnoreCase(email);

		if (user == null) {
			System.out.println(email + " não encontrado");
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		
		return user;
	}

}
