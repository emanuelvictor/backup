package demo;

import br.com.emanuelvictor.funcionario2.entity.application.Application;
import br.com.emanuelvictor.funcionario2.entity.application.GrantType;
import br.com.emanuelvictor.funcionario2.entity.application.Scope;
import br.com.emanuelvictor.funcionario2.entity.position.Position;
import br.com.emanuelvictor.funcionario2.entity.user.Employee;
import br.com.emanuelvictor.funcionario2.service.ServiceApplication;
import br.com.emanuelvictor.funcionario2.service.ServiceEmployee;
import br.com.emanuelvictor.funcionario2.service.ServicePosition;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashSet;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = br.com.emanuelvictor.funcionario2.Application.class)
public class Funcionario2ApplicationTests {

    @Autowired
    ServiceEmployee serviceEmployee;

    @Autowired
    ServiceApplication serviceApplication;

    @Autowired
    ServicePosition servicePosition;

    @Test
    @Rollback(value = false)
    public void contextLoads() {

        Position position = new Position(1, "Analista de Sistemas");
        position = this.servicePosition.save(position);

        Employee employee = new Employee();
        employee.setEmail("roy");
        employee.setName("roy");
        employee.setPassword("spring");
        employee.setPosition(position);

        this.serviceEmployee.save(employee);
    }

    @Test
    @Rollback(value = false)
    public void test() {
        Employee employee = this.serviceEmployee.find(1);
        employee.setPassword("123456");
        this.serviceEmployee.save(employee);
    }

    @Test
    @Rollback(value = false)
    public void testPopulatePositions() {
        Position programador = new Position(null, "Programador");
        Position diretorTecnico = new Position(null, "Diretor técnico");
        Position gerenteTI = new Position(null, "Gerente de TI");
        this.servicePosition.save(programador);
        this.servicePosition.save(diretorTecnico);
        this.servicePosition.save(gerenteTI);
    }


    @Test
    @Rollback(value = false)
    public void testSaveFuncionario2() {
        Application application = new Application();

        application.setClientId("funcionario2");
        application.setClientSecret("123456");

        Scope leitura = new Scope((long) 1, "leitura", application);
        Scope escrita = new Scope((long) 2, "escrita", application);
        Scope exclusao = new Scope((long) 3, "exclusao", application);

        application.setScopes(new HashSet<Scope>(Arrays.asList(leitura, escrita, exclusao)));

        application.getScope().forEach(description -> System.out.println(description));

        application.setAccessTokenValiditySeconds(36000);
        application.setRefreshTokenValiditySeconds(36000);

        application.setAuthorizedGrantTypes(new HashSet<GrantType>(Arrays.asList(GrantType.PASSWORD, GrantType.REFRESH_TOKEN, GrantType.IMPLICIT, GrantType.AUTHORIZATION_CODE, GrantType.CLIENT_CREDENTIALS)));

        serviceApplication.save(application);

    }

    @Test
    @Rollback(value = false)
    public void testSavePDTI() {

        /** Salvando usuário do pdti*/
        Employee employee = new Employee();
        employee.setEmail("pdti@pdti.com.br");
        employee.setName("pdti");
        employee.setPassword("spring");
        employee.setPosition(this.servicePosition.find(1));
        this.serviceEmployee.save(employee);


        /** Salvando aplicativo pdti*/
        Application pdti = new Application();

        pdti.setClientId("PDTI");
        // secret será utilizado para o grant_type AuthorizationCode, quando um alpicativo quer se autenticar
        pdti.setClientSecret("123456");

        Scope administrador = new Scope((long) 1, "ADMINISTRADOR", pdti);
        Scope publico = new Scope((long) 2, "PUBLICO", pdti);
        pdti.setScopes(new HashSet<Scope>(Arrays.asList(administrador, publico)));
        pdti.getScope().forEach(description -> System.out.println(description));
        pdti.setAccessTokenValiditySeconds(36000);
        pdti.setRefreshTokenValiditySeconds(36000);

        // Os grant_types a serem utilizados são os PASSWORD (para autenticação do usuário), AUTHORIZATION_CODE para autorização do aplicativo, e REFRESH_TOKEN (para recuperação do token)
        pdti.setAuthorizedGrantTypes(new HashSet<GrantType>(Arrays.asList(GrantType.PASSWORD, GrantType.REFRESH_TOKEN, GrantType.IMPLICIT, GrantType.AUTHORIZATION_CODE, GrantType.CLIENT_CREDENTIALS)));
        serviceApplication.save(pdti);

    }

    @Test
    public void testFindOneApplication() {
        Application application = this.serviceApplication.find("funcionario2");

        Assert.assertNotNull(application.getAuthorizedGrantTypes());

        System.out.println(application.getAuthorizedGrantTypes().size());

    }




}
