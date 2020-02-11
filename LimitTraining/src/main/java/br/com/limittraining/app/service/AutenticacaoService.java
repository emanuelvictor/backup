package br.com.limittraining.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.limittraining.app.entity.user.Usuario;
import br.com.limittraining.app.repository.IUsuarioRepository;


/**
 * Created by emanuelvictor on 06/02/15.
 */
@Component
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    @Transactional(/*readOnly = true*/)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        Cargo gerente = new Cargo();
//        gerente.setNome("GERENTE");
//        gerente.setPerfil(Perfil.ADMINISTRADOR);
//        gerente = daoCargo.save(gerente);
//
//        Cargo testador = new Cargo();
//        testador.setNome("TESTADOR");
//        testador.setPerfil(Perfil.USUARIO);
//        testador = daoCargo.save(testador);
//
//        Cargo analista = new Cargo();
//        analista.setPerfil(Perfil.ADMINISTRADOR);
//        analista.setNome("Analista de sistemas");
//        analista = daoCargo.save(analista);
//
//        Usuario funcionario1 = new Usuario();
//        funcionario1.setPassword(new BCryptPasswordEncoder().encode("123456"));
//        funcionario1.setNome("Rodrigo");
//        funcionario1.setEmail("rodrigo@eits.com.br");
//        funcionario1.setCargo(gerente);
//        iUsuarioRepository.save(funcionario1);
//
//        Usuario funcionario2 = new Usuario();
//        funcionario2.setNome("Emanuel");
//        funcionario2.setPassword(new BCryptPasswordEncoder().encode("123456"));
//        funcionario2.setEmail("emanuel.fonseca@eits.com.br");
//        funcionario2.setCargo(analista);
//        iUsuarioRepository.save(funcionario2);
//
//        Usuario usuario = new Usuario();
//        usuario.setUsername("test@test.com");
//        usuario.setPassword(new BCryptPasswordEncoder().encode("test"));
//        usuarioRepository.save(usuario);
//
//return null;

        Usuario usuario = this.usuarioRepository.findByUsernameIgnoreCase(username);

        if (usuario == null) {
            System.out.println(username + " Não encontrado");
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        System.out.println(username + " encontrado");

        return usuario;
    }


//    public UserDetails getCurrent() throws UsernameNotFoundException {
//        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    }




}
