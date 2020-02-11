package br.com.emanuelvictor.funcionario.entity.public_schema;

import java.io.Serializable;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Table(schema = "public")
@Entity
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 6598446511490737297L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( unique = true, nullable = false )
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	@Column(nullable = false, length = 100)
	protected String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}