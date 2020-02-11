package br.com.eits.oauth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.eits.oauth2.entity.User;
import br.com.eits.oauth2.repository.DAOUser;

@Service("serviceUser")
@Transactional
public class ServiceUser implements UserDetailsService, ClientDetailsService {

	
    @Autowired
    DAOUser daoUser;
    
    @Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
    	System.out.println(userName);
    	System.out.println("loadUserByUsername");
		return new User();
	}

	@Override
	public ClientDetails loadClientByClientId(String clientId)
			throws ClientRegistrationException {
		// TODO Auto-generated method stub
		System.out.println("loadClientByClientId");
		return null;
	}
    
    
//
//    public User save(User user) {
//
//        if (user.getPassword() == null) {
//
//            user.setPassword(this.daoUser.findOne(user.getId()).getPassword());
//        } else {
//
//            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
//        }
//        return this.daoUser.save(user);
//    }
//
//    public void destroy(Long id) {
//        daoUser.delete(id);
//    }
//
//    public List<User> find() {
//        return daoUser.findAll();
//    }
//
//    public User find(Long id) {
//        return daoUser.findOne(id);
//    }
//
//
//    public User getCurrentUser() {
//        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    }

}