package br.mil.mar.dabm.common.domain.entity.aplicativo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.eits.common.domain.entity.AbstractEntity;
import br.mil.mar.dabm.common.domain.entity.usuario.PerfilUsuarioAplicativo;

/**
 * @author emanuelvictor
 *
 */
@Entity
@Audited
@DataTransferObject(javascript = "PerfilAcesso")
@Table(uniqueConstraints =
{ @UniqueConstraint(columnNames =
		{ "nome", "aplicativo_id" }) })
public class PerfilAcesso extends AbstractEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 137041957827618469L;
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Column(nullable = false)
	private Boolean editavel;
	/**
	 * 
	 */
	@Column(nullable = false)
	private Boolean removivel;
	/**
	 * 
	 */
	@NotEmpty(message = "O nome do perfil não pode estar em branco")
	@Column(length = 144, nullable = false)
	private String nome;
	/**
	 * 
	 */
	@Column(length = 144)
	private String descricao;
	/**
	 * 
	 */
	@ManyToOne(optional = true)
	private Aplicativo aplicativo;
	/**
	 * 
	 */
	@Column(nullable = true)
	private Integer diasExpiracaoSenha;
	/**
	 * 
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "perfilAcesso", cascade = CascadeType.REMOVE)
	private Set<PerfilAcessoPermissao> perfilAcessoPermissoes;
	/**
	 * Lista mapeada para remover todos os perfilUsuarioAplicativo quando remover o perfil de acesso
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "perfilAcesso", cascade = CascadeType.REMOVE)
	private Set<PerfilUsuarioAplicativo> perfilUsuarioAplicativos;

	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public PerfilAcesso()
	{
		super();
	}

	/**
	 * @param id
	 */
	public PerfilAcesso( Long id )
	{
		super( id );
	}

	/**
	 * 
	 * @param id
	 * @param editavel
	 * @param removivel
	 * @param nome
	 * @param descricao
	 * @param aplicativo
	 * @param diasExpiracaoSenha
	 */
	public PerfilAcesso( Long id, Boolean editavel, Boolean removivel, String nome, String descricao, Aplicativo aplicativo, Integer diasExpiracaoSenha )
	{
		super( id );
		this.setEditavel( editavel );
		this.setRemovivel( removivel );
		this.setNome( nome );
		this.setDescricao( descricao );
		this.setAplicativo( aplicativo );
		this.setDiasExpiracaoSenha( diasExpiracaoSenha );
	}

	/**
	 * 
	 * @param id
	 * @param nome
	 * @param aplicativo
	 */
	public PerfilAcesso( Long id, String nome, Aplicativo aplicativo )
	{
		super( id );
		this.setNome( nome );
		this.setAplicativo( new Aplicativo( aplicativo.getId() ) );
	}

	/*-------------------------------------------------------------------
	 *							BEHAVIORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @return
	 */
	public Set<Permissao> getPermissoes()
	{
		if ( this.getPerfilAcessoPermissoes() != null )
		{
			final Set<Permissao> authorities = new HashSet<>();
			this.getPerfilAcessoPermissoes().forEach( perfilAcessoPermissao -> authorities.add( perfilAcessoPermissao.getPermissao() ) );
			return authorities;
		}
		return new HashSet<Permissao>();
	}

	/**
	 * @param diasExpiracaoSenha the diasExpiracaoSenha to set
	 */
	public void setDiasExpiracaoSenha( Integer diasExpiracaoSenha )
	{
		if ( this.getEditavel() == null || !this.getEditavel() )
		{
			this.diasExpiracaoSenha = null;
		}
		else if ( ( diasExpiracaoSenha != null && diasExpiracaoSenha > 0 ) || ( diasExpiracaoSenha == null ) )
		{
			this.diasExpiracaoSenha = diasExpiracaoSenha;
		}
	}

	/**
	 * @param aplicativo the aplicativo to set
	 */
	public void setAplicativo( Aplicativo aplicativo )
	{
		this.aplicativo = aplicativo;
	}

	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
	/**
	 * @return the editavel
	 */
	public Boolean getEditavel()
	{
		return editavel;
	}

	/**
	 * @return the aplicativo
	 */
	public Aplicativo getAplicativo()
	{
		return aplicativo;
	}

	/**
	 * @param editavel the editavel to set
	 */
	public void setEditavel( Boolean editavel )
	{
		this.editavel = editavel;
	}

	/**
	 * @return the removivel
	 */
	public Boolean getRemovivel()
	{
		return removivel;
	}

	/**
	 * @param removivel the removivel to set
	 */
	public void setRemovivel( Boolean removivel )
	{
		this.removivel = removivel;
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
	 * @return the descricao
	 */
	public String getDescricao()
	{
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao( String descricao )
	{
		this.descricao = descricao;
	}

	/**
	 * @return the diasExpiracaoSenha
	 */
	public Integer getDiasExpiracaoSenha()
	{
		return diasExpiracaoSenha;
	}

	/**
	 * @return the perfilAcessoPermissoes
	 */
	public Set<PerfilAcessoPermissao> getPerfilAcessoPermissoes()
	{
		return perfilAcessoPermissoes;
	}

	/**
	 * @param perfilAcessoPermissoes the perfilAcessoPermissoes to set
	 */
	public void setPerfilAcessoPermissoes( Set<PerfilAcessoPermissao> perfilAcessoPermissoes )
	{
		this.perfilAcessoPermissoes = perfilAcessoPermissoes;
	}

	/**
	 * @return the perfilUsuarioAplicativo
	 */
	public final Set<PerfilUsuarioAplicativo> getPerfilUsuarioAplicativos()
	{
		return perfilUsuarioAplicativos;
	}

	/**
	 * @param perfilUsuarioAplicativo the perfilUsuarioAplicativo to set
	 */
	public final void setPerfilUsuarioAplicativos( Set<PerfilUsuarioAplicativo> perfilUsuarioAplicativo )
	{
		this.perfilUsuarioAplicativos = perfilUsuarioAplicativo;
	}

	

}
