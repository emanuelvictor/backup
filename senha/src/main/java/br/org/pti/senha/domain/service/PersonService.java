package br.org.pti.senha.domain.service;

import br.org.pti.senha.domain.repository.PersonRepo;
import br.org.pti.senha.domain.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private PersonRepo personRepo;

    private UserRepo userRepo;

    @Autowired
    public PersonService(final PersonRepo personRepo, final UserRepo userRepo) {
        this.personRepo = personRepo;
        this.userRepo = userRepo;
    }


    public List<String> getAllPersonNames() {
        return this.personRepo.getAllPersonNames();
    }

    public List<String> getAllUserNames() {
        return this.userRepo.getAllPersonNames();
    }
}
