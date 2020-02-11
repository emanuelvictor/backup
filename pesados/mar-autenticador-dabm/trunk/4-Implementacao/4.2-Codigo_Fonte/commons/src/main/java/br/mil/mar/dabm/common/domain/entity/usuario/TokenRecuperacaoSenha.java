/**
 * 
 */
package br.mil.mar.dabm.common.domain.entity.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author emanuelvictor
 *
 */
@Entity
@Audited
public class TokenRecuperacaoSenha extends AbstractEntity 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6365463595805179426L;
	/**
	 * 
	 */
	@NotBlank
	@Length(min = 20)
	@Column(nullable = false, length = 255, unique = true)
	private String token;
	/**
	 * 
	 */
	@OneToOne(fetch = FetchType.EAGER, optional = false)
	private Usuario usuario;

	/**
	 * @return the token
	 */
	public String getToken()
	{
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken( String token )
	{
		this.token = token;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario()
	{
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario( Usuario usuario )
	{
		this.usuario = usuario;
	}

}
