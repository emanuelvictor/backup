package br.mil.mar.dabm.common.domain.entity.usuario;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import br.com.eits.common.domain.entity.AbstractEntity;
import br.mil.mar.dabm.common.domain.entity.aplicativo.Permissao;
import br.mil.mar.dabm.singra.domain.entity.OrganizacaoMilitar;
import br.mil.mar.dpmm.rh.domain.entity.Pessoa;

/**
 * 
 * @since 02/06/2014
 * @version 1.0
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
	public static final String[] FOTO_MIME_TYPES = new String[]
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
	@Column(nullable = true, length = 144)
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
	@Length(min = 6)
	@Column(nullable = false, length = 255)
	private String senha;

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

	/**
	 * O hibernate valida o email e este é uma chave única no banco, porém não é obrigatória.
	 */
	@Email(message = "Email inválido")
	@Column(length = 100, unique = true)
	private String email;

	/**
	 * 
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario")
	private Set<PerfilUsuarioAplicativo> perfisUsuarioAplicativo;

	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public Usuario()
	{
		super();
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
	 * @param id
	 * @param nomeCompleto
	 * @param login
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
		this.setEmail( email );
		this.setNomeCompleto( nomeCompleto );
		this.setLogin( login );
		this.setDataBloqueio( dataBloqueio );
		this.setDataDesbloqueio( dataDesbloqueio );
		this.setDataExclusao( dataExclusao );
		this.setAlterarSenha( alterarSenha );
		this.setDataAlteracaoSenha( dataAlteracaoSenha );
		this.setDataExpiracaoSenha( dataExpiracaoSenha );
		this.setDataUltimoAcesso( dataUltimoAcesso );
	}

	/**
	 * Puxa todos os dados menos os de pessoa
	 * 
	 * @param id
	 * @param nomeCompleto
	 * @param login
	 * @param dataAlteracaoSenha
	 * @param dataBloqueio
	 * @param dataDesbloqueio
	 * @param dataExclusao
	 * @param dataExpiracaoSenha
	 * @param dataUltimoAcesso
	 * @param alterarSenha
	 */
	public Usuario( Long id, String nomeCompleto, String login, String email, Calendar dataAlteracaoSenha, Calendar dataBloqueio, Calendar dataDesbloqueio, Calendar dataExclusao, Calendar dataExpiracaoSenha, Calendar dataUltimoAcesso, Boolean alterarSenha )
	{
		super( id );
		this.setEmail( email );
		this.setNomeCompleto( nomeCompleto );
		this.setLogin( login );
		this.setDataBloqueio( dataBloqueio );
		this.setDataDesbloqueio( dataDesbloqueio );
		this.setDataExclusao( dataExclusao );
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

		if ( dataDesbloqueio != null && dataBloqueio != null ) Assert.isTrue( dataDesbloqueio.after( dataBloqueio ), "A data de desbloqueio não pode ser configurada antes da data de bloqueio" );

		this.dataDesbloqueio = dataDesbloqueio;
	}

	/**
	 * 
	 */

	@Override
	@Transient
	public Set<Permissao> getAuthorities()
	{
		if ( this.getPerfisUsuarioAplicativo() != null )
		{
			final Set<Permissao> authorities = new HashSet<>();
			this.getPerfisUsuarioAplicativo().forEach( perfilUsuarioAplicativo -> {

				authorities.addAll( perfilUsuarioAplicativo.getPermissoes() );

			} );
			return authorities;
		}
		return null;
	}

	/**
	 * Tratamento para quando a conta estiver excluída (é o mesmo que como se estivesse expirada)
	 */
	@Override
	@Transient
	public boolean isAccountNonExpired()
	{
		return this.dataExclusao == null;
	}

	/**
	 * Tratamento para quando a conta estiver bloqueada, a data de hoje deve estar entra a data de desbloqueio e a data de bloqueio
	 */
	@Override
	@Transient
	public boolean isAccountNonLocked()
	{
		return !( this.dataBloqueio != null && this.dataBloqueio.before( Calendar.getInstance() ) && ( ( this.dataDesbloqueio != null && this.dataDesbloqueio.after( Calendar.getInstance() ) ) || this.dataDesbloqueio == null ) );
	}

	/**
	 * As credenciais estão expiradas quando o usuário foi assinalado para dever alterar a senha (alterarSenha ==true), ou a data de hoje for posterior a data de expiração da senha
	 */
	@Override
	@Transient
	public boolean isCredentialsNonExpired()
	{
		return ( this.getAlterarSenha() == null || !this.getAlterarSenha() ) && ( this.dataExpiracaoSenha == null || this.dataExpiracaoSenha.after( Calendar.getInstance() ) );
	}

	/**
	 * 
	 */
	@Override
	@Transient
	public boolean isEnabled()
	{
		return this.isAccountNonExpired() && this.isAccountNonLocked();
	}

	/**
	 * 
	 */
	public void desbloquear()
	{
		this.dataBloqueio = null;
		this.dataDesbloqueio = null;
	}

	/**
	 * 
	 * @return
	 */
	public String getNomeCompleto()
	{
		return this.pessoa != null && this.pessoa.getNomeCompleto() != null ? this.pessoa.getNomeCompleto() : this.nomeCompleto;
	}

	/**
	 * @param nomeCompleto the nomeCompleto to set
	 */
	public void setNomeCompleto( String nomeCompleto )
	{
		this.nomeCompleto = nomeCompleto;
		if ( this.pessoa != null && this.pessoa.getNomeCompleto() != null ) this.nomeCompleto = this.pessoa.getNomeCompleto();
	}

	/**
	 * 
	 * @param pessoa
	 */
	public void setPessoa( Pessoa pessoa )
	{
		this.pessoa = pessoa;
		if ( this.pessoa != null && this.pessoa.getNomeCompleto() != null ) this.nomeCompleto = this.pessoa.getNomeCompleto();
		if ( this.pessoa != null && this.pessoa.getNip() != null ) this.login = this.pessoa.getNip();
	}

	/**
	 * 
	 * @return
	 */
	public OrganizacaoMilitar getOrganizacaoMilitar()
	{
		// Se a pessoa for null retornar null se não retornar a OM da pessoa
		return this.pessoa != null && this.pessoa.getOrganizacaoMilitar() != null ? this.pessoa.getOrganizacaoMilitar() : null;
	}

	/**
	 * 
	 * @return
	 */
	public String getPostoOrganizacional()
	{
		// Se a pessoa for null retornar null se não retornar a OM da pessoa
		return this.pessoa != null && this.pessoa.getPostoGraduacao() != null ? this.pessoa.getPostoGraduacao() : null;
	}

	/**
	 * Retorna o telefone da pessoa, retorna null se não houver pessoa
	 * 
	 * @return
	 */
	public String getTelefone()
	{
		return this.pessoa != null ? this.pessoa.getTelefone() : null;
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
		return this.getLogin();
	}

	/**
	 * @return the login
	 */
	public String getLogin()
	{
		return this.pessoa != null && this.pessoa.getNip() != null ? this.pessoa.getNip() : this.login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin( String login )
	{
		this.login = login;
		if ( this.pessoa != null && this.pessoa.getNip() != null ) this.login = this.pessoa.getNip();
	}

	/**
	 * Valida a senha para que não contenha menos que 6 caracteres
	 */
	public void validatePassword()
	{
		Usuario.validatePassword( this.getPassword() );
	}

	/**
	 * Método estático que valida a senha para que não contenha menos que 6 caracteres (recebendo parâmetro)
	 */
	public static void validatePassword( String password )
	{
		Assert.isTrue( password.length() >= 6, "A senha deve conter mais que 6 caracteres" );
	}

	/**
	 * Método que desserializa o objeto retirando os loops infinitos para a conversão para JSON
	 * 
	 * @return
	 */
	public Usuario prepareSerializable()
	{
		this.getPerfisUsuarioAplicativo().forEach( perfilUsuarioAplicativo -> {
			perfilUsuarioAplicativo.setUsuario( null );
			perfilUsuarioAplicativo.getPerfilAcesso().setAplicativo( null );
			perfilUsuarioAplicativo.getPerfilAcesso().setPerfilUsuarioAplicativos( null );

			perfilUsuarioAplicativo.getPerfilAcesso().getPerfilAcessoPermissoes().forEach( perfilAcessoPermissao -> {
				perfilAcessoPermissao.setPerfilAcesso( null );

				perfilAcessoPermissao.getPermissao().clearPermissao( perfilAcessoPermissao.getPermissao() );
			} );
		} );
		return this;
	}

	/**
	 * Método estático que cria uma senha aleatória
	 * 
	 * @return
	 */
	public static String createRandomPassword()
	{
		String bigHash = UUID.randomUUID().toString();

		int length = bigHash.length();

		Random random = new Random();

		String randomPassword = new String();
		for ( int i = 0; i < 8; i++ )
		{
			int index = random.nextInt( length );
			String subString = bigHash.substring( index, index + 1 );
			if ( subString.equals( "-" ) )
			{
				i--;
				continue;
			}
			else if ( Character.isLetter( subString.charAt( 0 ) ) && new Random().nextBoolean() )
			{
				randomPassword += bigHash.substring( index, index + 1 ).toUpperCase();
			}
			else
			{
				randomPassword += bigHash.substring( index, index + 1 );
			}
		}
		return randomPassword;
	}

	/**
	 * @return the email
	 *         Se o usuário não tem email, retorna o email do usuáiro administrador da OM
	 */
	public String getEmail()
	{
		return this.pessoa != null && this.pessoa.getEmail() != null ? this.pessoa.getEmail() : this.email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail( final String email )
	{
		if ( this.pessoa == null )
		{
			this.email = email;
		}
	}
	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/

	/**
	 * @param senha the senha to set
	 */
	public void setSenha( String senha )
	{
		this.senha = senha;
	}

	/**
	 * @return the senha
	 */
	public String getSenha()
	{
		return senha;
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
		return alterarSenha != null && alterarSenha;
	}

	/**
	 * @param alterarSenha the alterarSenha to set
	 */
	public void setAlterarSenha( Boolean alterarSenha )
	{
		this.alterarSenha = alterarSenha;
	}

	/**
	 * @return the perfisUsuarioAplicativo
	 */
	public Set<PerfilUsuarioAplicativo> getPerfisUsuarioAplicativo()
	{
		return perfisUsuarioAplicativo;
	}

	/**
	 * @param perfisUsuarioAplicativo the perfisUsuarioAplicativo to set
	 */
	public void setPerfisUsuarioAplicativo( Set<PerfilUsuarioAplicativo> perfisUsuarioAplicativo )
	{
		this.perfisUsuarioAplicativo = perfisUsuarioAplicativo;
	}

	/**
	 * @return the pessoa
	 */
	public Pessoa getPessoa()
	{
		return pessoa;
	}

	/**
	 * @param dataExclusao the dataExclusao to set
	 */
	public void setDataExclusao( Calendar dataExclusao )
	{
		this.dataExclusao = dataExclusao;
	}

}