package br.com.limittraining.app.service;

import java.util.List;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.limittraining.app.entity.user.Usuario;
import br.com.limittraining.app.repository.IUsuarioRepository;


@Service
@Transactional
@RemoteProxy(name = "usuarioService")
public class UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    public Usuario save(Usuario usuario) {
        if (usuario.getPassword() == null) {
            //Handler pra atualizar demais informações do funcionário
            usuario.setPassword(this.usuarioRepository.findOne(usuario.getId()).getPassword());
        } else {
            //Handler pra novo funcionário
            usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        }
        return this.usuarioRepository.save(usuario);
    }

    public void destroy(Long id) {
        usuarioRepository.delete(id);
    }

    public List<Usuario> find() {
        return usuarioRepository.findAll();
    }

    public Usuario find(Long id) {
        return usuarioRepository.findOne(id);
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        return iUsuarioRepository.findByEmail(email);
//    }

    public Usuario getCurrentUser() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}