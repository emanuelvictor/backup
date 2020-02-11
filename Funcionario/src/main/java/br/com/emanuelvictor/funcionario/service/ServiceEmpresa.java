package br.com.emanuelvictor.funcionario.service;

import br.com.emanuelvictor.funcionario.entity.tenant_schema.Empresa;
import br.com.emanuelvictor.funcionario.repository.DAOEmpresa;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RemoteProxy(name = "empresaService")
public class ServiceEmpresa {

    @Autowired
    DAOEmpresa daoEmpresa;

    public Empresa save(Empresa empresa) {
        try {
            System.out.println(empresa.getCnpj());
            return daoEmpresa.save(empresa);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void destroy(Long id) {
        this.daoEmpresa.delete(id);
    }

    public Empresa find(Long id) {
        return daoEmpresa.findOne(id);
    }

    public List<Empresa> find() {
        try {
            return daoEmpresa.findAll();

        } catch (Exception e) {
            System.out.print(e.getMessage());
            e.printStackTrace();
        }
        return null;

    }

    public List<Empresa> find(String CNPJ) {
        return daoEmpresa.find(CNPJ);
    }

}