package br.com.emanuelvictor.funcionario.service;

import br.com.emanuelvictor.funcionario.entity.public_schema.Cargo;
import br.com.emanuelvictor.funcionario.repository.DAOCargo;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RemoteProxy(name = "cargoService")
public class ServiceCargo {

    @Autowired
    DAOCargo daoCargo;

    public Cargo save(Cargo cargo) {
        return daoCargo.save(cargo);
    }

    public void destroy(Long id) {
        this.daoCargo.delete(id);
    }

    public Cargo find(Long id) {
        return daoCargo.findOne(id);
    }

    public List<Cargo> find() {
        try {
            return daoCargo.findAll();

        } catch (Exception e) {
            System.out.print(e.getMessage());
            e.printStackTrace();
        }
        return null;

    }

    public List<Cargo> find(String nome) {
        return daoCargo.findByNomeIgnoreCaseContaining(nome);
    }

}