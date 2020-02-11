package design.patterns.strategy.point;

import org.junit.Test;

import design.patterns.strategy.point.behavior.function.impl.Employee;
import design.patterns.strategy.point.behavior.function.impl.Executive;
import design.patterns.strategy.point.behavior.function.impl.Scholarship;
import design.patterns.strategy.point.behavior.hitpoint.impl.FourPointsPerDay;
import design.patterns.strategy.point.behavior.hitpoint.impl.NotHitPoint;
import design.patterns.strategy.point.behavior.motive.impl.Foolish;
import design.patterns.strategy.point.behavior.servicehour.impl.FourHoursServicePerDay;
import design.patterns.strategy.point.behavior.servicehour.impl.FullTime;
import design.patterns.strategy.point.behavior.servicehour.impl.OptionalTime;
import design.patterns.strategy.point.entity.Collaborator;
import design.patterns.strategy.point.entity.CollaboratorConsultant;
import design.patterns.strategy.point.entity.CollaboratorEmployee;
import design.patterns.strategy.point.entity.CollaboratorExecutive;
import design.patterns.strategy.point.entity.CollaboratorScholarship;
import design.patterns.strategy.point.entity.CollaboratorTrainee;
import design.patterns.strategy.point.entity.Fired;

/**
 *
 */
public class TestStrategyPatternPoint {


    //Testes sem o spring
    //NOTA  : Observe o acoplamento
    @Test
    public void testWithoutSpring() {

        System.out.println();
        System.out.println("Point Project");
        System.out.println();

        Collaborator trainee = new CollaboratorTrainee();
        trainee.setName("João");

        Collaborator executive = new CollaboratorExecutive();
        executive.setName("Vanderson");

        Collaborator contratorEmployee = new CollaboratorConsultant();
        contratorEmployee.setName("Jéssica");

        // Este exemplo mostra a falta de coersão e desacoplamento do código.
        // Note que para o construtor de CollaboratorScholarship preciso passar outras três dependÊncias.
        // Isso é abstraído pelo spring, sendo gerenciado por ele.
        Collaborator scholarship = new CollaboratorScholarship(new Scholarship(), new OptionalTime(), new NotHitPoint());
        scholarship.setName("Maíra");

        Collaborator employee = new CollaboratorEmployee();
        employee.setName("Maria");

        executive.display();
        contratorEmployee.display();
        scholarship.display();
        trainee.display();
        employee.display();
        System.out.println();
        System.out.println("Chefe decide remodelar a organização, promevendo funcionário a executivo e estagiário a funcionário. O executivo foi demitido");
        System.out.println();

        employee.setFunction(new Executive());
        employee.setHitPoint(new NotHitPoint());
        employee.setServiceHour(new FullTime());

        trainee.setFunction(new Employee());
        trainee.setHitPoint(new FourPointsPerDay());
        trainee.setServiceHour(new FourHoursServicePerDay());

        //Neste trecho o desacoplamento fica ainda mais visível, pois se é necessário criar um Folish, setar o motivo nele,
        // e depois se criar um Fired entrando com essa dependencia recém criada. Imagine isso em escala maior.
        Foolish foolish = new Foolish();
        foolish.setMotive("Corte no orçamento");
        executive = new Fired(executive.getName(), foolish);

        executive.display();
        contratorEmployee.display();
        scholarship.display();
        trainee.display();
        employee.display();
    }


}
