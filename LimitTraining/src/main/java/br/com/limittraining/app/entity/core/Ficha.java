package br.com.limittraining.app.entity.core;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ficha {

	@Id
	private Long id;

	@ManyToOne(optional = false)
	private Aluno aluno;

	@Column(nullable = false)
	private Calendar dataInicio;

	private Calendar dataFim;

	private String objetivo;

	@ManyToOne
	private AvaliacaoFisica avaliacaoFisica;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public AvaliacaoFisica getAvaliacaoFisica() {
		return avaliacaoFisica;
	}

	public void setAvaliacaoFisica(AvaliacaoFisica avaliacaoFisica) {
		this.avaliacaoFisica = avaliacaoFisica;
	}

}
