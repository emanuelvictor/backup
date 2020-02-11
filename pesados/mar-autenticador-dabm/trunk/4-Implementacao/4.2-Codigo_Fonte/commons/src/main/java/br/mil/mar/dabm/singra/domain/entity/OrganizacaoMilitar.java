/**
 * 
 */
package br.mil.mar.dabm.singra.domain.entity;

import java.io.Serializable;

import org.directwebremoting.annotations.DataTransferObject;

import br.mil.mar.dpmm.rh.domain.entity.Pessoa;

/**
 * @author emanuelvictor
 *
 */
@DataTransferObject(javascript = "OrganizacaoMilitar")
public class OrganizacaoMilitar implements Serializable
{

	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	private static final long serialVersionUID = -6287009724696707920L;
	/**
	 * 
	 */
	private Long id;

	/**
	 * 
	 */
	private String nome;

	/**
	 * 
	 */
	private OrganizacaoMilitar organizacaoMilitarSuperior;

	/**
	 * 
	 */
	private Pessoa administrador;

	/**
	 * 
	 */
	private String sigla;
	/**
	 * 
	 */
	private String telefone;

	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public OrganizacaoMilitar()
	{
		super();
	}

	/**
	 * @param id
	 */
	public OrganizacaoMilitar( Long id )
	{
		this.setId( id );
	}

	/**
	 * 
	 * @param id
	 * @param nome
	 * @param organizacaoMilitarSuperior
	 * @param administrador
	 * @param sigla
	 */
	public OrganizacaoMilitar( Long id, String nome, OrganizacaoMilitar organizacaoMilitarSuperior, Pessoa administrador, String sigla, String telefone )
	{
		this.setId( id );
		this.nome = nome;
		this.setOrganizacaoMilitarSuperior( organizacaoMilitarSuperior );
		this.administrador = administrador;
		this.sigla = sigla;
		this.telefone = telefone;
	}

	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/

	/**
	 * @param administrador the administrador to set
	 */
	public void setAdministrador( Pessoa administrador )
	{
		this.administrador = administrador;
	}

	/**
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome( String nome )
	{
		this.nome = nome;
	}

	/**
	 * @return the sigla
	 */
	public String getSigla()
	{
		return sigla;
	}

	/**
	 * @param sigla the sigla to set
	 */
	public void setSigla( String sigla )
	{
		this.sigla = sigla;
	}

	/**
	 * @return the id
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId( Long id )
	{
		this.id = id;
	}

	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone( String telefone )
	{
		this.telefone = telefone;
	}

	/**
	 * @param organizacaoMilitarSuperior the organizacaoMilitarSuperior to set
	 */
	public void setOrganizacaoMilitarSuperior( OrganizacaoMilitar organizacaoMilitarSuperior )
	{
		// A organização militar não pode ter como organização militar superior ela mesma
		this.organizacaoMilitarSuperior = organizacaoMilitarSuperior;
	}

	/*-------------------------------------------------------------------
	 *							BEHAVIORS
	 *-------------------------------------------------------------------*/

	/**
	 * @return the administrador
	 */
	public Pessoa getAdministrador()
	{
		if ( this.administrador != null && this.organizacaoMilitarSuperior != null )
		{
			return this.organizacaoMilitarSuperior.getAdministrador();
		}
		return administrador;
	}

	/**
	 * @return the telefone
	 */
	public String getTelefone()
	{
		if ( this.getOrganizacaoMilitarSuperior() != null )
		{
			return telefone == null ? this.getOrganizacaoMilitarSuperior().getTelefone() : this.telefone;
		}
		else return telefone;

	}

	/**
	 * @return the organizacaoMilitarSuperior
	 */
	public OrganizacaoMilitar getOrganizacaoMilitarSuperior()
	{
		if ( this.administrador == null && this.organizacaoMilitarSuperior != null )
		{
			return this.organizacaoMilitarSuperior;
		}
		return organizacaoMilitarSuperior;

	}

}
