package br.com.emanuelvictor.funcionario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.emanuelvictor.funcionario.entity.public_schema.Funcionario;
import br.com.emanuelvictor.funcionario.repository.DAOFuncionario;

/**
 * Created by emanuelvictor on 06/02/15.
 */
@Component
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    DAOFuncionario daoFuncionario;

//    @Autowired
//    DAOCargo daoCargo;

    @Override
    @Transactional(/*readOnly = true*/)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

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


        Funcionario funcionario = this.daoFuncionario.findByEmailIgnoreCase(email);

        if (funcionario == null) {
            System.out.println(email + " não encontrado");
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        System.out.println(email + " encontrado");

        return funcionario;
    }


//    public UserDetails getCurrent() throws UsernameNotFoundException {
//        return (Funcionario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    }




}
