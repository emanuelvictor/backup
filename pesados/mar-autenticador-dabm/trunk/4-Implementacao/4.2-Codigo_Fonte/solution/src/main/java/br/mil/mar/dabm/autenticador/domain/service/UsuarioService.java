package br.mil.mar.dabm.autenticador.domain.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.jcr.RepositoryException;

import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.io.FileTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.eits.common.infrastructure.jcr.MetaFile;
import br.com.eits.common.infrastructure.jcr.modeshape.MetaFileRepository;
import br.mil.mar.dabm.autenticador.domain.repository.usuario.IPerfilUsuarioAplicativoRepository;
import br.mil.mar.dabm.common.application.security.ContextHolder;
import br.mil.mar.dabm.common.domain.entity.aplicativo.Aplicativo;
import br.mil.mar.dabm.common.domain.entity.aplicativo.PerfilAcesso;
import br.mil.mar.dabm.common.domain.entity.usuario.PerfilUsuarioAplicativo;
import br.mil.mar.dabm.common.domain.entity.usuario.Usuario;

/**
 * 
 * @author rodrigo@eits.com.br
 */
@Service
@Transactional
@RemoteProxy(name = "usuarioService")
////@PreAuthorize("hasRole('Autenticador.usuarioService')")
public class UsuarioService extends SynchronizeService
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	// Repositories

	/**
	 * repositório utilizado para salvar as fotos
	 */
	@Autowired
	private MetaFileRepository metaFileRepository;

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
//	//@PreAuthorize("hasRole('Autenticador.usuarioService.findUsuarioById.insertUsuario')")
	public Usuario insertUsuario( Usuario usuario )
	{

		Assert.isNull( usuario.getId(), "O usuário não pode ser salvo já contendo uma identificação" );

		String senhaAleatoria = Usuario.createRandomPassword();
		usuario.setSenha( senhaAleatoria );

		final String encodedPassword = this.passwordEncoder.encodePassword( usuario.getPassword(), saltSource.getSalt( usuario ) );
		usuario.setSenha( encodedPassword );

		Calendar dataExpiracaoSenha = Calendar.getInstance();
		dataExpiracaoSenha.set( Calendar.DAY_OF_YEAR, dataExpiracaoSenha.get( Calendar.DAY_OF_YEAR ) + this.configuracaoRepository.findOne( 1L ).getDiasExpiracaoSenha() );
		usuario.setDataExpiracaoSenha( dataExpiracaoSenha );

		usuario.setAlterarSenha( true );

		usuario = this.usuarioRepository.save( usuario );

		this.perfilUsuarioAplicativoRepository.save( new PerfilUsuarioAplicativo( null, usuario, new PerfilAcesso( 401L ) ) );

		this.usuarioMailRepository.newUsuario( this.synchronizePessoa( this.usuarioRepository.findByLoginIgnoreCase( usuario.getLogin() ) ), senhaAleatoria );

		return this.synchronizePessoa( usuario );
	}

	/**
	 * 
	 * @param usuario
	 * @return
	 */
//	////@PreAuthorize("hasRole('Autenticador.usuarioService.findUsuarioById.updateUsuario')")
	public Usuario updateUsuario( Usuario usuario )
	{
		return this.synchronizePessoa( this.usuarioRepository.save( usuario ) );
	}

	/**
	 * 
	 * @return
	 */
