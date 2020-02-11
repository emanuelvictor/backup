package design.patterns.strategy.papeisusuario;

import design.patterns.strategy.papeisusuario.papeis.impl.PapelAdministrador;
import design.patterns.strategy.papeisusuario.papeis.impl.PapelAluno;
import design.patterns.strategy.papeisusuario.papeis.impl.PapelInstrutor;
import design.patterns.strategy.papeisusuario.entity.Administrador;
import design.patterns.strategy.papeisusuario.entity.Aluno;
import design.patterns.strategy.papeisusuario.entity.Usuario;
import design.patterns.strategy.papeisusuario.entity.Instrutor;
import org.junit.Test;

/**
 *
 */
public class TestStrategyPatternUsuario {


    //Testes sem o spring
    @Test
    public void testWithoutSpring() {

        System.out.println();
        System.out.println("Usuário strategy pattern project");
        System.out.println();

        Usuario joao = new Aluno();
        joao.setName("João");

        Usuario vanderson = new Instrutor();
        vanderson.setName("Vanderson");

        Usuario jessica = new Administrador();
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

}
