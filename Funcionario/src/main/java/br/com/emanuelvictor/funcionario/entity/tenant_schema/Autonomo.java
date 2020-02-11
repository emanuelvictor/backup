package br.com.emanuelvictor.funcionario.entity.tenant_schema;

import br.com.emanuelvictor.funcionario.entity.public_schema.Funcionario;
import org.directwebremoting.annotations.DataTransferObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by emanuelvictor on 01/04/16.
 */
@Entity
@DataTransferObject
public class Autonomo extends Funcionario {

    @ManyToOne(optional = false)
    private Empresa empresa;

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
