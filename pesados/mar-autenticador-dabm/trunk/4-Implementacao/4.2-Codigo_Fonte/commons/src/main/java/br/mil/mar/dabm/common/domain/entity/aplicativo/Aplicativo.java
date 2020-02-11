package br.mil.mar.dabm.common.domain.entity.aplicativo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
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

/**
 * 
 * @author emanuelvictor
 *
 */
@Entity
@Audited
@DataTransferObject(javascript = "Aplicativo")
@Table(uniqueConstraints =
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
	@Column(nullable = false, length = 255, unique = true)
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
	 * Codigo identificador único do aplicativo utilizado para as API's
	 */
	@Column(length = 255, unique = true)
	private String identificador;

	/**
	 * 
	 */
	@Column(columnDefinition = "text")
	private String mensagemDesativacao;

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

	/**
	 * Mapeamento de lista para retornar todos os perfis de acesso deste aplicativo
	 */
	@OneToMany(targetEntity = PerfilAcesso.class, mappedBy = "aplicativo", fetch = FetchType.EAGER)
	private Set<PerfilAcesso> perfisAcesso;

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

	// Apenas exemplo de role
	/**
	 * Coluna transiente para handler de getAuthorities de aplicativo
	 */
	@Transient
	private Set<GrantedAuthority> authorities = new HashSet<>( Arrays.asList( new SimpleGrantedAuthority( "APP_ADMIN" ) ) );

	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public Aplicativo()
	{
		super();
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
	 * 
	 * @param id
	 * @param nome
	 * @param ativo
	 * @param perfisDinamicos
	 * @param identificador
	 * @param versao
	 * @param descricao
	 * @param versaoEstavel
	 * @param endereco
	 * @param mensagemDesativacao
	 * @param refreshToken
	 */
	public Aplicativo( Long id, String nome, Boolean ativo, Boolean perfisDinamicos, String identificador, String versao, String descricao, Aplicativo versaoEstavel, String endereco, String mensagemDesativacao, String refreshToken )
	{
		super( id );
		this.refreshToken = refreshToken;
		this.nome = nome;
		this.ativo = ativo;
		this.perfisDinamicos = perfisDinamicos;
		this.identificador = identificador;
		this.versao = versao;
		this.descricao = descricao;
		this.setVersaoEstavel( versaoEstavel );
		this.mensagemDesativacao = mensagemDesativacao;
		this.endereco = endereco;
	}

	/**
	 * 
	 * @param id
	 * @param nome
	 * @param ativo
	 * @param perfisDinamicos
	 * @param identificador
	 * @param versao
	 * @param descricao
	 * @param endereco
	 * @param mensagemDesativacao
	 * @param refreshToken
	 */
	public Aplicativo( Long id, String nome, Boolean ativo, Boolean perfisDinamicos, String identificador, String versao, String descricao, String endereco, String mensagemDesativacao, String refreshToken )
	{
		super( id );
		this.refreshToken = refreshToken;
		this.nome = nome;
		this.ativo = ativo;
		this.perfisDinamicos = perfisDinamicos;
		this.identificador = identificador;
		this.versao = versao;
		this.descricao = descricao;
		this.mensagemDesativacao = mensagemDesativacao;
		this.endereco = endereco;
	}

	/**
	 * 
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

	/**
	 * 
	 * @param id
	 * @param nome
	 * @param identificador
	 * @param ativo
	 * @param perfisAcesso
	 */
	public Aplicativo( Long id, String nome, String identificador, Boolean ativo, Set<PerfilAcesso> perfisAcesso )
	{
		super( id );
		this.setNome( nome );
		this.setAtivo( ativo );
		this.setIdentificador( identificador );
		this.setPerfisAcesso( perfisAcesso );
	}

	/**
	 * 
	 * @param identificador
	 */
	public Aplicativo( String identificador )
	{
		this.authorities = null;
		this.setIdentificador( identificador );
	}

	/*-------------------------------------------------------------------
	 *							BEHAVIORS
	 *-------------------------------------------------------------------*/

	/**
	 * 
	 * @param versaoEstavel
	 */
	public void setVersaoEstavel( Aplicativo versaoEstavel )
	{

		if ( versaoEstavel != null )
		{
			// A versão estável do aplicativo não pode ter como versão estavel o próprio aplicativo
			Assert.assertNotEquals( "A versão estável do aplicativo não pode ser ele mesmo", id, versaoEstavel.getId() );
			this.versaoEstavel = versaoEstavel;
		}
		else
		{
			this.versaoEstavel = versaoEstavel;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.oauth2.provider.ClientDetails#getClientId()
	 */
	@Override
	public String getClientId()
	{
		return this.getIdentificador();
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

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.oauth2.provider.ClientDetails#isSecretRequired()
	 */
	@Override
	public boolean isSecretRequired()
	{
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.oauth2.provider.ClientDetails#getClientSecret()
	 */
	@Override
	public String getClientSecret()
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.oauth2.provider.ClientDetails#isScoped()
	 */
	@Override
	public boolean isScoped()
	{
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.oauth2.provider.ClientDetails#getScope()
	 */
	@Override
	public Set<String> getScope()
	{
		final Set<String> scopes = new HashSet<>();
		scopes.add( "FULL" );
		return scopes;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.oauth2.provider.ClientDetails#getAuthorizedGrantTypes()
	 */
	@Override
	public Set<String> getAuthorizedGrantTypes()
	{

		final Set<String> authorizedGrantTypes = new HashSet<>();

		new HashSet<>( Arrays.asList( TipoAcesso.AUTHORIZATION_CODE, TipoAcesso.CLIENT_CREDENTIALS, TipoAcesso.IMPLICIT, TipoAcesso.PASSWORD, TipoAcesso.REFRESH_TOKEN ) ).forEach( tipoAcesso -> authorizedGrantTypes.add( tipoAcesso.getTipo() ) );

		return authorizedGrantTypes;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.oauth2.provider.ClientDetails#getRegisteredRedirectUri()
	 */
	@Override
	public Set<String> getRegisteredRedirectUri()
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.oauth2.provider.ClientDetails#getAuthorities()
	 */
	@Override
	@Transient
	public Set<GrantedAuthority> getAuthorities()
	{
		return authorities;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.oauth2.provider.ClientDetails#getAccessTokenValiditySeconds()
	 */
	@Override
	public Integer getAccessTokenValiditySeconds()
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.oauth2.provider.ClientDetails#getRefreshTokenValiditySeconds()
	 */
	@Override
	public Integer getRefreshTokenValiditySeconds()
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.oauth2.provider.ClientDetails#isAutoApprove(java.lang.String)
	 */
	@Override
	public boolean isAutoApprove( String scope )
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.oauth2.provider.ClientDetails#getAdditionalInformation()
	 */
	@Override
	public Map<String, Object> getAdditionalInformation()
	{
		return null;
	}

	/**
	 * Esta função só é utilizada para setar null nas authorities, para o objeto poder ser convertido para JSON
	 */
	public void setNullAuthorities()
	{
		this.authorities = null;
	}

	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @return
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * 
	 * @param nome
	 */
	public void setNome( final String nome )
	{
		this.nome = nome;
	}

	/**
	 * 
	 * @return
	 */
	public String getIdentificador()
	{
		return identificador;
	}

	/**
	 * 
	 * @return
	 */
	public String getMensagemDesativacao()
	{
		return mensagemDesativacao;
	}

	/**
	 * 
	 * @param mensagemDesativacao
	 */
	public void setMensagemDesativacao( final String mensagemDesativacao )
	{
		this.mensagemDesativacao = mensagemDesativacao;
	}

	/**
	 * 
	 * @return
	 */
	public Set<PerfilAcesso> getPerfisAcesso()
	{
		return perfisAcesso;
	}

	/**
	 * 
	 * @param perfisAcesso
	 */
	public void setPerfisAcesso( Set<PerfilAcesso> perfisAcesso )
	{
		this.perfisAcesso = perfisAcesso;
	}

	/**
	 * 
	 * @param identificador
	 */
	public void setIdentificador( final String identificador )
	{
		this.identificador = identificador;
	}

	/**
	 * 
	 * @return
	 */
	public String getDescricao()
	{
		return descricao;
	}

	/**
	 * 
	 * @param descricao
	 */
	public void setDescricao( final String descricao )
	{
		this.descricao = descricao;
	}

	/**
	 * 
	 * @return
	 */
	public String getEndereco()
	{
		return endereco;
	}

	/**
	 * 
	 * @param endereco
	 */
	public void setEndereco( final String endereco )
	{
		this.endereco = endereco;
	}

	/**
	 * 
	 * @return
	 */
	public Boolean getPerfisDinamicos()
	{
		return perfisDinamicos;
	}

	/**
	 * 
	 * @param perfisDinamicos
	 */
	public void setPerfisDinamicos( final Boolean perfisDinamicos )
	{
		this.perfisDinamicos = perfisDinamicos;
	}

	/**
	 * 
	 * @return
	 */
	public Boolean getAtivo()
	{
		return ativo;
	}

	/**
	 * 
	 * @param ativo
	 */
	public void setAtivo( final Boolean ativo )
	{
		this.ativo = ativo;
	}

	/**
	 * 
	 * @return
	 */
	public String getVersao()
	{
		return versao;
	}

	/**
	 * 
	 * @param versao
	 */
	public void setVersao( final String versao )
	{
		this.versao = versao;
	}

	/**
	 * 
	 * @return
	 */
	public Aplicativo getVersaoEstavel()
	{
		return versaoEstavel;
	}

	/**
	 * 
	 * @return
	 */
	public String getRefreshToken()
	{
		return refreshToken;
	}

	/**
	 * 
	 * @param refreshToken
	 */
	public void setRefreshToken( final String refreshToken )
	{
		this.refreshToken = refreshToken;
	}

}