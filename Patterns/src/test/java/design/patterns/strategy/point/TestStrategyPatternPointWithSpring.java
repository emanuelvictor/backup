package design.patterns.strategy.point;

import design.patterns.strategy.point.behavior.function.impl.Employee;
import design.patterns.strategy.point.behavior.function.impl.Executive;
import design.patterns.strategy.point.behavior.function.impl.NoFunction;
import design.patterns.strategy.point.behavior.hitpoint.impl.FourPointsPerDay;
import design.patterns.strategy.point.behavior.hitpoint.impl.NotHitPoint;
import design.patterns.strategy.point.behavior.motive.Motive;
import design.patterns.strategy.point.behavior.servicehour.impl.FourHoursServicePerDay;
import design.patterns.strategy.point.behavior.servicehour.impl.FullTime;
import design.patterns.strategy.point.behavior.servicehour.impl.NoHoursServicePerDay;
import design.patterns.strategy.point.entity.*;
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
@ContextConfiguration(classes = TestStrategyPatternPointWithSpring.class, loader = SpringApplicationContextLoader.class)
@ComponentScan
@EnableAutoConfiguration
public class TestStrategyPatternPointWithSpring {

    // Armazenará o contexto da aplicação
    ApplicationContext application = null;

    @Before
    public void setUp() {
        // Instancia o spring
        application = SpringApplication.run(TestStrategyPatternPointWithSpring.class);
    }

    @Test
    public void testWithSpring() {

        Assert.assertNotNull(application);

        System.out.println();
        System.out.println("Point Project");
        System.out.println();

        Collaborator trainee = application.getBean(CollaboratorTrainee.class);
        trainee.setName("João");

        Collaborator executive = application.getBean(CollaboratorExecutive.class);
        executive.setName("Vanderson");

        Collaborator contratorConsultant = application.getBean(CollaboratorConsultant.class);
        contratorConsultant.setName("Jéssica");

        Collaborator scholarship = application.getBean(CollaboratorScholarship.class);
        scholarship.setName("Maíra");

        Collaborator employee = application.getBean(CollaboratorEmployee.class);
        employee.setName("Maria");

        Assert.assertEquals(executive.getFunction().getFunction(), "Eu sou um (a) executivo (a)");
        Assert.assertEquals(executive.getServiceHour().getServiceHour(), "Eu trabalho em período integral");
        Assert.assertEquals(executive.getHitPoint().getPoints(), "Eu não preciso bater ponto");
        executive.display();

        Assert.assertEquals(contratorConsultant.getFunction().getFunction(), "Eu sou um (a) consultor (a)");
        Assert.assertEquals(contratorConsultant.getServiceHour().getServiceHour(), "Eu trabalho a quantidade de horas que quiser");
        Assert.assertEquals(contratorConsultant.getHitPoint().getPoints(), "Eu não preciso bater ponto");
        contratorConsultant.display();

        Assert.assertEquals(scholarship.getFunction().getFunction(), "Eu sou um (a) bolsista");
        Assert.assertEquals(scholarship.getServiceHour().getServiceHour(), "Eu trabalho a quantidade de horas que quiser");
        Assert.assertEquals(scholarship.getHitPoint().getPoints(), "Eu não preciso bater ponto");
        scholarship.display();

        Assert.assertEquals(trainee.getFunction().getFunction(), "Eu sou um (a) estagiário (a)");
        Assert.assertEquals(trainee.getServiceHour().getServiceHour(), "Eu trabalho quatro horas por dia");
        Assert.assertEquals(trainee.getHitPoint().getPoints(), "Eu bato o ponto 2 vezes por dia");
        trainee.display();


        Assert.assertEquals(employee.getFunction().getFunction(), "Eu sou um (a) funcionário (a) normal");
        Assert.assertEquals(employee.getServiceHour().getServiceHour(), "Eu trabalho 8 horas por dia");
        Assert.assertEquals(employee.getHitPoint().getPoints(), "Eu bato ponto quatro vezes por dia");
        employee.display();

        System.out.println();
        System.out.println("Chefe decide remodelar a organização, promevendo funcionário a executivo e estagiário a funcionário. O executivo foi demitido");
        System.out.println();

        employee.setFunction(application.getBean(Executive.class));
        employee.setHitPoint(application.getBean(NotHitPoint.class));
        employee.setServiceHour(application.getBean(FullTime.class));

        trainee.setFunction(application.getBean(Employee.class));
        trainee.setHitPoint(application.getBean(FourPointsPerDay.class));
        trainee.setServiceHour(application.getBean(FourHoursServicePerDay.class));

        Fired fired = application.getBean(Fired.class);
        fired.setFired(application.getBean(NoHoursServicePerDay.class), application.getBean(NotHitPoint.class), application.getBean(NoFunction.class));
        fired.setName(executive.getName());
        fired.setMotive( application.getBean(Motive.class));
        executive = fired;

        Assert.assertEquals(((Fired) executive).getMotive().getMotive(), "Corte no orçamento");
        Assert.assertEquals(executive.getFunction().getFunction(), "Eu não tenho função :(");
        Assert.assertEquals(executive.getServiceHour().getServiceHour(), "Eu trabalho 0 horas por dia :(");
        Assert.assertEquals(executive.getHitPoint().getPoints(), "Eu não preciso bater ponto");
        executive.display();

        contratorConsultant.display();
        scholarship.display();

        Assert.assertEquals(trainee.getFunction().getFunction(), "Eu sou um (a) funcionário (a) normal");
        Assert.assertEquals(trainee.getServiceHour().getServiceHour(), "Eu trabalho 8 horas por dia");
        Assert.assertEquals(trainee.getHitPoint().getPoints(), "Eu bato ponto quatro vezes por dia");
        trainee.display();

        Assert.assertEquals(employee.getFunction().getFunction(), "Eu sou um (a) executivo (a)");
        Assert.assertEquals(employee.getServiceHour().getServiceHour(), "Eu trabalho em período integral");
        Assert.assertEquals(employee.getHitPoint().getPoints(), "Eu não preciso bater ponto");
        employee.display();

    }

    @Test
    public void testWithNotNullSpringInstanceAndMustPass() {
        Assert.assertNotNull(application);
    }

    @Test
    public void testGetBeanAndNotNullTraineeInstanceAndMustPass() {
        Collaborator trainee = application.getBean(CollaboratorTrainee.class);
        Assert.assertNotNull(trainee);
        // O nome do colaborador esta sendo setado no construtor default
        Assert.assertNotNull(trainee.getName());
        // O nome do colaborador deve ser aquele que foi setado no construtor
        Assert.assertEquals(trainee.getName(), "test");
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
