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
@DataTransferObject(javascript = "PerfilAcessoPermissao")
@Table(uniqueConstraints = { 
		@UniqueConstraint(columnNames = { "id", "perfil_acesso_id", "permissao_id" })
})
public class PerfilAcessoPermissao extends AbstractEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3336890072300590528L;
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@ManyToOne(optional = false)
	private PerfilAcesso perfilAcesso;
	/**
	 * 
	 */
	@ManyToOne(optional = false)
	private Permissao permissao;

	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * @param id
	 * @param perfilAcesso
	 * @param permissao
	 */
	public PerfilAcessoPermissao( Long id, PerfilAcesso perfilAcesso, Permissao permissao )
	{
		super( id );
		this.perfilAcesso = perfilAcesso;
		this.permissao = permissao;
	}

	/**
	 * 
	 */
	public PerfilAcessoPermissao()
	{
		super();
	}

	/**
	 * @param id
	 */
	public PerfilAcessoPermissao( Long id )
	{
		super( id );
	}

	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
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

}
