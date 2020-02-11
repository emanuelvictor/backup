package br.com.agenciaiguassu.domain.entity.job;

import java.util.Calendar;

import javax.persistence.*;

import br.com.agenciaiguassu.domain.entity.base.SpringData;
import br.com.agenciaiguassu.domain.entity.Candidato;
import br.com.agenciaiguassu.domain.entity.Empresa;
import br.com.agenciaiguassu.domain.entity.VinculoEmpregaticio;

//Tabela responsável por armazenar os dados de experiência no banco de dados
@Entity
public class Experiencia extends SpringData<Long> {

	private static final long serialVersionUID = 1816644237239991227L;

	@ManyToOne(optional = false)
	private Cargo cargo;

//    @JsonIgnore
    @ManyToOne(optional = false)
//    @JsonBackReference
	private Candidato candidato;

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    @ManyToOne(optional = true)
	private Empresa empresa;

	@Column(length = 20000)
	private String atividades;
	@Column(length = 20000)
	private String observacoes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private VinculoEmpregaticio vinculoEmpregaticio;

	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Calendar dataInicio;
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Calendar dataTermino;

    public VinculoEmpregaticio getVinculoEmpregaticio() {
        return vinculoEmpregaticio;
    }

    public void setVinculoEmpregaticio(VinculoEmpregaticio vinculoEmpregaticio) {
        this.vinculoEmpregaticio = vinculoEmpregaticio;
    }

    public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getAtividades() {
		return atividades;
	}

	public void setAtividades(String atividades) {
		this.atividades = atividades;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Calendar dataTermino) {
		this.dataTermino = dataTermino;
	}

}
