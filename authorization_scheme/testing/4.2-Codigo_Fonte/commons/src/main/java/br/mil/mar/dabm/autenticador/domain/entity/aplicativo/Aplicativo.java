package br.mil.mar.dabm.autenticador.domain.entity.aplicativo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;
import org.junit.Assert;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import br.com.eits.common.domain.entity.AbstractEntity;
import br.com.eits.common.infrastructure.jcr.MetaFile;

/**
 * 
 * @author rodrigo
 */
@Entity
@Audited
@DataTransferObject(javascript = "Aplicativo")
@Table( uniqueConstraints =
{ @UniqueConstraint(columnNames =
{ "id", "versao_estavel_id" }) })
public class Aplicativo extends AbstractEntity implements ClientDetails
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -453259665329431429L;

	/**
	* 
	*/
	public static final String ICONE_FOLDER = "aplicativos/%d/icone";

	/**
	* 
	*/
	public static final String ICONE_PATH = ICONE_FOLDER + "/original";
	/**
	* 
	*/
	public static final String[] ICONE_MIME_TYPES = new String[]
	{ MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE };
	/**
	* 
	*/
	public static final int ICONE_MAX_SIZE = 5 * 1024 * 1024;

	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Column(length = 144)
	private String descricao;
	/**
	 * 
	 */
	@NotNull(message = "O endereço não pode estar em branco")
	@Column(nullable = false)
	private String endereco;
	/**
	 * 
	 */
	@NotEmpty(message = "O nome não pode estar em branco")
	@Column(nullable = false, length = 144)
	private String nome;
	
	/**
	 * 
	 */
	@Column(unique = true)
	private String refreshToken;
	
	/**
	 * Codigo identificador único do aplicativo utilizado para as APIs TODO (ClientId?)
	 */
	@Column(length = 255, unique = true)
	private String codigo;

	/**
	 * 
	 */
	@Column(length = 255)
	private String mensagemDesativacao;
	
	/**
	 * 
	 */
	@Column(nullable = false)
	@ElementCollection(targetClass = TipoAcesso.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "tipo_acesso_aplicativo", joinColumns = @JoinColumn(name = "aplicativo_id"))
	private Set<TipoAcesso> tiposAcessoAplicativo = new HashSet<>();
	/**
	 * 
	 */
	@NotNull(message = "Informe se o aplicativo tem perfis dinâmicos ou estáticos")
	@Column(nullable = false)
	private Boolean perfisDinamicos;
	/**
	 * 
	 */
	@NotNull(message = "Informe o ativo do aplicativo")
	@Column(nullable = false)
	private Boolean ativo;
	
