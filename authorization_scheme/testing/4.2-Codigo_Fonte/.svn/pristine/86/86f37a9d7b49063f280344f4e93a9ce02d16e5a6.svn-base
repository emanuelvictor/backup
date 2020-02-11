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
@DataTransferObject(javascript = "DependenciaPerfilAcesso")
@Table(uniqueConstraints =
{ @UniqueConstraint(columnNames =
{ "perfil_acesso_id", "perfil_acesso_subordinado_id" }) })
public class DependenciaPerfilAcesso extends AbstractEntity
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2346308379800531055L;
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@ManyToOne(optional = false)
	private PerfilAcesso perfilAcessoSubordinado;

	/**
	 * 
	 */
	@ManyToOne(optional = false)
	private PerfilAcesso perfilAcesso;

	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * @param id
	 * @param perfilAcessoSubordinado
	 * @param perfilAcesso
	 */
	public DependenciaPerfilAcesso( Long id, PerfilAcesso perfilAcessoSubordinado, PerfilAcesso perfilAcesso )
	{
		super( id );
		this.perfilAcessoSubordinado = perfilAcessoSubordinado;
		this.perfilAcesso = perfilAcesso;
	}

	/**
	 * @param perfilAcessoSubordinado
	 * @param perfilAcesso
	 */
	public DependenciaPerfilAcesso( PerfilAcesso perfilAcessoSubordinado, PerfilAcesso perfilAcesso )
	{
		this.perfilAcessoSubordinado = perfilAcessoSubordinado;
		this.perfilAcesso = perfilAcesso;
	}

	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
	/**
	 * @return the perfilAcessoSubordinado
	 */
	public PerfilAcesso getPerfilAcessoSubordinado()
	{
		return perfilAcessoSubordinado;
	}

	/**
	 * @param perfilAcessoSubordinado the perfilAcessoSubordinado to set
	 */
	public void setPerfilAcessoSubordinado( PerfilAcesso perfilAcessoSubordinado )
	{
		this.perfilAcessoSubordinado = perfilAcessoSubordinado;
	}

	/**
	 * @return the perfilAcesso
	 */
	public PerfilAcesso getPerfilAcesso()
	{
		return perfilAcesso;
	}

	/**
	 * @param perfilAcesso the perfilAcesso to set
	 */
	public void setPerfilAcesso( PerfilAcesso perfilAcesso )
	{
		this.perfilAcesso = perfilAcesso;
	}

}
