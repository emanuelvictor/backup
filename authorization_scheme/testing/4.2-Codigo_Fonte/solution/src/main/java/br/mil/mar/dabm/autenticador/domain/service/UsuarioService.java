package br.mil.mar.dabm.autenticador.domain.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.jcr.RepositoryException;

import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.io.FileTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.history.Revision;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.eits.common.infrastructure.jcr.MetaFile;
import br.com.eits.common.infrastructure.jcr.modeshape.MetaFileRepository;
import br.mil.mar.dabm.autenticador.application.security.ContextHolder;
import br.mil.mar.dabm.autenticador.domain.entity.aplicativo.Aplicativo;
import br.mil.mar.dabm.autenticador.domain.entity.aplicativo.PerfilAcesso;
import br.mil.mar.dabm.autenticador.domain.entity.usuario.PerfilUsuarioAplicativo;
import br.mil.mar.dabm.autenticador.domain.entity.usuario.Usuario;
import br.mil.mar.dabm.autenticador.domain.repository.IUsuarioMailRepository;
import br.mil.mar.dabm.autenticador.domain.repository.usuario.IConfiguracaoRepository;
import br.mil.mar.dabm.autenticador.domain.repository.usuario.IPerfilUsuarioAplicativoRepository;
import br.mil.mar.dabm.autenticador.domain.repository.usuario.IUsuarioRepository;

/**
 * 
 * @author rodrigo@eits.com.br
 */
