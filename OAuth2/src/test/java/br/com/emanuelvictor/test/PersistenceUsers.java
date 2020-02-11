package br.com.emanuelvictor.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.eits.oauth2.entity.User;
import br.com.eits.oauth2.repository.DAOUser;

@TransactionConfiguration
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class PersistenceUsers {


    @Autowired
    DAOUser daoUser;

    @Test
    @Rollback(false)
    @Transactional
    public void saveSuccess() {

        System.out.println("Testando a integração com o banco");


        User user1 = new User();
        user1.setPassword(new BCryptPasswordEncoder().encode("123456"));
        user1.setName("Rodrigo");
        user1.setEmail("rodrigo@eits.com.br");
        daoUser.save(user1);

        User user2 = new User();
        user2.setName("Emanuel");
        user2.setPassword(new BCryptPasswordEncoder().encode("123456"));
        user2.setEmail("emanuel.fonseca@eits.com.br");
        daoUser.save(user2);

        User user3 = new User();
        user3.setName("Fulano");
        user3.setPassword(new BCryptPasswordEncoder().encode("123456"));
        user3.setEmail("fulano@gmail.com");
        daoUser.save(user3);

        assertTrue(true);

    }




}