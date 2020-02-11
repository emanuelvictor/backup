package br.com.eits.oauth2.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails, Serializable {

	private static final long serialVersionUID = 1121147605946406759L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(length = 50, unique = true, nullable = false)
	private String name;
	
	@Column(length = 50, unique = true, nullable = false)
	private String email;

	@Column(nullable = false, length = 100000)
	private String password;

	//TODO deverá ter um atributo para Roles também
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	// AUTENTICAÇÃO
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" +
		// "ADMINISTRADOR"/*this.getPerfil()*/);
		// ArrayList<GrantedAuthority> authorities = new
		// ArrayList<GrantedAuthority>();
		// authorities.add(authority);
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
