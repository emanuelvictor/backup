package br.com.emanuelvictor.funcionario.entity.public_schema;

import java.io.Serializable;

import javax.persistence.*;

import org.directwebremoting.annotations.DataTransferObject;

@Entity
@Table(schema = "public")
@DataTransferObject
public class Cargo implements Serializable {

	private static final long serialVersionUID = -5183749530891212557L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( unique = true, nullable = false)
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
	private String nome;

    @Column(nullable = false)
    private Perfil perfil;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}
