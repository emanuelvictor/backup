/**
 * 
 */
package br.mil.mar.dabm.common.domain.entity.usuario;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;

import br.com.eits.common.domain.entity.AbstractEntity;
import br.mil.mar.dabm.common.domain.entity.aplicativo.PerfilAcesso;
import br.mil.mar.dabm.common.domain.entity.aplicativo.Permissao;

/**
 * @author emanuelvictor
 *
 */
@Entity
@Audited
@DataTransferObject(javascript = "UsuarioAplicativo")
@Table(uniqueConstraints =
{ @UniqueConstraint(columnNames =
{"perfil_acesso_id", "usuario_id" }) })
public class PerfilUsuarioAplicativo extends AbstractEntity
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4376892322183336654L;

	/**
	 * 
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private PerfilAcesso perfilAcesso;
	/**
	 * 
	 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private Usuario usuario;

	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public PerfilUsuarioAplicativo()
	{
		super();
	}

	/**
	 * @param id
	 */
	public PerfilUsuarioAplicativo( Long id )
	{
		super( id );
	}

	/**
	 * 
	 * @param id
	 * @param usuario
	 * @param perfilAcesso
	 */
	public PerfilUsuarioAplicativo( Long id, Usuario usuario, PerfilAcesso perfilAcesso )
	{
		super( id );
		this.perfilAcesso = perfilAcesso;
		this.usuario = usuario;
	}

	/*-------------------------------------------------------------------
	 *							BEHAVIORS
	 *-------------------------------------------------------------------*/

	/**
	 * @return todas as authorities desse serviço
	 */
	public Set<Permissao> getPermissoes()
	{
		return this.getPerfilAcesso().getPermissoes();
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