@Service
@Transactional
@RemoteProxy(name = "usuarioService")
public class UsuarioService
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * Password encoder
	 */
	@Autowired
	private ShaPasswordEncoder passwordEncoder;

	/**
	 * Hash generator for encryption
	 */
	@Autowired
	private SaltSource saltSource;

	// Repositories
	/**
	 * 
	 */
	@Autowired
	private IUsuarioRepository usuarioRepository;
	/**
	 * repositório utilizado para salvar as fotos
	 */
	@Autowired
	private MetaFileRepository metaFileRepository;
	/**
	 * repositório utilizado para enviar emails
	 */
	@Autowired
	private IUsuarioMailRepository usuarioMailRepository;
	/**
	 * repositório para data default de expiração de senha
	 */
	@Autowired
	private IConfiguracaoRepository configuracaoRepository;
	/**
	 * 
	 */
	@Autowired
	private IPerfilUsuarioAplicativoRepository perfilUsuarioAplicativoRepository;

	/*-------------------------------------------------------------------
	 *				 		     SERVICES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param usuario
	 * @return
	 */
	public Usuario insertUsuario( Usuario usuario )
	{
		Assert.isNull( usuario.getId(), "O usuário não pode ser salvo já contendo uma identificação" );
		final String encodedPassword = this.passwordEncoder.encodePassword( usuario.getPassword(), saltSource.getSalt( usuario ) );
		usuario.setSenha( encodedPassword );

		Calendar dataExpiracaoSenha = Calendar.getInstance();
		dataExpiracaoSenha.set( Calendar.DAY_OF_YEAR, dataExpiracaoSenha.get( Calendar.DAY_OF_YEAR ) + this.configuracaoRepository.findOne( 1L ).getDiasExpiracaoSenha() );
		usuario.setDataExpiracaoSenha( dataExpiracaoSenha );

		usuario = this.usuarioRepository.save( usuario );
		this.perfilUsuarioAplicativoRepository.save( new PerfilUsuarioAplicativo( null, usuario, new PerfilAcesso( 401L ) ) );
		return usuario;
	}

	/**
	 * FIXME esse método será substituido pelo fluxo do Oauth2
	 * 
	 * @return
	 */
	public Usuario getLoggedUser()
	{
		return this.findUsuarioById( ContextHolder.getAuthenticatedUser().getId());
		
	}

	/**
	 * 
	 * @param usuario
	 * @return
	 */
	public Usuario updateUsuario( Usuario usuario )
	{
		return this.usuarioRepository.save( usuario );
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Usuario findUsuarioById( final Long id )
	{
		final Usuario usuario = this.usuarioRepository.findOne( id );
		Assert.notNull( usuario, "Não foi possível encontrar o registro com identificador: " + id );
		return usuario;
	}

	/**
	 * 
	 * @param usuarios
	 * @param dataBloqueio
	 * @param dataDesbloqueio
	 */
	public List<Usuario> bloquearUsuarios( final List<Usuario> usuarios, final Calendar dataBloqueio, final Calendar dataDesbloqueio )
	{
		List<Long> usuariosId = new ArrayList<Long>();
		for ( Usuario usuario : usuarios )
		{
			usuariosId.add( usuario.getId() );
		}
		return this.bloquearUsuariosById( usuariosId, dataBloqueio, dataDesbloqueio );
	}

	/**
	 * 
	 * @param usuariosId
	 * @param dataBloqueio
	 * @param dataDesbloqueio
	 */
	public List<Usuario> bloquearUsuariosById( final List<Long> usuariosId, final Calendar dataBloqueio, final Calendar dataDesbloqueio )
	{
		List<Usuario> usuarios = this.usuarioRepository.findAll( usuariosId );
		for ( Usuario usuario : usuarios )
		{
			usuario.setDataBloqueio( dataBloqueio );
			usuario.setDataDesbloqueio( dataDesbloqueio );
			usuario = this.updateUsuario( usuario );

		}
		return usuarios;
	}

	/**
	 * 
	 * @param usuarios
	 * @return
	 */
	public List<Usuario> desbloquearUsuarios( final List<Usuario> usuarios )
	{
		List<Long> usuariosId = new ArrayList<Long>();
		for ( Usuario usuario : usuarios )
		{
			usuariosId.add( usuario.getId() );
		}
		return this.desbloquearUsuariosById( usuariosId );
	}

	/**
	 * 
	 * @param usuariosId
	 */
	public List<Usuario> desbloquearUsuariosById( final List<Long> usuariosId )
	{
		List<Usuario> usuarios = this.usuarioRepository.findAll( usuariosId );
		for ( Usuario usuario : usuarios )
		{
			usuario.desbloquear();
			this.updateUsuario( usuario );
		}
		return usuarios;
	}

	/**
	 * 
	 * @param usuarioId
	 * @param senhaAtual
	 * @param novaSenha
	 */
	public Usuario changeSenhaUsuario( final Long usuarioId, final String senhaAtual, final String novaSenha )
	{
		Usuario usuario = this.usuarioRepository.findOne( usuarioId );

		final String senhaConfirmacao = this.passwordEncoder.encodePassword( senhaAtual, saltSource.getSalt( usuario ) );

		final String senhaAntiga = usuario.getPassword();

		Assert.notNull( senhaAtual, "A senha atual para confirmação de senha não pode ser nula ou vazia" );
		Assert.isTrue( senhaAntiga.equals( senhaConfirmacao ), "A senha atual esta incorreta" );
		return this.changeSenhaUsuario( usuarioId, novaSenha );
	}

	/**
	 * 
	 * @param usuarioId
	 * @param novaSenha
	 */
	public Usuario changeSenhaUsuario( final Long usuarioId, final String novaSenha )
	{
		Usuario usuario = this.usuarioRepository.findOne( usuarioId );
		final String encodedPassword = this.passwordEncoder.encodePassword( novaSenha, saltSource.getSalt( usuario ) );
		usuario.setSenha( encodedPassword );
		usuario.setDataAlteracaoSenha( Calendar.getInstance() );
		this.enviarEmail( this.usuarioRepository.save( usuario ).getLogin() );
		return usuario;
	}

	/**
	 * 
	 * @param usuarioId
	 * @param pageable
	 * @return
	 */
	public Page<PerfilUsuarioAplicativo> listPerfilUsuarioAplicativoByUsuarioId( final Long usuarioId, final PageRequest pageable )
	{
		return this.perfilUsuarioAplicativoRepository.findPerfilUsuarioAplicativosByUsuarioId( usuarioId, pageable );
	}

//	public Set<PerfilUsuarioAplicativo> listPerfilUsuarioAplicativoByUsuarioId( final Long usuarioId )
//	{
//		return this.perfilUsuarioAplicativoRepository.findPerfilUsuarioAplicativosByUsuarioId( usuarioId );
//	}

	public Set<Aplicativo> listAplicativoByUsuarioId( final Long usuarioId)
	{
		return this.perfilUsuarioAplicativoRepository.listAplicativoByUsuarioId( usuarioId);
	}

	/**
	 * 
	 * @return
	 */
	public Set<Aplicativo> listAplicativoByUserLogged()
	{
		return this.perfilUsuarioAplicativoRepository.listAplicativoByUsuarioId( this.getLoggedUser().getId() );
	}

	/**
	 * 
	 * @param idUsuario
	 */
	private void enviarEmail( final String login )
	{
		this.usuarioMailRepository.sendRecuperarSenha( this.usuarioRepository.findByLoginIgnoreCase( login ) );
	}

	/**
	 * 
	 * @param login
	 * @return
	 */
	public boolean checkLogin( final String login )
	{
		return this.usuarioRepository.findByLoginIgnoreCase( login ) != null;
	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	public boolean checkEmail( final String email )
	{
		return this.usuarioRepository.findByEmail( email ) != null;
	}

	/**
	 * @param id
	 * @return
	 */
	private Usuario excluirUsuario( final Long id )
	{
		Usuario usuario = this.usuarioRepository.findOne( id );
		usuario.setDataExclusao( Calendar.getInstance() );
		return this.updateUsuario( usuario );
	}

	/**
	 * 
	 * @param usuariosId
	 */
	public List<Usuario> excluirUsuarios( final List<Long> usuariosId )
	{
		List<Usuario> usuarios = this.usuarioRepository.findAll( usuariosId );
		for ( int i = 0; i < usuarios.size(); i++ )
		{
			usuarios.get( i ).setDataExclusao( Calendar.getInstance() );
			usuarios.set( i, this.excluirUsuario( usuarios.get( i ).getId() ) );
		}
		return usuarios;
	}



	/**
	 * @param usuariosId
	 */
	public List<Usuario> restaurarUsuarios( final List<Long> usuariosId )
	{
		List<Usuario> usuarios = this.usuarioRepository.findAll( usuariosId );
		for ( int i = 0; i < usuarios.size(); i++ )
		{
			usuarios.get( i ).setDataExclusao( null );
			this.updateUsuario( usuarios.get( i ) );
		}
		return usuarios;
	}

	/**
	 * 
	 * @param idUsuario
	 */
	private void removePerfisAcesso( final List<Long> idUsuario )
	{
		// Removendo perfis de acesso
		for ( final Long id : idUsuario )
		{
			List<PerfilUsuarioAplicativo> listPerfilUsuarioAplicativo = this.listPerfilUsuarioAplicativoByUsuarioId( id, null ).getContent();
			this.perfilUsuarioAplicativoRepository.deleteInBatch( listPerfilUsuarioAplicativo );
		}
	}

	/**
	 * 
	 * @param idUsuarioOrigem
	 * @param listIdUsuarioDestino
	 */
	public void replicarPerfisAcesso( final Long idUsuarioOrigem, final List<Long> listIdUsuarioDestino )
	{
		Assert.notNull( idUsuarioOrigem, "O usuário de origem não pode ser nulo" );
		Assert.notNull( listIdUsuarioDestino, "Os usuários de destino não podem ser nulos" );

		// Removendo perfis de acesso anteriores. Obs: Isso está nos requisitos, o usuário de destino perde todos perfis e recebe somente os novos
		this.removePerfisAcesso( listIdUsuarioDestino );

		// Puxando perfis do usuário de origem
		final List<PerfilUsuarioAplicativo> listPerfilUsuarioAplicativo = this.listPerfilUsuarioAplicativoByUsuarioId( idUsuarioOrigem, null ).getContent();

		// Adicionando novos perfis de acesso
		// FIXME aperfeiçoar isso
		for ( final PerfilUsuarioAplicativo perfilUsuarioAplicativo : listPerfilUsuarioAplicativo )
		{

			for ( final Long idUsuarioDestino : listIdUsuarioDestino )
			{
				this.insertPerfilUsuarioAplicativo( perfilUsuarioAplicativo.getPerfilAcesso().getId(), idUsuarioDestino );
			}
		}
	}

	/**
	 * 
	 * @param idUsuarioOrigem
	 * @param listIdUsuarioDestino
	 */
	public void replicarPerfisAcesso( final Usuario usuarioOrigem, final List<Usuario> usuariosDestino )
	{
		List<Long> listIdUsuarioDestino = new ArrayList<>();
		for ( Usuario usuario : usuariosDestino )
		{
			listIdUsuarioDestino.add( usuario.getId() );
		}
		this.replicarPerfisAcesso( usuarioOrigem.getId(), listIdUsuarioDestino );
	}

	/**
	 * 
	 * @param perfilAcessoId
	 * @param usuarioId
	 * @return
	 */
	public PerfilUsuarioAplicativo insertPerfilUsuarioAplicativo( final Long usuarioId, final Long perfilAcessoId )
	{
		return this.perfilUsuarioAplicativoRepository.save( new PerfilUsuarioAplicativo( null, new Usuario( usuarioId ), new PerfilAcesso( perfilAcessoId ) ) );
	}

	/**
	 * 
	 * @param usuarioId
	 * @param perfilAcessoId
	 */
	public void removePerfilUsuarioAplicativo( final Long usuarioId, final Long perfilAcessoId )
	{
		System.out.println("Excluindo");
		this.perfilUsuarioAplicativoRepository.deleteByUsuarioIdAndPerfilAcessoId( usuarioId, perfilAcessoId );
	}

	/**
	 * Serviço para remover todos os aplicativos de usuário (remove todos os perfilUsuarioAplicativo)
	 * 
	 * @param usuarioId
	 * @param perfilAcessoId
	 */
	public void removePerfilUsuarioAplicativo( List<PerfilUsuarioAplicativo> perfisUsuarioAplicativo )
	{
		this.perfilUsuarioAplicativoRepository.delete( perfisUsuarioAplicativo );
	}

	/**
	 * 
	 * @param fileTransfer
	 * @param usuarioId
	 * @throws IOException
	 * @throws RepositoryException
	 */
	public void uploadFotoUsuario( final FileTransfer fileTransfer, final Long usuarioId )
	{
		Assert.notNull( fileTransfer, "O arquino nao pode ser nulo" );
		Assert.notNull( usuarioId, "O usuário não pode ser nulo" );
		Assert.isTrue( Arrays.asList( Usuario.ICONE_MIME_TYPES ).contains( fileTransfer.getMimeType() ), "Tipo não permitido" );

		try
		{
			MetaFile metaFile = new MetaFile();

			metaFile.setContentType( fileTransfer.getMimeType() );
			metaFile.setFolder( String.format( Usuario.FOTO_PATH, usuarioId ) );
			metaFile.setName( fileTransfer.getFilename() );

			metaFile.setInputStream( fileTransfer.getInputStream() );

			metaFile.setId( usuarioId.toString() );
//		metaFile.setCreatedBy( ContextHolder.getAuthenticatedUser().getId().toString() );

			metaFile = this.metaFileRepository.insert( metaFile );
		}
		catch ( Exception e )
		{
			throw new RuntimeException( "Não foi possível realizar o upload do arquivo." );
		}
	}

	/**
	 * 
	 * @param fileTransfer
	 * @param usuarioId
	 * @throws IOException
	 * @throws RepositoryException
	 */
	public FileTransfer findFotoUsuarioById( final Long usuarioId )
	{
		Assert.notNull( usuarioId, "O usuário não pode ser nulo" );

		try
		{
			final MetaFile metaFile = this.metaFileRepository.findById( usuarioId.toString(), true );

			return new FileTransfer( metaFile.getName(), metaFile.getContentType(), metaFile.getInputStream() );

		}
		catch ( Exception e )
		{
			throw new RuntimeException( "Não foi possível realizar o upload do arquivo." );
		}
	}

	/**
	 * 
	 * @param usuarioId
	 * @throws RepositoryException
	 */
	public void removeFotoUsuario( final Long usuarioId )
	{
		try
		{
			this.metaFileRepository.remove( usuarioId.toString() );
		}
		catch ( RepositoryException e )
		{
			throw new RuntimeException( "Não foi possível realizar a remoção do arquivo." );
		}
	}

	/**
	 * @Transactional(readOnly = true)
	 * @param filters
	 * @param pageable
	 * @return
	 */
	public Page<Usuario> listUsuariosByFilters( String filters, PageRequest pageable )
	{
		return this.usuarioRepository.listByFilters( filters, Calendar.getInstance(), pageable );
	}

	/**
	 * 
	 * @param filters
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<Usuario> listUsuariosByFiltersAndBloqueados( final String filters, final PageRequest pageable )
	{
		return this.usuarioRepository.listByFiltersAndBloqueados( filters, Calendar.getInstance(), pageable );
	}

	/**
	 * 
	 * @param filters
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<Usuario> listUsuariosByFiltersAndExcluidos( final String filters, final PageRequest pageable )
	{
		return this.usuarioRepository.listByFiltersAndExcluidos( filters, pageable );
	}

	/**
	 * 
	 * @param usuarioId
	 * @return
	 */
	public Page<Revision<Integer, PerfilUsuarioAplicativo>> listPerfilUsuarioAplicativoHistoricoByUsuarioId( final Long usuarioId )
	{
		return null;// perfilUsuarioAplicativoRepository.findRevisions( (long) 1, new PageRequest(10, 5) );
	}

}