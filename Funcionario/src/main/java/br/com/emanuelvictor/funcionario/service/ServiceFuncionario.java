package br.com.emanuelvictor.funcionario.service;

import java.util.List;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.emanuelvictor.funcionario.entity.public_schema.Funcionario;
import br.com.emanuelvictor.funcionario.repository.DAOFuncionario;

@Service
@Transactional
@RemoteProxy(name = "funcionarioService")
public class ServiceFuncionario {

    @Autowired
    DAOFuncionario daoFuncionario;

    public Funcionario save(Funcionario funcionario) {
        if (funcionario.getPassword() == null) {
            //Handler pra atualizar demais informações do funcionário
            funcionario.setPassword(this.daoFuncionario.findOne(funcionario.getId()).getPassword());
        } else {
            //Handler pra novo funcionário
            funcionario.setPassword(new BCryptPasswordEncoder().encode(funcionario.getPassword()));
        }
        return this.daoFuncionario.save(funcionario);
    }

    public void destroy(Long id) {
        daoFuncionario.delete(id);
    }

    public List<Funcionario> find() {
        return daoFuncionario.findAll();
    }

    public Funcionario find(Long id) {
        return daoFuncionario.findOne(id);
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        return daoFuncionario.findByEmail(email);
//    }

    public Funcionario getCurrentUser() {
        return (Funcionario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}