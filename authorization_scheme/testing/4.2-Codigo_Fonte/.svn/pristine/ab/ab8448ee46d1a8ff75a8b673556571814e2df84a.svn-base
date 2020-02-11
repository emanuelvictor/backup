/**
 * 
 */
package br.mil.mar.dabm.autenticador.domain.entity.aplicativo;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author emanuelvictor
 *
 */
@Entity
@Audited
@DataTransferObject(javascript = "DependenciaPermissao")
@Table(uniqueConstraints =
{ @UniqueConstraint(columnNames =
{ "permissao_id", "permissao_subordinada_id" }) })
public class DependenciaPermissao extends AbstractEntity
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 646329483349875044L;
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@ManyToOne(optional = false)
	private Permissao permissao;
	/**
	 * 
	 */
	@ManyToOne(optional = false)
	private Permissao permissaoSubordinada;

	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * @param permissao
	 * @param permissaoSubordinada
	 */
	public DependenciaPermissao( Permissao permissao, Permissao permissaoSubordinada )
	{
		super();
		this.permissao = permissao;
		this.permissaoSubordinada = permissaoSubordinada;
	}

	/**
	 * @param id
	 * @param permissao
	 * @param permissaoSubordinada
	 */
	public DependenciaPermissao( Long id, Permissao permissao, Permissao permissaoSubordinada )
	{
		super( id );
		this.permissao = permissao;
		this.permissaoSubordinada = permissaoSubordinada;
	}

	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
	/**
	 * @return the permissao
	 */
	public Permissao getPermissao()
	{
		return permissao;
	}

	/**
	 * @param permissao the permissao to set
	 */
	public void setPermissao( Permissao permissao )
	{
		this.permissao = permissao;
	}

	/**
	 * @return the permissaoSubordinada
	 */
	public Permissao getPermissaoSubordinada()
	{
		return permissaoSubordinada;
	}

	/**
	 * @param permissaoSubordinada the permissaoSubordinada to set
	 */
	public void setPermissaoSubordinada( Permissao permissaoSubordinada )
	{
		this.permissaoSubordinada = permissaoSubordinada;
	}

}