//	/**
//	 * Mapeamento de lista para retornar todos os perfis de acesso deste aplicativo 
//	 */
//	@OneToMany(targetEntity=PerfilAcesso.class, mappedBy="aplicativo",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<PerfilAcesso> perfisAcesso;
//	/**
//	 * @return the perfisAcesso
//	 */
//	public List<PerfilAcesso> getPerfisAcesso()
//	{
//		return perfisAcesso;
//	}
//	/**
//	 * @param perfisAcesso the perfisAcesso to set
//	 */
//	public void setPerfisAcesso( List<PerfilAcesso> perfisAcesso )
//	{
//		this.perfisAcesso = perfisAcesso;
//	}
	/**
	 * 
	 */
	@Column(length = 144)
	private String versao;
	/**
	 * 
	 */
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	private Aplicativo versaoEstavel;

	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public Aplicativo()
	{
	}

	/**
	 * 
	 * @param id
	 */
	public Aplicativo( Long id )
	{
		super( id );
	}

	/**
	 * @param id
	 * @param nome
	 * @param ativo
	 * @param perfisDinamicos
	 * @param refreshToken
	 * @param versao
	 * @param descricao
	 * @param versaoEstavel
	 * @param endereco
	 */
	public Aplicativo( Long id, String nome, Boolean ativo, Boolean perfisDinamicos, String codigo, String versao, String descricao, Aplicativo versaoEstavel, String endereco, String mensagemDesativacao, String refreshToken )
	{
		super( id );
		this.refreshToken = refreshToken;
		this.nome = nome;
		this.ativo = ativo;
		this.perfisDinamicos = perfisDinamicos;
		this.codigo = codigo;
		this.versao = versao;
		this.descricao = descricao;
		this.setVersaoEstavel( versaoEstavel );
		this.mensagemDesativacao = mensagemDesativacao;
		this.endereco = endereco;
	}

	/**
	 * @param id
	 * @param nome
	 * @param ativo
	 */
	public Aplicativo( Long id, String nome, Boolean ativo )
	{
		super( id );
		this.nome = nome;
		this.ativo = ativo;
	}
	

	/*-------------------------------------------------------------------
	 *							BEHAVIORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @return um path ex.: aplicativos/1/icone/original
	 */
	public MetaFile getIcone()
	{
		return new MetaFile( String.format(ICONE_PATH, id) );
	}
	
	/**
	 * @param versaoEstavel the versaoEstavel to set
	 */
	public void setVersaoEstavel(final Aplicativo versaoEstavel )
	{
		
		if ( versaoEstavel != null )
		{
			// A versão estável do aplicativo não pode ter como versão estavel o próprio aplicativo
			Assert.assertNotEquals("A versão estável do aplicativo não pode ser ele mesmo", id, versaoEstavel.getId() );
			this.versaoEstavel = versaoEstavel;
		}
		else
		{
			this.versaoEstavel = versaoEstavel;
		}
	}
	
	@Override
	public String getClientSecret()
	{
		return this.getCodigo();
	}

	@Override
	public String getClientId()
	{
		return this.getId().toString();
	}
	
	@Override
	public Set<String> getScope()
	{
		//TODO IMPLEMENTAR (deverá ser dinâmico)
		final Set<String> scopes = new HashSet<>();
		scopes.add( "ADMINISTRATOR" );
		scopes.add( "PUBLIC" );
		return scopes;
	}
	
	@Override
	public Set<String> getAuthorizedGrantTypes()
	{
		final Set<String> authorizedGrantTypes = new HashSet<>();
		this.tiposAcessoAplicativo.forEach( tipoAcessoAplicativo -> authorizedGrantTypes.add( tipoAcessoAplicativo.getTipo() ) );
		return authorizedGrantTypes;
	}
	
	@Override
	public boolean isSecretRequired()
	{// deverá ser reimplemntado para o caso de clientes customizados, não sendo mais estático
		return false;
	}

	@Override
	public boolean isScoped()
	{// deverá ser reimplemntado para o caso de clientes customizados, não sendo mais estático
		return false;
	}
	
	@Override
	public boolean isAutoApprove( String scope )
	{// deverá ser reimplemntado para o caso de clientes customizados, não sendo mais estático
		return true;
	}
	
	@Override
	public Integer getAccessTokenValiditySeconds()
	{// deverá ser reimplemntado para o caso de clientes customizados, não sendo mais estático
		return 99999999;
	}
	
	@Override
	public Integer getRefreshTokenValiditySeconds()
	{// deverá ser reimplemntado para o caso de clientes customizados, não sendo mais estático
		return 99999999;
	}

	//Desnecessários
	@Override
	public Set<String> getRegisteredRedirectUri()
	{
		return null;
	}

	@Override
	public Map<String, Object> getAdditionalInformation()
	{
		return null;
	}
	
	@Override
	public Collection<GrantedAuthority> getAuthorities()
	{
		final Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add( new SimpleGrantedAuthority( "ADMINISTRATOR" ) );
		authorities.add( new SimpleGrantedAuthority( "PUBLIC" ) );
		return authorities;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.oauth2.provider.ClientDetails#getResourceIds()
	 */
	@Override
	public Set<String> getResourceIds()
	{
		return null;
	}

	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
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
	public void setNome(final String nome )
	{
		this.nome = nome;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo()
	{
		return codigo;
	}

	/**
	 * @return the mensagemDesativacao
	 */
	public String getMensagemDesativacao()
	{
		return mensagemDesativacao;
	}

	/**
	 * @param mensagemDesativacao the mensagemDesativacao to set
	 */
	public void setMensagemDesativacao(final String mensagemDesativacao )
	{
		this.mensagemDesativacao = mensagemDesativacao;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo( final String codigo )
	{
		this.codigo = codigo;
	}

	/**
	 * @return the tiposAcessoAplicativo
	 */
	public Set<TipoAcesso> getTiposAcessoAplicativo()
	{
		return tiposAcessoAplicativo;
	}

	/**
	 * @param tiposAcessoAplicativo the tiposAcessoAplicativo to set
	 */
	public void setTiposAcessoAplicativo(final Set<TipoAcesso> tiposAcessoAplicativo )
	{
		this.tiposAcessoAplicativo = tiposAcessoAplicativo;
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
	public void setDescricao(final String descricao )
	{
		this.descricao = descricao;
	}

	/**
	 * @return the endereco
	 */
	public String getEndereco()
	{
		return endereco;
	}

	/**
	 * @param endereco the endereco to set
	 */
	public void setEndereco( final String endereco )
	{
		this.endereco = endereco;
	}

	/**
	 * @return the perfisDinamicos
	 */
	public Boolean getPerfisDinamicos()
	{
		return perfisDinamicos;
	}

	/**
	 * @param perfisDinamicos the perfisDinamicos to set
	 */
	public void setPerfisDinamicos(final Boolean perfisDinamicos )
	{
		this.perfisDinamicos = perfisDinamicos;
	}

	/**
	 * @return the ativo
	 */
	public Boolean getAtivo()
	{
		return ativo;
	}

	/**
	 * @param ativo the ativo to set
	 */
	public void setAtivo(final Boolean ativo )
	{
		this.ativo = ativo;
	}

	/**
	 * @return the versao
	 */
	public String getVersao()
	{
		return versao;
	}

	/**
	 * @param versao the versao to set
	 */
	public void setVersao(final String versao )
	{
		this.versao = versao;
	}

	/**
	 * @return the versaoEstavel
	 */
	public Aplicativo getVersaoEstavel()
	{
		return versaoEstavel;
	}

	/**
	 * @return the refreshToken
	 */
	public String getRefreshToken()
	{
		return refreshToken;
	}

	/**
	 * @param refreshToken the refreshToken to set
	 */
	public void setRefreshToken(final String refreshToken )
	{
		this.refreshToken = refreshToken;
	}

}