package br.com.emanuelvictor.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@TransactionConfiguration
////@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
//@RunWith(SpringJUnit4ClassRunner.class)
public class Funcionarios {


//    @Autowired
//    DAOFuncionario daoFuncionario;
//
//    @Autowired
//    DAOCargo daoCargo;

//    @Test
//    @Rollback(false)
//    @Transactional
//    public void saveSuccess() {
//
//        System.out.println("Testando a integração com o banco");
//
//        Cargo gerente = new Cargo();
//        gerente.setNome("GERENTE");
//        gerente = daoCargo.save(gerente);
//
//        Cargo testador = new Cargo();
//        testador.setNome("TESTADOR");
//        testador = daoCargo.save(testador);
//
//        Cargo analista = new Cargo();
//        analista.setNome("Analista de sistemas");
//        analista = daoCargo.save(analista);
//
//        Funcionario funcionario1 = new Funcionario();
//        funcionario1.setPassword(new BCryptPasswordEncoder().encode("123456"));
//        funcionario1.setNome("Rodrigo");
//        funcionario1.setEmail("rodrigo@eits.com.br");
//        funcionario1.setCargo(gerente);
//        daoFuncionario.save(funcionario1);
//
//        Funcionario funcionario2 = new Funcionario();
//        funcionario2.setNome("Emanuel");
//        funcionario2.setPassword(new BCryptPasswordEncoder().encode("123456"));
//        funcionario2.setEmail("emanuel.fonseca@eits.com.br");
//        funcionario2.setCargo(analista);
//        daoFuncionario.save(funcionario2);
//
//        Funcionario funcionario3 = new Funcionario();
//        funcionario3.setNome("Fulano");
//        funcionario3.setPassword(new BCryptPasswordEncoder().encode("123456"));
//        funcionario3.setEmail("fulano@gmail.com");
//        funcionario3.setCargo(testador);
//        daoFuncionario.save(funcionario3);
//
//        assertTrue(true);
//
//    }
//
//
//    @Test
//    @Rollback(false)
//    @Transactional
//    public void update() {
//
//        System.out.println("Testando a integração com o banco");
//
//        Cargo gerente = daoCargo.findOne((long) 1);
//        gerente.setNome("GERENTE 2");
//
//        daoCargo.save(gerente);
//
//        assertTrue(true);
//
//    }
//
//    @Test
//    @Rollback(false)
//    @Transactional
//    public void saveFailtWhitoutCharge() {
//
//        System.out.println("Testando a integração com o banco");
//
//
//        Funcionario funcionario1 = new Funcionario();
//        funcionario1.setPassword(new BCryptPasswordEncoder().encode("123456"));
//        funcionario1.setNome("Rodrigo");
//        funcionario1.setEmail("rodrigo@eits.com.br");
//        daoFuncionario.save(funcionario1);
//
//        assertTrue(true);
//
//    }
//
//    @Test
//    @Rollback(false)
//    @Transactional
//    public void saveFailtDoubleLogin() {
//
//        System.out.println("Testando a integração com o banco");
//
//
//        Funcionario funcionario1 = new Funcionario();
//        funcionario1.setPassword(new BCryptPasswordEncoder().encode("123456"));
//        funcionario1.setNome("Rodrigo");
//        funcionario1.setEmail("rodrigo@eits.com.br");
//        daoFuncionario.save(funcionario1);
//
//        assertTrue(true);
//
//    }


    @Test
    public void saveFailtDoubleLogin() {

        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }


}