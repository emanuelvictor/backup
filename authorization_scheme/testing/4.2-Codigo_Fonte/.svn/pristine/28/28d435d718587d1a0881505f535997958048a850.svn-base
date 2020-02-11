package br.mil.mar.dabm.autenticador.domain.entity.usuario;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import br.com.eits.common.domain.entity.AbstractEntity;
import br.mil.mar.dabm.singra.domain.entity.OrganizacaoMilitar;
import br.mil.mar.dpmm.rh.domain.entity.Pessoa;

/**
 * 
 * @since 02/06/2014
 * @version 1.0
 * @category
 
 */
@Entity
@Audited
@DataTransferObject(javascript = "Usuario")
public class Usuario extends AbstractEntity implements UserDetails
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -453259664329431429L;
	/**
	* 
	*/
	public static final String FOTO_FOLDER = "usuarios/%d/foto";
	/**
	* 
	*/
	public static final String FOTO_PATH = FOTO_FOLDER + "/original";
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
	// Basic
	/**
	 * 
	 */
	@NotEmpty
	@Column(nullable = false, length = 144)
	private String nomeCompleto;
	/**
	 * Coluna de pessoa, será uma entidade transiente pois virá da base do singra
	 * No futuro não será transiente e sim a ligação direta no banco
	 */
	@Transient
	private Pessoa pessoa;
	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false, length = 50, unique = true)
	private String login;
	/**
	 * 
	 */
	@NotBlank
	@Length(min = 8)
	@Column(nullable = false, length = 255)
	private String senha;
	/**
	 * O hibernate valida o email e este é uma chave única no banco, porém não é obrigatória.
	 */
	@Email(message = "Email inválido")
	@Column(length = 100, unique = true)
	private String email;
	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataAlteracaoSenha;
	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataBloqueio;
	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataDesbloqueio;
	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataExclusao;
	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Calendar dataExpiracaoSenha;
	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataUltimoAcesso;
	/**
	 * 
	 */
	@Column(nullable = true)
	private Boolean alterarSenha;

	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public Usuario()
	{
	}

	/**
	 * 
	 * @param id
	 */
	public Usuario( Long id )
	{
		super( id );
	}

	/**
	 * 
	 * Construtor com os campos obrigaótrios
	 * 
	 * @param id
	 * @param nomeCompleto
	 * @param senha
	 */
	public Usuario( Long id, String nomeCompleto, String login, String senha )
	{
		super( id );
		this.nomeCompleto = nomeCompleto;
		this.login = login;
		this.senha = senha;
	}

	/**
	 * 
	 * @param id
	 * @param pessoa
	 * @param nomeCompleto
	 * @param login
	 * @param email
	 * @param dataAlteracaoSenha
	 * @param dataBloqueio
	 * @param dataDesbloqueio
	 * @param dataExclusao
	 * @param dataExpiracaoSenha
	 * @param dataUltimoAcesso
	 * @param alterarSenha
	 */
	public Usuario( Long id, Pessoa pessoa, String nomeCompleto, String login, String email, Calendar dataAlteracaoSenha, Calendar dataBloqueio, Calendar dataDesbloqueio, Calendar dataExclusao, Calendar dataExpiracaoSenha, Calendar dataUltimoAcesso, Boolean alterarSenha )
	{
		super( id );
		this.setPessoa( pessoa );
		this.setNomeCompleto( nomeCompleto );
		this.setLogin( login );
		this.setEmail( email );
		this.setDataBloqueio( dataBloqueio );
		this.setDataDesbloqueio( dataDesbloqueio );
		this.setDataExclusao( dataExclusao );
		this.setAlterarSenha( alterarSenha );
		this.setAlterarSenha( alterarSenha );
		this.setDataAlteracaoSenha( dataAlteracaoSenha );
		this.setDataExpiracaoSenha( dataExpiracaoSenha );
		this.setDataUltimoAcesso( dataUltimoAcesso );
	}

	/*-------------------------------------------------------------------
	 *							BEHAVIORS
	 *-------------------------------------------------------------------*/

	/**
	 * @param dataBloqueio the dataBloqueio to set
	 */
	public void setDataBloqueio( Calendar dataBloqueio )
	{
		if ( dataDesbloqueio != null && dataBloqueio != null ) Assert.isTrue( dataBloqueio.before( dataDesbloqueio ), "A data de bloqueio não pode ser posterior a data de desbloqueio" );
		
		this.dataBloqueio = dataBloqueio;
	}

	/**
	 * @param dataDesbloqueio the dataDesbloqueio to set
	 */
	public void setDataDesbloqueio( Calendar dataDesbloqueio )
	{
//		Assert.notNull( this.dataBloqueio, "A data de desbloqueio não pode ser configurada antes da data de bloqueio" );

		if ( dataDesbloqueio != null && dataBloqueio != null ) Assert.isTrue( dataDesbloqueio.after( dataBloqueio ), "A data de desbloqueio não pode ser configurada antes da data de bloqueio" );
		
		this.dataDesbloqueio = dataDesbloqueio;
	}

	/**
	 * 
	 */
	@Override
	@Transient
	public Set<GrantedAuthority> getAuthorities()
	{
		// Apenas exemplo de role
		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add( new SimpleGrantedAuthority( "ADMINISTRADOR" ) );
		return authorities;
	}

	/**
	 * 
	 */
	@Override
	@Transient
	public boolean isAccountNonExpired()
	{
		return this.dataExpiracaoSenha != null && Calendar.getInstance().before( this.dataExpiracaoSenha ) || this.dataExpiracaoSenha == null;
	}

	/**
	 * 
	 */
	@Override
	@Transient
	public boolean isAccountNonLocked()
	{
		return !( this.dataBloqueio != null && this.dataBloqueio.before( Calendar.getInstance() ) && ( ( this.dataDesbloqueio != null && this.dataDesbloqueio.after( Calendar.getInstance() ) ) || this.dataDesbloqueio == null ) );
	}

	/**
	 * 
	 */
	@Override
	@Transient
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	/**
	 * 
	 */
	@Override
	@Transient
	public boolean isEnabled()
	{
		return this.dataExclusao == null && this.isAccountNonExpired() && this.isAccountNonLocked();
	}

	/**
	 * 
	 */
	public void desbloquear()
	{
		this.dataBloqueio = null;
		this.dataDesbloqueio = null;
//		this.dataExpiracaoSenha = null;
	}

	/**
	 * @param pessoa the pessoa to set
	 */
	public String getNomeCompleto()
	{
		return this.nomeCompleto == null && this.pessoa != null ? this.pessoa.getNomeCompleto() : this.nomeCompleto;
	}

	/**
	 * @param nomeCompleto the nomeCompleto to set
	 */
	public void setNomeCompleto( String nomeCompleto )
	{
		this.nomeCompleto = nomeCompleto;
		if ( this.pessoa != null ) this.pessoa.setNomeCompleto( nomeCompleto );
	}

	/**
	 * 
	 * @param pessoa
	 */
	public void setPessoa( Pessoa pessoa )
	{
		this.pessoa = pessoa;
		if ( pessoa != null ) this.nomeCompleto = pessoa.getNomeCompleto();
	}

	/**
	 * @return the pessoa
	 */
	public Pessoa getPessoa()
	{
		return pessoa;
	}

	/**
	 * 
	 * @return
	 */
	public OrganizacaoMilitar getOrganizacaoMilitar()
	{
		// Se a pessoa for null retornar null se não retornar a OM da pessoa
		return this.pessoa != null ? this.pessoa.getOrganizacaoMilitar() : null;
	}

	/**
	 * @param dataExclusao the dataExclusao to set
	 */
	public void setDataExclusao( Calendar dataExclusao )
	{
		this.dataExclusao = dataExclusao;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	@Transient
	public String getPassword()
	{
		return this.senha;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	@Transient
	public String getUsername()
	{
		return this.login;
	}

	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail( String email )
	{
		this.email = email;
	}

	/**
	 * @return the senha
	 */
	public String getSenha()
	{
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha( String senha )
	{
		this.senha = senha;
	}

	/**
	 * @return the login
	 */
	public String getLogin()
	{
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin( String login )
	{
		this.login = login;
	}

	/**
	 * @return the dataAlteracaoSenha
	 */
	public Calendar getDataAlteracaoSenha()
	{
		return dataAlteracaoSenha;
	}

	/**
	 * @param dataAlteracaoSenha the dataAlteracaoSenha to set
	 */
	public void setDataAlteracaoSenha( Calendar dataAlteracaoSenha )
	{
		this.dataAlteracaoSenha = dataAlteracaoSenha;
	}

	/**
	 * @return the dataBloqueio
	 */
	public Calendar getDataBloqueio()
	{
		return dataBloqueio;
	}

	/**
	 * @return the dataDesbloqueio
	 */
	public Calendar getDataDesbloqueio()
	{
		return dataDesbloqueio;
	}

	/**
	 * @return the dataExclusao
	 */
	public Calendar getDataExclusao()
	{
		return dataExclusao;
	}

	/**
	 * @return the dataExpiracaoSenha
	 */
	public Calendar getDataExpiracaoSenha()
	{
		return dataExpiracaoSenha;
	}

	/**
	 * @param dataExpiracaoSenha the dataExpiracaoSenha to set
	 */
	public void setDataExpiracaoSenha( Calendar dataExpiracaoSenha )
	{
		this.dataExpiracaoSenha = dataExpiracaoSenha;
	}

	/**
	 * @return the dataUltimoAcesso
	 */
	public Calendar getDataUltimoAcesso()
	{
		return dataUltimoAcesso;
	}

	/**
	 * @param dataUltimoAcesso the dataUltimoAcesso to set
	 */
	public void setDataUltimoAcesso( Calendar dataUltimoAcesso )
	{
		this.dataUltimoAcesso = dataUltimoAcesso;
	}

	/**
	 * @return the alterarSenha
	 */
	public Boolean getAlterarSenha()
	{
		return alterarSenha;
	}

	/**
	 * @param alterarSenha the alterarSenha to set
	 */
	public void setAlterarSenha( Boolean alterarSenha )
	{
		this.alterarSenha = alterarSenha;
	}
}