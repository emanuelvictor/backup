/**
 * 
 */
package br.mil.mar.dabm.autenticador.domain.entity.aplicativo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@DataTransferObject(javascript = "Permissao")
@Table(uniqueConstraints =
{ @UniqueConstraint(columnNames =
{ "id", "identificador", "aplicativo_id", "permissao_superior_id" }) })
public class Permissao extends AbstractEntity
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
	private List<Permissao> permissoesInferiores;
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
	 * @param id
	 */
	public Permissao( Long id )
	{
		super( id );
	}
	
	

	/**
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
	 * @param id
	 * @param identificador
	 * @param permissaoSuperior
	 * @param aplicativo
	 */
	public Permissao( Long id, String identificador, Permissao permissaoSuperior, List<Permissao> permissoesInferiores)
	{
		super( id );
		this.identificador = identificador;
		this.permissaoSuperior = permissaoSuperior;
		this.permissoesInferiores = permissoesInferiores;
	}

	/**
	 * @param id
	 * @param identificador
	 * @param permissaoSuperior
	 */
	public Permissao( Long id, String identificador, Long permissaoSuperiorId )
	{
		super( id );
		this.identificador = identificador;
		this.permissaoSuperior = new Permissao( permissaoSuperiorId );
	}
	/**
	 * @param id
	 * @param identificador
	 * @param permissaoSuperior
	 */
	public Permissao( Long id, String identificador, List<Permissao> permissoesInferiores)
	{
		super( id );
		this.identificador = identificador;
		this.permissoesInferiores = permissoesInferiores;
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
	 * @return the permissao
	 */
	public final List<Permissao> getPermissao()
	{
		return permissoesInferiores;
	}

	/**
	 * @param permissao the permissao to set
	 */
	public final void setPermissao( List<Permissao> permissoesInferiores )
	{
		this.permissoesInferiores = permissoesInferiores;
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

}
