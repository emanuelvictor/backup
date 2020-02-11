/**
 * 
 */
package br.mil.mar.dpmm.rh.domain.entity;

import org.hibernate.validator.constraints.NotBlank;

import br.com.eits.common.domain.entity.AbstractEntity;
import br.mil.mar.dabm.autenticador.domain.entity.usuario.Usuario;
import br.mil.mar.dabm.singra.domain.entity.OrganizacaoMilitar;

/**
 * @author emanuelvictor
 *
 */
public class Pessoa extends AbstractEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9126421676422256092L;
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	private OrganizacaoMilitar organizacaoMilitar;
	/**
	 * 
	 */
	private String cpf;
	/**
	 * 
	 */
	@NotBlank
	private String nip;
	/**
	 * 
	 */
	@NotBlank(message = "O nome não pode estar em branco")
	private String nomeCompleto;
	/**
	 * 
	 */
	@NotBlank(message = "O nome de guerra não pode estar em branco")
	private String nomeGuerra;
	/**
	 * 
	 */
	@NotBlank(message = "O posto de graduação não pode estar em branco")
	private String postoGraduacao;

	/**
	 * 
	 */
	private Usuario usuario;

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
		super( id );
	}

	/**
	 * 
	 * @param id
	 * @param organizacaoMilitar
	 * @param cpf
	 * @param nip
	 * @param nomeCompleto
	 * @param nomeGuerra
	 * @param postoGraduacao
	 * @param usuario
	 */
	public Pessoa( Long id, OrganizacaoMilitar organizacaoMilitar, String cpf, String nip, String nomeCompleto, String nomeGuerra, String postoGraduacao, Usuario usuario )
	{
		super( id );
		this.organizacaoMilitar = organizacaoMilitar;
		this.cpf = cpf;
		this.nip = nip;
		this.nomeCompleto = nomeCompleto;
		this.nomeGuerra = nomeGuerra;
		this.postoGraduacao = postoGraduacao;
		this.usuario = usuario;
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
	 * @return the nip
	 */
	public String getNip()
	{
		return nip;
	}

	/**
	 * @param nip the nip to set
	 */
	public void setNip( String nip )
	{
		this.nip = nip;
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
	 * @return the postoGraduacao
	 */
	public String getPostoGraduacao()
	{
		return postoGraduacao;
	}

	/**
	 * @param postoGraduacao the postoGraduacao to set
	 */
	public void setPostoGraduacao( String postoGraduacao )
	{
		this.postoGraduacao = postoGraduacao;
	}

}
