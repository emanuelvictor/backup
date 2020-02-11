package br.com.limittraining.app.entity.core;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Serie {

	@Id
	private Long id;
	/**
	 * Duração do exercício (em minutos)
	 */
	private Long tempo;

	/**
	 * Número de repetições do exercício
	 */
	private Integer repeticoesDoExercicio;
	/**
	 * 	
	 */
	@ManyToOne(optional = false)
	private Exercicio exercicio;
	/**
	 * Tempo de descanço entre uma serie e outra
	 */
	private Long tempoDescanso;
	/**
	 * @Param agrupamento
	 */
	@ManyToOne(optional = false)
	private Agrupamento agrupamento;

	private Integer ordemNoAgrupamento;

	private String observacao;

	private Long carga;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTempo() {
		return tempo;
	}

	public void setTempo(Long tempo) {
		this.tempo = tempo;
	}

	public Integer getRepeticoesDoExercicio() {
		return repeticoesDoExercicio;
	}

	public void setRepeticoesDoExercicio(Integer repeticoesDoExercicio) {
		this.repeticoesDoExercicio = repeticoesDoExercicio;
	}

	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	public Long getTempoDescanso() {
		return tempoDescanso;
	}

	public void setTempoDescanso(Long tempoDescanso) {
		this.tempoDescanso = tempoDescanso;
	}

	public Agrupamento getAgrupamento() {
		return agrupamento;
	}

	public void setAgrupamento(Agrupamento agrupamento) {
		this.agrupamento = agrupamento;
	}

	public Integer getOrdemNoAgrupamento() {
		return ordemNoAgrupamento;
	}

	public void setOrdemNoAgrupamento(Integer ordemNoAgrupamento) {
		this.ordemNoAgrupamento = ordemNoAgrupamento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Long getCarga() {
		return carga;
	}

	public void setCarga(Long carga) {
		this.carga = carga;
	}

}
