package br.org.pti.senha.domain;

import br.org.pti.senha.AbstractIntegrationTests;
import br.org.pti.senha.domain.service.PersonService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 */
public class UserServiceIntegrationTests extends AbstractIntegrationTests
{

    /**
     *
     */
    @Autowired
    private PersonService personService;

    /**
     *
     */
    @Test
    public void getAllPersonNamesTestMustPass() {
        final List<String> names = personService.getAllPersonNames();
        LOGGER.info("names: " + names);
    }

    /**
     *
     */
    @Test
    public void getAllUserNamesTestMustPass() {
        final List<String> names = personService.getAllUserNames();
        LOGGER.info("names: " + names);
    }
}
