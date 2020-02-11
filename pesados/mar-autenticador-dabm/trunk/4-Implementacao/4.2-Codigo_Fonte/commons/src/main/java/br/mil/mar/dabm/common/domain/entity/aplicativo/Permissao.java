/**
 * 
 */
package br.mil.mar.dabm.common.domain.entity.aplicativo;

import java.beans.Transient;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;
import org.springframework.security.core.GrantedAuthority;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author emanuelvictor
 *
 */
@Entity
@Audited
@DataTransferObject(javascript = "Permissao")
@Table(uniqueConstraints =
{ @UniqueConstraint(columnNames =
		{ "identificador", "aplicativo_id", "permissao_superior_id" }) })
public class Permissao extends AbstractEntity implements GrantedAuthority
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3992546696846261194L;
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Column(length = 144, nullable = false)
	private String identificador;
	/**
	 * 
	 */
	@ManyToOne(optional = true)
	private Permissao permissaoSuperior;
	/**
	 * 
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "permissaoSuperior")
	private Set<Permissao> permissoesInferiores;
	/**
	 * 
	 */
	@ManyToOne(optional = false)
	private Aplicativo aplicativo;

	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public Permissao()
	{
		super();
	}

	/**
	 * 
	 * @param id
	 */
	public Permissao( Long id )
	{
		super( id );
	}

	/**
	 * 
	 * @param id
	 * @param identificador
	 * @param permissaoSuperior
	 * @param aplicativo
	 */
	public Permissao( Long id, String identificador, Permissao permissaoSuperior, Aplicativo aplicativo )
	{
		super( id );
		this.identificador = identificador;
		this.permissaoSuperior = permissaoSuperior;
		this.aplicativo = aplicativo;
	}

	/**
	 * 
	 * @param id
	 * @param identificador
	 * @param permissaoSuperior
	 * @param permissoesInferiores
	 */
	public Permissao( Long id, String identificador, Permissao permissaoSuperior, Set<Permissao> permissoesInferiores )
	{
		super( id );
		this.identificador = identificador;
		this.permissaoSuperior = permissaoSuperior;
		this.permissoesInferiores = permissoesInferiores;
	}

	/**
	 * 
	 * @param id
	 * @param identificador
	 * @param permissaoSuperiorId
	 */
	public Permissao( Long id, String identificador, Long permissaoSuperiorId )
	{
		super( id );
		this.identificador = identificador;
		this.permissaoSuperior = new Permissao( permissaoSuperiorId );
	}

	/**
	 * 
	 * @param id
	 * @param identificador
	 * @param permissoesInferiores
	 */
	public Permissao( Long id, String identificador, Set<Permissao> permissoesInferiores )
	{
		super( id );
		this.identificador = identificador;
		this.permissoesInferiores = permissoesInferiores;
	}

	/*-------------------------------------------------------------------
	 *							BEHAVIORS
	 *-------------------------------------------------------------------*/
	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 */
	@Override
	@Transient
	public String getAuthority()
	{
		// Se o aplicativo for null retorna null
		return this.getAplicativo() == null ? null : this.getAplicativo().getIdentificador() + this.getIdentificador( new String() );
	}

	/**
	 * 
	 * Função recursiva que retorna todas as roles de forma concatenadas
	 * 
	 * @param role
	 * @return
	 */
	public String getIdentificador( String role )
	{
		if ( this.getPermissaoSuperior() != null )
		{
			role += this.getPermissaoSuperior().getIdentificador( role );
		}
		return role += "." + this.getIdentificador();
	}

	/**
	 * limpa a permissão dos loops
	 * 
	 * @param permissao
	 * @return
	 */
	public Permissao clearPermissao( Permissao permissao )
	{
		if ( permissao != null )
		{
			permissao.setPermissoesInferiores( null );

			permissao.setAplicativo( new Aplicativo( permissao.getAplicativo().getIdentificador() ) );

			permissao.clearPermissao( this.getPermissaoSuperior() );
		}
		return permissao;
	}

	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
	/**
	 * @return the identificador
	 */
	public String getIdentificador()
	{
		return identificador;
	}

	/**
	 * @param identificador the identificador to set
	 */
	public void setIdentificador( String identificador )
	{
		this.identificador = identificador;
	}

	/**
	 * @return the permissaoSuperior
	 */
	public Permissao getPermissaoSuperior()
	{
		return permissaoSuperior;
	}

	/**
	 * @param permissaoSuperior the permissaoSuperior to set
	 */
	public void setPermissaoSuperior( Permissao permissaoSuperior )
	{
		this.permissaoSuperior = permissaoSuperior;
	}

	/**
	 * @return the aplicativo
	 */
	public Aplicativo getAplicativo()
	{
		return aplicativo;
	}

	/**
	 * @param aplicativo the aplicativo to set
	 */
	public void setAplicativo( Aplicativo aplicativo )
	{
		this.aplicativo = aplicativo;
	}

	/**
	 * @return the permissoesInferiores
	 */
	public Set<Permissao> getPermissoesInferiores()
	{
		return this.permissoesInferiores;
	}

	/**
	 * @param permissoesInferiores the permissoesInferiores to set
	 */
	public void setPermissoesInferiores( Set<Permissao> permissoesInferiores )
	{
		this.permissoesInferiores = permissoesInferiores;
	}


}
