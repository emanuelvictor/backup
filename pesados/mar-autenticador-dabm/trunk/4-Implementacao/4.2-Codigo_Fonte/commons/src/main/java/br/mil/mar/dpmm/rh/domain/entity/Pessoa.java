/**
 * 
 */
package br.mil.mar.dpmm.rh.domain.entity;

import java.io.Serializable;

import org.directwebremoting.annotations.DataTransferObject;

import br.mil.mar.dabm.singra.domain.entity.OrganizacaoMilitar;

/**
 * @author emanuelvictor
 *
 */
@DataTransferObject(javascript = "Pessoa")
public class Pessoa implements Serializable
{

	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/

	/**
	 * 
	 */
	private static final long serialVersionUID = -1759095005210717271L;
	/**
	 * 
	 */
	private OrganizacaoMilitar organizacaoMilitar;
	/**
	 * 
	 */
	private Long id;

	/**
	 * 
	 */
	private String cpf;
	/**
	 * 
	 */
	private String nip;
	/**
	 * 
	 */
	private String nomeCompleto;
	/**
	 * 
	 */
	private String nomeGuerra;

	/**
	 * 
	 */
	private String email;
	/**
	 * 
	 */
	private String postoGraduacao;

	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public Pessoa()
	{
		super();
	}

	/**
	 * @param id
	 */
	public Pessoa( Long id )
	{
		this.setId( id );
	}

	/**
	 * 
	 * @param id
	 * @param organizacaoMilitar
	 * @param cpf
	 * @param nip
	 * @param nomeCompleto
	 * @param email
	 * @param nomeGuerra
	 * @param postoGraduacao
	 */
	public Pessoa( Long id, OrganizacaoMilitar organizacaoMilitar, String cpf, String nip, String nomeCompleto, String email, String nomeGuerra, String postoGraduacao )
	{
		this.setId( id );
		this.setOrganizacaoMilitar( organizacaoMilitar );
		this.setCpf( cpf );
		this.setNomeCompleto( nomeCompleto );
		this.setNomeGuerra( nomeGuerra );
		this.setPostoGraduacao( postoGraduacao );
		this.setEmail( email );
		this.setNip( nip );
	}

	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
	/**
	 * @return the organizacaoMilitar
	 */
	public OrganizacaoMilitar getOrganizacaoMilitar()
	{
		return organizacaoMilitar;
	}

	/**
	 * @param organizacaoMilitar the organizacaoMilitar to set
	 */
	public void setOrganizacaoMilitar( OrganizacaoMilitar organizacaoMilitar )
	{
		this.organizacaoMilitar = organizacaoMilitar;
	}

	/**
	 * @return the cpf
	 */
	public String getCpf()
	{
		return cpf;
	}

	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf( String cpf )
	{
		this.cpf = cpf;
	}

	/**
	 * @return the nomeCompleto
	 */
	public String getNomeCompleto()
	{
		return nomeCompleto;
	}

	/**
	 * @param nomeCompleto the nomeCompleto to set
	 */
	public void setNomeCompleto( String nomeCompleto )
	{
		this.nomeCompleto = nomeCompleto;
	}

	/**
	 * @return the nomeGuerra
	 */
	public String getNomeGuerra()
	{
		return nomeGuerra;
	}

	/**
	 * @param nomeGuerra the nomeGuerra to set
	 */
	public void setNomeGuerra( String nomeGuerra )
	{
		this.nomeGuerra = nomeGuerra;
	}

	/**
	 * @param postoGraduacao the postoGraduacao to set
	 */
	public void setPostoGraduacao( String postoGraduacao )
	{
		this.postoGraduacao = postoGraduacao;
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

	/*-------------------------------------------------------------------
	 *							BEHAVIORS
	 *-------------------------------------------------------------------*/

	/**
	 * @return the telefone
	 */
	public String getTelefone()
	{
		return this.getOrganizacaoMilitar() != null ? this.getOrganizacaoMilitar().getTelefone() : null;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		if ( email == null && this.organizacaoMilitar.getAdministrador() != null )
		{
			return this.getOrganizacaoMilitar().getAdministrador().getEmail();
		}
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail( String email )
	{
		if ( email != null )
		{
			email = email.toLowerCase();
		}
		this.email = email;
	}

	/**
	 * @return the postoGraduacao
	 */
	public String getPostoGraduacao()
	{
		if ( ( postoGraduacao == null ) && ( this.organizacaoMilitar != null ) )
		{
			return this.organizacaoMilitar.getAdministrador().getPostoGraduacao();
		}
		return postoGraduacao;
	}

	/**
	 * @return the nip
	 */
	public String getNip()
	{
		if ( nip != null )
		{
			nip = nip.toLowerCase();
		}
		return nip;
	}

	/**
	 * @param nip the nip to set
	 */
	public void setNip( String nip )
	{
		if ( nip != null )
		{
			nip = nip.toLowerCase();
		}
		this.nip = nip;
	}

}
