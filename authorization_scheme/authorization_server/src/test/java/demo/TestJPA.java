package demo;


import br.com.emanuelvictor.authorization_server.Application;
import br.com.emanuelvictor.authorization_server.entity.client.Client;
import br.com.emanuelvictor.authorization_server.entity.client.GrantType;
import br.com.emanuelvictor.authorization_server.entity.position.Profile;
import br.com.emanuelvictor.authorization_server.entity.user.UserAccount;
import br.com.emanuelvictor.authorization_server.service.ClientService;
import br.com.emanuelvictor.authorization_server.service.ProfileService;
import br.com.emanuelvictor.authorization_server.service.UserAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TestJPA {

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    ClientService clientService;

    @Autowired
    ProfileService profileService;

    @Test
    @Rollback(value = false)
    public void populateBaseMustPass() {

        this.populateProfilesMustPass();
        this.saveAuthorizationServerMustPass();
        this.saveClient1MustPass();
        this.saveClient2MustPass();
        this.saveUserAuthorizationServerMustPass();
        this.saveUserClient1MustPass();
        this.saveUserClient2MustPass();
    }

    @Test
    @Rollback(value = false)
    public void populateProfilesMustPass() {

        Profile administratorAuthorizationServer = new Profile(1, "ADMINISTRATOR_AUTHORIZATION_SERVER");
        Profile pub = new Profile(2, "PUBLIC");
        Profile administrator = new Profile(3, "ADMINISTRATOR");
        Profile attendant = new Profile(4, "ATTENDANT");
        Profile manager = new Profile(5, "MANAGER");

        this.profileService.save(administratorAuthorizationServer);
        this.profileService.save(pub);
        this.profileService.save(administrator);
        this.profileService.save(attendant);
        this.profileService.save(manager);
    }

    @Test
    @Rollback(value = false)
    public void saveAuthorizationServerMustPass() {

        Client authorizationServer = new Client();

        authorizationServer.setClientId("authorization_server");
        authorizationServer.setClientSecret("123456");

        Profile administratorAuthorizationServer = this.profileService.find(1);
        Profile pub = this.profileService.find(2);

        authorizationServer.setProfiles(new ArrayList<Profile>(Arrays.asList(administratorAuthorizationServer, pub)));

        authorizationServer.getScope().forEach(description -> System.out.println(description));

        authorizationServer.setAccessTokenValiditySeconds(36000);
        authorizationServer.setRefreshTokenValiditySeconds(36000);

        authorizationServer.setAuthorizedGrantTypes(new HashSet<GrantType>(Arrays.asList(GrantType.PASSWORD, GrantType.REFRESH_TOKEN, GrantType.IMPLICIT, GrantType.AUTHORIZATION_CODE, GrantType.CLIENT_CREDENTIALS)));

        clientService.save(authorizationServer);
    }

    @Test
    @Rollback(value = false)
    public void saveClient1MustPass() {

        Client client1 = new Client();

        client1.setClientId("client1");

        client1.setClientSecret("123456");


        Profile administrator = this.profileService.find(3);
        Profile pub = this.profileService.find(2);

        client1.setProfiles(new ArrayList<Profile>(Arrays.asList(administrator, pub)));
        client1.getScope().forEach(description -> System.out.println(description));
        client1.setAccessTokenValiditySeconds(36000);
        client1.setRefreshTokenValiditySeconds(36000);

        client1.setAuthorizedGrantTypes(new HashSet<GrantType>(Arrays.asList(GrantType.PASSWORD, GrantType.REFRESH_TOKEN, GrantType.IMPLICIT, GrantType.AUTHORIZATION_CODE, GrantType.CLIENT_CREDENTIALS)));
        clientService.save(client1);
    }

    @Test
    @Rollback(value = false)
    public void saveClient2MustPass() {

        Client client2 = new Client();

        client2.setClientId("client2");

        client2.setClientSecret("123456");

        Profile pub = this.profileService.find(2);
        Profile attendant = this.profileService.find(4);
        Profile manager = this.profileService.find(5);

        client2.setProfiles(new ArrayList<Profile>(Arrays.asList(pub, attendant, manager)));
        client2.getScope().forEach(description -> System.out.println(description));
        client2.setAccessTokenValiditySeconds(36000);
        client2.setRefreshTokenValiditySeconds(36000);

        client2.setAuthorizedGrantTypes(new HashSet<GrantType>(Arrays.asList(GrantType.PASSWORD, GrantType.REFRESH_TOKEN, GrantType.IMPLICIT, GrantType.AUTHORIZATION_CODE, GrantType.CLIENT_CREDENTIALS)));
        clientService.save(client2);
    }

    @Test
    @Rollback(value = false)
    public void saveUserAuthorizationServerMustPass() {

        Profile administratorAuthorizationServer = this.profileService.find(1);
        Profile pub = this.profileService.find(2);

        UserAccount userAccountAdministratorAuthorizationServer = new UserAccount();
        userAccountAdministratorAuthorizationServer.setEmail("admin@email.com");
        userAccountAdministratorAuthorizationServer.setName("admin");
        userAccountAdministratorAuthorizationServer.setPassword("123456");
        userAccountAdministratorAuthorizationServer.setProfiles(new LinkedList<Profile>(Arrays.asList(administratorAuthorizationServer, pub)));

        userAccountService.save(userAccountAdministratorAuthorizationServer);
    }

    @Test
    @Rollback(value = false)
    public void saveUserClient1MustPass() {

        Profile administrator = this.profileService.find(3);
        Profile pub = this.profileService.find(2);

        UserAccount userAccountAdministratorClient1 = new UserAccount();
        userAccountAdministratorClient1.setEmail("adminClient1@email.com");
        userAccountAdministratorClient1.setName("admin");
        userAccountAdministratorClient1.setPassword("123456");
        userAccountAdministratorClient1.setProfiles(new LinkedList<Profile>(Arrays.asList(administrator, pub)));

        userAccountService.save(userAccountAdministratorClient1);
    }

    @Test
    @Rollback(value = false)
    public void saveUserClient2MustPass() {

        Profile pub = this.profileService.find(2);
        Profile manager = this.profileService.find(4);
        Profile attendant = this.profileService.find(5);

        UserAccount userAccountAdministratorClient2 = new UserAccount();
        userAccountAdministratorClient2.setEmail("adminClient2@email.com");
        userAccountAdministratorClient2.setName("admin");
        userAccountAdministratorClient2.setPassword("123456");
        userAccountAdministratorClient2.setProfiles(new LinkedList<Profile>(Arrays.asList(attendant, manager, pub)));

        userAccountService.save(userAccountAdministratorClient2);
    }

}
