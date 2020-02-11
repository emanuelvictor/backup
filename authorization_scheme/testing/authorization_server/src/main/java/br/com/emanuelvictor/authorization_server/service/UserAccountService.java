package br.com.emanuelvictor.authorization_server.service;

import br.com.emanuelvictor.authorization_server.entity.user.UserAccount;
import br.com.emanuelvictor.authorization_server.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountService implements UserDetailsService {

	@Autowired
	UserAccountRepository userAccountRepository;
	
	public UserAccount save(UserAccount userAccount) {

        if (userAccount.getPassword() == null) {
			userAccount.setPassword(this.userAccountRepository.findOne(userAccount.getEmail()).getPassword());
		} else {
            userAccount.setPassword(new ShaPasswordEncoder().encodePassword(userAccount.getPassword(), null));
        }
        return this.userAccountRepository.save(userAccount);
    }
	
	public List<UserAccount> find() {
		return this.userAccountRepository.findAll();
	}
	
	public UserAccount find(String email){
		return this.userAccountRepository.findOne(email);
	}
	
	public void delete(String email){
		this.userAccountRepository.delete(email);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		System.out.println("Find userAccount by email: " + email);
		UserAccount userAccount = userAccountRepository.findByEmailIgnoreCase(email);
		if (userAccount == null) {
			System.out.println("UserAccount with email " + email + " not found");
			throw new UsernameNotFoundException(String.format("UserAccount %s does not exist!", email));
		}
		return userAccount;
	}

}
