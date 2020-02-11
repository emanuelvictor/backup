package br.com.limittraining.app.entity.core;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.limittraining.app.entity.user.Usuario;

@Entity
public class Professor {

	@Id
	private Long id;

	@ManyToOne(optional = false)
	private Usuario usuario;

	private String nome;

	private String CPF;

	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
