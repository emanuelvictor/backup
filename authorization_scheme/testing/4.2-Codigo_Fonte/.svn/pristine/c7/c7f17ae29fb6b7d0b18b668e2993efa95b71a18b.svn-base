/**
 * 
 */
package br.mil.mar.dabm.autenticador.domain.entity.usuario;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;

import br.com.eits.common.domain.entity.AbstractEntity;
import br.mil.mar.dabm.autenticador.domain.entity.aplicativo.PerfilAcesso;

/**
 * @author emanuelvictor
 *
 */
@Entity
@Audited
@DataTransferObject(javascript = "UsuarioAplicativo")
@Table(uniqueConstraints =
{ @UniqueConstraint(columnNames =
{ "perfil_acesso_id", "usuario_id" }) })
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
	 * @param id
	 * @param perfilAcesso
	 * @param usuarioAplicativo
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( perfilAcesso == null ) ? 0 : perfilAcesso.hashCode() );
		result = prime * result + ( ( usuario == null ) ? 0 : usuario.hashCode() );
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj ) return true;
		if ( !super.equals( obj ) ) return false;
		if ( getClass() != obj.getClass() ) return false;
		PerfilUsuarioAplicativo other = ( PerfilUsuarioAplicativo ) obj;
		if ( perfilAcesso == null )
		{
			if ( other.perfilAcesso != null ) return false;
		}
		else if ( !perfilAcesso.equals( other.perfilAcesso ) ) return false;
		if ( usuario == null )
		{
			if ( other.usuario != null ) return false;
		}
		else if ( !usuario.equals( other.usuario ) ) return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PerfilUsuarioAplicativo [perfilAcesso=" + perfilAcesso + ", usuario=" + usuario + ", id=" + id + ", created=" + created + ", updated=" + updated + "]";
	}

}
