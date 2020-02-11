package br.com.emanuelvictor.funcionario.entity.tenant_schema;

import org.directwebremoting.annotations.DataTransferObject;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by emanuelvictor on 01/04/16.
 */
@Entity
@DataTransferObject
public class Empresa implements Serializable{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( unique = true, nullable = false )
    private Long id;


    //TODO colocar em uma classe genérica pessoajurídica
//    @org.hibernate.validator.constraints.br.CNPJ
    @Column( unique = true, nullable = false )
    private String CNPJ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return CNPJ;
    }

    public void setCnpj(String CNPJ) {
        this.CNPJ = CNPJ;
    }
}
