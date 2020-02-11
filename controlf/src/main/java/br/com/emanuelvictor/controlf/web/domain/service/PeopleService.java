package br.com.emanuelvictor.controlf.web.domain.service;

import br.com.emanuelvictor.controlf.web.domain.entity.master.user.People;
import br.com.emanuelvictor.controlf.web.domain.repository.IPeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by emanuelvictor on 05/04/16.
 */
@Service
public class PeopleService implements UserDetailsService {

    @Autowired
    IPeopleRepository peopleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.print("ASDFASDF");
        return peopleRepository.findByUsername(username);
    }
}
