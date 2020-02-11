package design.patterns.strategy.papeisusuario;

import design.patterns.strategy.point.entity.*;
import design.patterns.strategy.papeisusuario.papeis.impl.PapelAdministrador;
import design.patterns.strategy.papeisusuario.papeis.impl.PapelAluno;
import design.patterns.strategy.papeisusuario.papeis.impl.PapelInstrutor;
import design.patterns.strategy.papeisusuario.entity.Administrador;
import design.patterns.strategy.papeisusuario.entity.Aluno;
import design.patterns.strategy.papeisusuario.entity.Instrutor;
import design.patterns.strategy.papeisusuario.entity.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestStrategyPatternUsuarioWithSpring.class, loader = SpringApplicationContextLoader.class)
@ComponentScan
@EnableAutoConfiguration
public class TestStrategyPatternUsuarioWithSpring {

    // Armazenará o contexto da aplicação
    ApplicationContext application = null;

    @Before
    public void setUp() {
        // Instancia o spring
        application = SpringApplication.run(TestStrategyPatternUsuarioWithSpring.class);
    }

    @Test
    public void testWithSpring() {

        Assert.assertNotNull(application);

        System.out.println();
        System.out.println("Usuário strategy pattern project");
        System.out.println();

        Usuario joao = application.getBean(Aluno.class);
        joao.setName("João");

        Usuario vanderson = application.getBean(Instrutor.class);
        vanderson.setName("Vanderson");

        Usuario jessica = application.getBean(Administrador.class);
        jessica.setName("Jéssica");


        joao.display();
        vanderson.display();
        jessica.display();
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.println();

        joao.setPapel(new PapelInstrutor());
        vanderson.setPapel(new PapelAluno());
        jessica.setPapel(new PapelAdministrador());

        joao.display();
        vanderson.display();
        jessica.display();

    }

    @Test
    public void testWithNotNullSpringInstanceAndMustPass() {
        Assert.assertNotNull(application);
    }

    /**
     *
     */
    @Test
    public void testGetBeanAndNotNullTraineeInstanceAndMustPass() {
        Usuario joao = application.getBean(Aluno.class);
        Assert.assertEquals(joao.getPapel().getPerfil(), new PapelAluno().getPerfil());

        joao.setPapel(new PapelInstrutor());
        Assert.assertNotEquals(joao.getPapel().getPerfil(), new PapelAluno().getPerfil());
        Assert.assertEquals(joao.getPapel().getPerfil(), new PapelInstrutor().getPerfil());
    }

    @Test
    public void testGetBeanAndNotNullConsultantInstanceAndMustPass() {
        Collaborator consultant = application.getBean(CollaboratorConsultant.class);
        Assert.assertNotNull(consultant);
        // O nome do colaborador não esta sendo setado no construtor default
        Assert.assertNotNull(consultant.getName());
        // O nome do colaborador deve ser aquele que foi setado na classe mãe 'User'
        Assert.assertEquals(consultant.getName(), "User Name");
    }

    @Test
    public void testGetBeanAndNotNullScholarshipInstanceAndMustPass() {
        Collaborator scholarship = application.getBean(CollaboratorScholarship.class);
        Assert.assertNotNull(scholarship);
        // Testa se o bolsista esta mesmo como bolsista, e não com a configuração padrão da classe abstrata
        // Note que o colaborador é bolsista somente porque o método setPapel foi implementado
        Assert.assertEquals(scholarship.getFunction().getFunction(), "Eu sou um (a) bolsista" );
    }

}
