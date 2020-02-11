/**
 * 
 */
package br.mil.mar.dabm.autenticador.domain.entity.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author emanuelvictor
 *
 */
@Entity
@Audited
@DataTransferObject(javascript = "Configuracao")
public class Configuracao extends AbstractEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7298716080263570386L;

	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	// Basic
	/**
	 * 
	 */
	@NotNull(message = "Os dias de expiração de senha não podem ser nulos")
	@Column(nullable = false)
	@Min(value = 0)
	private Integer diasExpiracaoSenha;

	/**
	 * 
	 */
	public Configuracao()
	{
		super();
	}

	/**
	 * @param id
	 */
	public Configuracao( Long id )
	{
		super( id );
	}

	/**
	 * @param diasExpiracaoSenha
	 */
	public Configuracao( Long id, Integer diasExpiracaoSenha )
	{
		super( id );
		this.setDiasExpiracaoSenha( diasExpiracaoSenha );
	}

	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
	/**
	 * @return the diasExpiracaoSenha
	 */
	public Integer getDiasExpiracaoSenha()
	{
		return diasExpiracaoSenha;
	}

	/*-------------------------------------------------------------------
	 *							BEHAVIORS
	 *-------------------------------------------------------------------*/

	/**
	 * @param diasExpiracaoSenha the diasExpiracaoSenha to set
	 */
	public void setDiasExpiracaoSenha( Integer diasExpiracaoSenha )
	{
		if ( diasExpiracaoSenha > 0 )
		{
			this.diasExpiracaoSenha = diasExpiracaoSenha;
		}
	}

}
