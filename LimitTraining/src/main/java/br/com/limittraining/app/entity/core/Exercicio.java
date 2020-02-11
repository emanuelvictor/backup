package br.com.limittraining.app.entity.core;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Exercicio {

	@Id
	private Long id;

	private String nome;

	@ManyToOne
	private TipoExercicio tipoExercicio;

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

	public TipoExercicio getTipoExercicio() {
		return tipoExercicio;
	}

	public void setTipoExercicio(TipoExercicio tipoExercicio) {
		this.tipoExercicio = tipoExercicio;
	}

}
