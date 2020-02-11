package br.com.emanuelvictor.funcionario.entity.public_schema;

import javax.persistence.*;

/**
 * Created by emanuel on 27/10/14.
 */
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(schema = "public")
public class PessoaFisica extends Pessoa {

    private static final long serialVersionUID = 6598446511490737297L;


}
