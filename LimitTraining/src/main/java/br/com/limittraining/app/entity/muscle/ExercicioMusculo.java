package br.com.limittraining.app.entity.muscle;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.limittraining.app.entity.core.Exercicio;

@Entity
public class ExercicioMusculo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7439442844973540116L;

	@Id
	@ManyToOne
	@JoinColumn(name = "exercicio_id", nullable = false)
	private Exercicio exercicio;

	@ManyToOne
	@JoinColumn(name = "musculo_id", nullable = false)
	private Musculo musculo;

	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	public Musculo getMusculo() {
		return musculo;
	}

	public void setMusculo(Musculo musculo) {
		this.musculo = musculo;
	}

}