//	//@PreAuthorize("hasRole('Autenticador.usuarioService.getUserLogged')")
	@Transactional(readOnly = true)
	public Usuario getUserLogged()
	{
		return this.synchronizePessoa( this.usuarioRepository.findOne( ContextHolder.getAuthenticatedUser().getId() ) );
	}

	/**
	 * 
	 * @param usuarioId
	 * @return
	 */
	//@PreAuthorize("hasRole('Autenticador.usuarioService.findUsuarioById')")
	@Transactional(readOnly = true)
	public Usuario findUsuarioById( final Long usuarioId )
	{
		Usuario usuario = this.usuarioRepository.findOne( usuarioId );
		usuario.prepareSerializable();
		Assert.notNull( usuario, "Não foi possível encontrar o registro com identificador: " + usuarioId );
		return this.synchronizePessoa( usuario );
	}

	/**
	 * 
	 * @param usuariosId
	 * @param dataBloqueio
	 * @param dataDesbloqueio
	 */
	//@PreAuthorize("hasRole('Autenticador.usuarioService.findUsuarioById.bloquearUsuarios')")
	public List<Usuario> bloquearUsuarios( final List<Long> usuariosId, final Calendar dataBloqueio, final Calendar dataDesbloqueio )
	{
		List<Usuario> usuarios = this.usuarioRepository.findAll( usuariosId );
		for ( Usuario usuario : usuarios )
		{
			usuario.setDataBloqueio( dataBloqueio );
			usuario.setDataDesbloqueio( dataDesbloqueio );
			usuario = this.updateUsuario( usuario );
		}
		return this.synchronizePessoa( usuarios );
	}

	/**
	 * 
	 * @param usuariosId
	 */
	//@PreAuthorize("hasRole('Autenticador.usuarioService.findUsuarioById.desbloquearUsuarios')")
	public List<Usuario> desbloquearUsuarios( final List<Long> usuariosId )
	{
		List<Usuario> usuarios = this.usuarioRepository.findAll( usuariosId );
		for ( Usuario usuario : usuarios )
		{
			usuario.desbloquear();
			this.updateUsuario( usuario );
		}
		return this.synchronizePessoa( usuarios );
	}

	/**
	 * Serviço utilizado pelos usuários simples para alterar suas próprias senhas
	 * 
	 * @param usuarioId
	 * @param senhaAtual
	 * @param novaSenha
	 */
	//@PreAuthorize("hasRole('Autenticador.usuarioService.getUserLogged.changeSenhaUsuario')")
	public Usuario changeSenhaUsuario( final Long usuarioId, final String senhaAtual, final String novaSenha )
	{
		Assert.isTrue( usuarioId == this.getUserLogged().getId(), "Você não pode alterar a senha de outro usuário" );
		Usuario usuario = this.usuarioRepository.findOne( usuarioId );

		final String senhaConfirmacao = this.passwordEncoder.encodePassword( senhaAtual, saltSource.getSalt( usuario ) );

		Assert.notNull( senhaAtual, "A senha atual para confirmação de senha não pode ser nula ou vazia" );
		Assert.isTrue( usuario.getPassword().equals( senhaConfirmacao ), "A senha atual esta incorreta" );

		this.usuarioMailRepository.sendSenhaAlterada( this.synchronizePessoa( this.usuarioRepository.findByLoginIgnoreCase( usuario.getLogin() ) ) );

		return this.synchronizePessoa( this.changePassword( usuario, novaSenha ) );
	}

	/**
	 * Serviço utilizado pelos usuários administradores para resetar o password de outros usuários
	 * 
	 * @param usuarioId
	 * @param novaSenha
	 */
	//@PreAuthorize("hasRole('Autenticador.usuarioService.findUsuarioById.resetSenhaUsuario')")
	public Usuario resetSenhaUsuario( final Long usuarioId, final String novaSenha )
	{

		Usuario usuario = this.usuarioRepository.findOne( usuarioId );
		// Atende a regra de negócio, sempre que o usuário tiver sua senha alterada ele deve alterar a mesma no primeiro login
		usuario.setAlterarSenha( true );

		this.usuarioMailRepository.sendRedefinirSenha( this.synchronizePessoa( this.usuarioRepository.findByLoginIgnoreCase( usuario.getLogin() ) ), novaSenha );

		return this.synchronizePessoa( this.changePassword( usuario, novaSenha ) );
	}

	/**
	 * Método auxiliar para alteração e envio de email, utilizado resetSenhaUsuario(pelo adm)
	 * 
	 * @param usuarioId
	 * @param novaSenha
	 */
	private Usuario changePassword( final Usuario usuario, final String novaSenha )
	{
		Usuario.validatePassword( novaSenha );
		final String encodedPassword = this.passwordEncoder.encodePassword( novaSenha, saltSource.getSalt( usuario ) );
		usuario.setSenha( encodedPassword );
		Calendar calendar = Calendar.getInstance();
		calendar.set( Calendar.DAY_OF_YEAR, Calendar.getInstance().get( Calendar.DAY_OF_YEAR ) + this.configuracaoRepository.getOne( 1L ).getDiasExpiracaoSenha() );
		usuario.setDataExpiracaoSenha( calendar );
		usuario.setDataAlteracaoSenha( Calendar.getInstance() );
		// this.usuarioMailRepository.sendRedefinirSenha( this.usuarioRepository.findByLoginIgnoreCase( usuario.getLogin()), novaSenha);
		return this.usuarioRepository.save( usuario );
	}

	/**
	 * 
	 * @param usuarioId
	 * @param pageable
	 * @return
	 */
	//@PreAuthorize("hasRole('Autenticador.usuarioService.findUsuarioById.listPerfilUsuarioAplicativoByUsuarioId')")
	public Page<PerfilUsuarioAplicativo> listPerfilUsuarioAplicativoByUsuarioId( final Long usuarioId, final PageRequest pageable )
	{
		return this.perfilUsuarioAplicativoRepository.findPerfilUsuarioAplicativosByUsuarioId( usuarioId, pageable );
	}

	/**
	 * 
	 * @param usuarioId
	 * @param pageable
	 * @return
	 */
	//@PreAuthorize("hasRole('Autenticador.usuarioService.findUsuarioById.listAplicativoByUsuarioId')")
	public Set<Aplicativo> listAplicativoByUsuarioId( final Long usuarioId, final PageRequest pageable )
	{
		return this.getAplicativosAndPerfisAcessoByUsuarioId( new HashSet<PerfilUsuarioAplicativo>( perfilUsuarioAplicativoRepository.findPerfilUsuarioAplicativosByUsuarioId( usuarioId, pageable ).getContent() ) );
	}

	/**
	 * 
	 * @return
	 */
	//@PreAuthorize("hasRole('Autenticador.usuarioService.getUserLogged.listAplicativosByUserLogged')")
	public Set<Aplicativo> listAplicativosByUserLogged()
	{
		return this.getAplicativosAndPerfisAcessoByUsuarioId( new HashSet<PerfilUsuarioAplicativo>( perfilUsuarioAplicativoRepository.findPerfilUsuarioAplicativosByUsuarioId( ContextHolder.getAuthenticatedUser().getId(), null ).getContent() ) );
	}

	/**
	 * Método auxiliar que retorna os aplicativos com os perfis de acesso filtrados pelo usuário
	 * 
	 * @return
	 */
	private Set<Aplicativo> getAplicativosAndPerfisAcessoByUsuarioId( final Set<PerfilUsuarioAplicativo> perfisUsuarioAplicativo )
	{
		Set<Aplicativo> aplicativos = new HashSet<Aplicativo>();
		List<PerfilAcesso> perfisAcesso = new ArrayList<PerfilAcesso>();

		for ( final PerfilUsuarioAplicativo perfilUsuarioAplicativo : perfisUsuarioAplicativo )
		{
			perfilUsuarioAplicativo.getPerfilAcesso().getAplicativo().setPerfisAcesso( new HashSet<PerfilAcesso>() );
			aplicativos.add( perfilUsuarioAplicativo.getPerfilAcesso().getAplicativo() );
			perfisAcesso.add( perfilUsuarioAplicativo.getPerfilAcesso() );
		}

		for ( final Aplicativo aplicativo : aplicativos )
		{
			for ( int i = 0; i < perfisAcesso.size(); i++ )
			{
				if ( perfisAcesso.get( i ).getAplicativo().getId() == aplicativo.getId() )
				{
					aplicativo.getPerfisAcesso().add( perfisAcesso.get( i ) );
				}
			}
		}
		return aplicativos;
	}

	/**
	 * @param id
	 * @return
	 */
	private Usuario excluirUsuario( final Long id )
	{
		Usuario usuario = this.usuarioRepository.findOne( id );
		usuario.setDataExclusao( Calendar.getInstance() );
		return this.synchronizePessoa( this.updateUsuario( usuario ) );
	}

	/**
	 * 
	 * @param usuariosId
	 */
	//@PreAuthorize("hasRole('Autenticador.usuarioService.findUsuarioById.excluirUsuarios')")
	public List<Usuario> excluirUsuarios( final List<Long> usuariosId )
	{
		List<Usuario> usuarios = this.usuarioRepository.findAll( usuariosId );
		for ( int i = 0; i < usuarios.size(); i++ )
		{
			usuarios.get( i ).setDataExclusao( Calendar.getInstance() );
			usuarios.set( i, this.excluirUsuario( usuarios.get( i ).getId() ) );
		}
		return this.synchronizePessoa( usuarios );
	}

	/**
	 * 
	 * @param usuariosId
	 * @return
	 */
	//@PreAuthorize("hasRole('Autenticador.usuarioService.findUsuarioById.restaurarUsuarios')")
	public List<Usuario> restaurarUsuarios( final List<Long> usuariosId )
	{
		List<Usuario> usuarios = this.usuarioRepository.findAll( usuariosId );
		for ( int i = 0; i < usuarios.size(); i++ )
		{
			usuarios.get( i ).setDataExclusao( null );
			this.updateUsuario( usuarios.get( i ) );
		}
		return this.synchronizePessoa( usuarios );
	}

	/**
	 * 
	 * @param idUsuario
	 */
	private void removePerfisAcesso( final List<Long> usuarioIds )
	{
		for ( final Long usuarioId : usuarioIds )
		{
			this.perfilUsuarioAplicativoRepository.deleteInBatch( this.listPerfilUsuarioAplicativoByUsuarioId( usuarioId, null ).getContent() );
		}
	}

	/**
	 * 
	 * @param idUsuarioOrigem
	 * @param listIdUsuarioDestino
	 */
	//@PreAuthorize("hasRole('Autenticador.usuarioService.findUsuarioById.replicarPerfisAcesso')")
	public void replicarPerfisAcesso( final Long idUsuarioOrigem, final List<Long> listIdUsuarioDestino )
	{
		Assert.notNull( idUsuarioOrigem, "O usuário de origem não pode ser nulo" );
		Assert.notNull( listIdUsuarioDestino, "Os usuários de destino não podem ser nulos" );

		// Removendo perfis de acesso anteriores. Obs: Isso está nos requisitos, o usuário de destino perde todos perfis e recebe somente os novos
		this.removePerfisAcesso( listIdUsuarioDestino );

		// Puxando perfis do usuário de origem
		final List<PerfilUsuarioAplicativo> listPerfilUsuarioAplicativo = this.listPerfilUsuarioAplicativoByUsuarioId( idUsuarioOrigem, null ).getContent();

		// Adicionando novos perfis de acesso
		for ( final PerfilUsuarioAplicativo perfilUsuarioAplicativo : listPerfilUsuarioAplicativo )
		{
			for ( final Long idUsuarioDestino : listIdUsuarioDestino )
			{
				this.perfilUsuarioAplicativoRepository.save( new PerfilUsuarioAplicativo( null, new Usuario( idUsuarioDestino ), new PerfilAcesso( perfilUsuarioAplicativo.getPerfilAcesso().getId() ) ) );
			}
		}
	}

	/**
	 * 
	 * @param perfilAcessoId
	 * @param usuarioId
	 * @return
	 */
	//@PreAuthorize("hasRole('Autenticador.usuarioService.findUsuarioById.insertPerfilUsuarioAplicativo')")
	public PerfilUsuarioAplicativo insertPerfilUsuarioAplicativo( final Long usuarioId, final Long perfilAcessoId )
	{
		return this.perfilUsuarioAplicativoRepository.save( new PerfilUsuarioAplicativo( null, new Usuario( usuarioId ), new PerfilAcesso( perfilAcessoId ) ) );
	}

	/**
	 * 
	 * @param usuarioId
	 * @param perfilAcessoId
	 */
	//@PreAuthorize("hasRole('Autenticador.usuarioService.findUsuarioById.removePerfilUsuarioAplicativo')")
	public void removePerfilUsuarioAplicativo( final Long usuarioId, final Long perfilAcessoId )
	{
		this.perfilUsuarioAplicativoRepository.deleteByUsuarioIdAndPerfilAcessoId( usuarioId, perfilAcessoId );
	}

	/**
	 * Serviço para remover todos os aplicativos de usuário (remove todos os perfilUsuarioAplicativo)
	 * 
	 * @param usuarioId
	 * @param perfilAcessoId
	 */
	//@PreAuthorize("hasRole('Autenticador.usuarioService.findUsuarioById.removePerfilUsuarioAplicativo')")
	public void removePerfilUsuarioAplicativoByAplicativoId( final Long usuarioId, final Aplicativo aplicativo )
	{
		for ( PerfilAcesso perfilAcesso : aplicativo.getPerfisAcesso() )
		{
			this.perfilUsuarioAplicativoRepository.deleteByUsuarioIdAndPerfilAcessoId( usuarioId, perfilAcesso.getId() );
		}
	}

	/**
	 * 
	 * @param fileTransfer
	 * @param usuarioId
	 * @throws IOException
	 * @throws RepositoryException
	 */
	//@PreAuthorize("hasRole('Autenticador.usuarioService.getUserLogged.uploadFotoUsuario')")
	public void uploadFotoUsuario( final FileTransfer fileTransfer, final Long usuarioId )
	{

		Assert.notNull( fileTransfer, "O arquino nao pode ser nulo" );
		Assert.notNull( usuarioId, "O usuário não pode ser nulo" );
		Assert.isTrue( Arrays.asList( Usuario.FOTO_MIME_TYPES ).contains( fileTransfer.getMimeType() ), "Tipo não permitido" );

		try
		{
			MetaFile metaFile = new MetaFile();

			metaFile.setContentType( fileTransfer.getMimeType() );
			metaFile.setFolder( String.format( Usuario.FOTO_PATH, usuarioId ) );
			metaFile.setName( fileTransfer.getFilename() );

			metaFile.setInputStream( fileTransfer.getInputStream() );

			metaFile.setId( String.format( Usuario.FOTO_PATH, usuarioId ) );

			metaFile = this.metaFileRepository.insert( metaFile );
		}
		catch ( Exception e )
		{
			throw new RuntimeException( "Não foi possível realizar o upload da foto do usuário" );
		}
	}

	/**
	 * 
	 * @param fileTransfer
	 * @param usuarioId
	 * @throws IOException
	 * @throws RepositoryException
	 */
	//@PreAuthorize("hasRole('Autenticador.usuarioService.getUserLogged.findFotoUsuarioById')")
	public FileTransfer findFotoUsuarioById( final Long usuarioId )
	{
		Assert.notNull( usuarioId, "O usuário não pode ser nulo" );

		try
		{
			final MetaFile metaFile = this.metaFileRepository.findById( String.format( Usuario.FOTO_PATH, usuarioId ) , true );

			return new FileTransfer( metaFile.getName(), metaFile.getContentType(), metaFile.getInputStream() );
		}
		catch ( Exception e )
		{
			return null;
		}
	}

	/**
	 * 
	 * @param usuarioId
	 * @throws RepositoryException
	 */
	//@PreAuthorize("hasRole('Autenticador.usuarioService.getUserLogged.removeFotoUsuario')")
	public void removeFotoUsuario( final Long usuarioId )
	{
		try
		{
			this.metaFileRepository.remove( String.format( Usuario.FOTO_PATH, usuarioId )  );
		}
		catch ( RepositoryException e )
		{
			throw new RuntimeException( "Não foi possível realizar a remoção do arquivo." );
		}
	}

	/**
	 * 
	 * @param filters
	 * @param pageRequest
	 * @return
	 */
	//@PreAuthorize("hasRole('Autenticador.usuarioService.findUsuarioById.listUsuariosByFilters')")
	@Transactional(readOnly = true)
	public Page<Usuario> listUsuariosByFilters( final String filters, final String perfil, final String aplicativo, final PageRequest pageRequest )
	{
		return synchronizePessoa( this.usuarioRepository.listByFilters( filters, perfil, aplicativo, Calendar.getInstance(), pageRequest ), filters );
	}

	/**
	 * 
	 * @param filters
	 * @param pageRequest
	 * @return
	 */
	//@PreAuthorize("hasRole('Autenticador.usuarioService.findUsuarioById.listUsuariosByFiltersAndExcluidos')")
	@Transactional(readOnly = true)
	public Page<Usuario> listUsuariosByFiltersAndExcluidos( final String filters, final String perfil, final String aplicativo, final PageRequest pageRequest )
	{
		return synchronizePessoa( this.usuarioRepository.listByFiltersAndExcluidos( filters, perfil, aplicativo, pageRequest ) , filters);
	}

	/**
	 * 
	 * @param filters
	 * @param pageRequest
	 * @return
	 */
	//@PreAuthorize("hasRole('Autenticador.usuarioService.findUsuarioById.listUsuariosByFiltersAndExcluidos')")
	@Transactional(readOnly = true)
	public Page<Usuario> listUsuariosByFiltersAndBloqueados( final String filters, final String perfil, final String aplicativo, final PageRequest pageRequest )
	{
		return synchronizePessoa( this.usuarioRepository.listByFiltersAndBloqueados( filters, perfil, aplicativo, Calendar.getInstance(), pageRequest ), filters );
	}

	/**
	 * 
	 * @param usuarioId
	 * @return
	 */
	//@PreAuthorize("hasRole('Autenticador.usuarioService.findUsuarioById.listPerfilUsuarioAplicativoHistoricoByUsuarioId')")
	@Transactional(readOnly = true)
	public Set<Aplicativo> listPerfilUsuarioAplicativoHistoricoByUsuarioId( final Long usuarioId )
	{

		Set<Aplicativo> aplicativos = new HashSet<Aplicativo>();
		List<PerfilAcesso> perfisAcesso = new ArrayList<PerfilAcesso>();

		List<PerfilUsuarioAplicativo> perfilUsuarioAplicativo = this.perfilUsuarioAplicativoRepository.findRevisionsByUsuarioId( usuarioId );

		for ( final PerfilUsuarioAplicativo perfilUsuarioAplicativo1 : perfilUsuarioAplicativo )
		{
			perfilUsuarioAplicativo1.getPerfilAcesso().getAplicativo().setPerfisAcesso( new HashSet<PerfilAcesso>() );
			aplicativos.add( perfilUsuarioAplicativo1.getPerfilAcesso().getAplicativo() );
			perfisAcesso.add( perfilUsuarioAplicativo1.getPerfilAcesso() );
		}

		for ( final Aplicativo aplicativo : aplicativos )
		{
			for ( int i = 0; i < perfisAcesso.size(); i++ )
			{
				if ( perfisAcesso.get( i ).getAplicativo().getId() == aplicativo.getId() )
				{
					aplicativo.getPerfisAcesso().add( perfisAcesso.get( i ) );
				}
			}
		}

		return aplicativos;

	}

	/**
	 * @param login
	 * @return
	 */
	public Usuario findUsuarioByLogin( String login )
	{
		return this.synchronizePessoa( this.usuarioRepository.findByLoginIgnoreCase( login ) );
	}

}