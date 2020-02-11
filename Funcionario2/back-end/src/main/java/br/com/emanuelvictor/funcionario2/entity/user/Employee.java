/**
 * 
 */
package br.com.emanuelvictor.funcionario2.entity.user;

import br.com.emanuelvictor.funcionario2.entity.position.Position;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author emanuelvictor
 *
 */
@Entity
// @Audited
//@JsonIgnoreProperties({"password"})
public class Employee implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer esmployeeId;

	@NotBlank(message = "O campo nome é obrigatório")
	@Column(length = 100, nullable = false)
	private String name;


	private boolean enabled = true;


	@Transient
	private OAuth2Authentication oAuth2Authentication;



	public OAuth2Authentication getoAuth2Authentication() {
		return oAuth2Authentication;
	}

	public void setoAuth2Authentication(OAuth2Authentication oAuth2Authentication) {
		this.oAuth2Authentication = oAuth2Authentication;
	}

	//	@CPF
//	@NotBlank(message = "O campo CPF é obrigatório")
//	@Column(unique = true, nullable = false)
//	private String cpf;

//	@Email
	@NotBlank(message = "O campo email é obrigatório")
	@Column(length = 50, unique = true, nullable = false)
	private String email;

	@NotBlank(message = "O campo senha é obrigatório")
	@Column(nullable = false, length = 100000)
	private String password;

	@NotNull(message = "O funcionário deve ter um cargo")
	@ManyToOne(optional = false)
	private Position position;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getEsmployeeId() {
		return esmployeeId;
	}

	public void setEsmployeeId(Integer esmployeeId) {
		this.esmployeeId = esmployeeId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub SERÁ UTILIZADO NO LOGINDAbM, FAZER DINÂMICO NA FUNÇÃO
		GrantedAuthority administrador = new SimpleGrantedAuthority(/*"ROLE_" +*/ "ADMINISTRADOR");
		GrantedAuthority publico = new SimpleGrantedAuthority(/*"ROLE_" +*/ "PUBLICO");
        ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(administrador);
		authorities.add(publico);
        return authorities;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub SOBRESCREVER NO LOGINDAbM

		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	

}
