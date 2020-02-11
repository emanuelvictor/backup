package design.patterns.strategy.perfisusuarios;

import design.patterns.strategy.papeisusuario.papeis.impl.PapelAdministrador;
import design.patterns.strategy.papeisusuario.papeis.impl.PapelAluno;
import design.patterns.strategy.papeisusuario.papeis.impl.PapelInstrutor;
import design.patterns.strategy.perfisusuarios.entity.Administrador;
import design.patterns.strategy.perfisusuarios.entity.Aluno;
import design.patterns.strategy.perfisusuarios.entity.Instrutor;
import design.patterns.strategy.perfisusuarios.entity.Usuario;
import design.patterns.strategy.perfisusuarios.papeis.impl.AdministradorPerfil;
import design.patterns.strategy.perfisusuarios.papeis.impl.AlunoPerfil;
import design.patterns.strategy.perfisusuarios.papeis.impl.InstrutorPerfil;
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

        joao.setPerfil(new InstrutorPerfil());
        vanderson.setPerfil(new AlunoPerfil());
        jessica.setPerfil(new AdministradorPerfil());

        joao.display();
        vanderson.display();
        jessica.display();

    }

}
