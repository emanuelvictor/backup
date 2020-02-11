package br.mil.mar.dabm.autenticador.domain.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;

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
import br.mil.mar.dabm.autenticador.domain.repository.aplicativo.IAplicativoRepository;
import br.mil.mar.dabm.autenticador.domain.repository.aplicativo.IPerfilAcessoPermissaoRepository;
import br.mil.mar.dabm.autenticador.domain.repository.aplicativo.IPerfilAcessoRepository;
import br.mil.mar.dabm.autenticador.domain.repository.aplicativo.IPermissaoRepository;
import br.mil.mar.dabm.common.domain.entity.aplicativo.Aplicativo;
import br.mil.mar.dabm.common.domain.entity.aplicativo.PerfilAcesso;
import br.mil.mar.dabm.common.domain.entity.aplicativo.PerfilAcessoPermissao;
import br.mil.mar.dabm.common.domain.entity.aplicativo.Permissao;

/**
 * @author Boz
 *
 */
@Service
@Transactional
@RemoteProxy(name = "aplicativoService")
// @PreAuthorize("hasRole('Autenticador.aplicativoService')")
public class AplicativoService
{

	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	// Repositories
	/**
	 * 
	 */
	@Autowired
	private IAplicativoRepository aplicativoRepository;

	/**
	 * 	
	 */
	@Autowired
	private IPerfilAcessoPermissaoRepository perfilAcessoPermissaoRepository;
	/**
	 * 
	 */
	@Autowired
	private IPermissaoRepository permissaoRepository;

	/**
	 * 
	 */
	@Autowired
	private IPerfilAcessoRepository perfilAcessoRepository;

	/**
	 * repositório utilizado para salvar os ícones
	 */
	@Autowired
	private MetaFileRepository metaFileRepository;

	/*-------------------------------------------------------------------
	 *				 		     SERVICES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param aplicativoId
	 * @return
	 */
	public Aplicativo findAplicativoById( Long aplicativoId )
	{
		Aplicativo aplicativo = this.aplicativoRepository.findOne( aplicativoId );
		Assert.notNull( aplicativo, "Não foi possível encontrar o registro com identificador: " + aplicativoId );
		return aplicativo;
	}

	/**
	 * 
	 * @param identificador
	 * @return
	 */
	public Aplicativo findAplicativoByIdentificador( String identificador )
	{
		Aplicativo aplicativo = this.aplicativoRepository.findByIdentificador( identificador );
		Assert.notNull( aplicativo, "Não foi possível encontrar o registro com identificador: " + identificador );
		return aplicativo;
	}

	/**
	 * @param perfilAcessoId
	 * @return
	 */
	public PerfilAcesso findPerfilAcessoById( Long perfilAcessoId )
	{
		final PerfilAcesso perfilAcesso = this.perfilAcessoRepository.findOne( perfilAcessoId );
		Assert.notNull( perfilAcesso, "Não foi possível encontrar o registro com identificador: " + perfilAcessoId );
		return perfilAcesso;
	}

	/**
	 * @param perfilAcessoPermisssao
	 * @return
	 */
	public PerfilAcessoPermissao findPerfilAcessoPermissaoById( Long perfilAcessoPermisssaoId )
	{
		final PerfilAcessoPermissao perfilAcessoPermisssao = this.perfilAcessoPermissaoRepository.findOne( perfilAcessoPermisssaoId );
		Assert.notNull( perfilAcessoPermisssao, "Não foi possível encontrar o registro com identificador: " + perfilAcessoPermisssaoId );
		return perfilAcessoPermisssao;
	}

	/**
	 * 
	 * @param aplicativo
	 * @return
	 */
	public Aplicativo insertAplicativo( Aplicativo aplicativo )
	{
		Assert.notNull( aplicativo );
		aplicativo.setAtivo( true );
		aplicativo = this.aplicativoRepository.save( aplicativo );

		PerfilAcesso perfilAcesso = new PerfilAcesso( null, false, true, "Público", "Perfil público", aplicativo, null );
		perfilAcessoRepository.save( perfilAcesso );
		return aplicativo;

	}

	/**
	 * 
	 * @param perfilAcesso
	 * @return
	 */
	public PerfilAcesso insertPerfilAcesso( PerfilAcesso perfilAcesso )
	{
		return this.perfilAcessoRepository.save( perfilAcesso );
	}

	/**
	 * 
	 * @param perfilAcessoId
	 * @param permissaoId
	 * @return
	 */
	public Permissao insertPermissao( Permissao permissao )
	{
		return this.permissaoRepository.save( permissao );
	}

	/**
	 * 
	 * @param perfilAcessoId
	 * @param permissaoId
	 * @return
	 */
	public PerfilAcessoPermissao insertPerfilAcessoPermissao( Long perfilAcessoId, Long permissaoId )
	{
		return this.perfilAcessoPermissaoRepository.save( new PerfilAcessoPermissao( null, new PerfilAcesso( perfilAcessoId ), new Permissao( permissaoId ) ) );
	}

	/**
	 * 
	 * @param filters
	 * @param ativo
	 * @param pageable
	 * @return
	 */

	public Page<Aplicativo> listAplicativosByFilters( String filters, Boolean ativo, PageRequest pageable )
	{
		return this.aplicativoRepository.listByFilters( filters, ativo, pageable );
	}

	/**
	 * 
	 * @param filters
	 * @param aplicativoId
	 * @param pageable
	 * @return
	 */
	public Page<PerfilAcesso> listPerfisAcessoByFilters( String filters, Long aplicativoId, PageRequest pageable )
	{
		return this.perfilAcessoRepository.listByFilters( filters, aplicativoId, pageable );
	}

	/**
	 * 
	 * @param aplicativoId
	 * @return
	 */
	public List<PerfilAcesso> listPerfisAcessoByAplicativoId( Long aplicativoId )
	{
		return this.perfilAcessoRepository.findAllByAplicativoId( aplicativoId );
	}

	/**
	 * 
	 * @param aplicativoId
	 * @return
	 */
	public List<Permissao> listPermissoesByAplicativoId( Long aplicativoId )
	{
		return this.permissaoRepository.findAllByAplicativoId( aplicativoId );
	}

	/**
	 * 
	 * @param aplicativoId
	 * @return
	 */
	public List<Permissao> listPerfilAcessoPermissoesByPerfilAcessoId( Long perfilAcessoId )
	{
		return this.perfilAcessoPermissaoRepository.findAllPerfilAcessoPermissoesByPerfilAcessoId( perfilAcessoId );
	}

	/**
	 * 
	 * @param perfilAcessoId
	 */
	public void removePerfilAcesso( Long perfilAcessoId )
	{
		Assert.isTrue( this.perfilAcessoRepository.isUsed( perfilAcessoId ).size() == 0, "Esse perfil de acesso é utilizado por um ou mais usuários" );
		this.perfilAcessoRepository.delete( perfilAcessoId );
	}

	/**
	 * 
	 * @param perfilAcessoPermissoesId
	 */
	public void removePerfilAcessoPermissoes( List<Long> perfilAcessoPermissoesId )
	{

		final List<PerfilAcessoPermissao> perfilAcessoPermissoes = new ArrayList<>();

		for ( Long perfilAcessoPermissaoId : perfilAcessoPermissoesId )
		{
			perfilAcessoPermissoes.add( new PerfilAcessoPermissao( perfilAcessoPermissaoId ) );
		}

		this.perfilAcessoPermissaoRepository.deleteInBatch( perfilAcessoPermissoes );

	}

	/**
	 * 
	 * @param aplicativo
	 * @return
	 */
	public Aplicativo updateAplicativo( Aplicativo aplicativo )
	{
		return this.aplicativoRepository.save( aplicativo );
	}

	/**
	 * 
	 * @param perfilAcesso
	 * @return
	 */
	public PerfilAcesso updatePerfilAcesso( PerfilAcesso perfilAcesso )
	{
		if ( perfilAcesso.getEditavel() )
		{
			return this.perfilAcessoRepository.save( perfilAcesso );
		}
		return null;
	}

	/**
	 * 
	 * @param fileTransfer
	 * @param aplicativoId
	 * @throws IOException
	 * @throws RepositoryException
	 */
	public void updateIcone( FileTransfer fileTransfer, Long aplicativoId )
	{
		Assert.notNull( fileTransfer, "O arquivo não pode ser nulo" );
		Assert.notNull( aplicativoId, "O aplicativo não pode ser nulo" );
		Assert.isTrue( Arrays.asList( Aplicativo.ICONE_MIME_TYPES ).contains( fileTransfer.getMimeType() ), "Tipo não permitido" );

		try
		{
			MetaFile metaFile = new MetaFile();
			metaFile.setContentType( fileTransfer.getMimeType() );
			metaFile.setFolder( String.format( Aplicativo.ICONE_PATH, aplicativoId ) );
			metaFile.setName( fileTransfer.getFilename() );
			metaFile.setInputStream( fileTransfer.getInputStream() );
			metaFile.setId( String.format( Aplicativo.ICONE_PATH, aplicativoId ) );
			metaFile = this.metaFileRepository.insert( metaFile );
		}
		catch ( Exception e )
		{
			throw new RuntimeException( "Não foi possível realizar o upload do ícone." );
		}
	}

	/**
	 * 
	 * @param fileTransfer
	 * @param aplicativoId
	 * @throws IOException
	 * @throws RepositoryException
	 */
	public FileTransfer findIconeAplicativoById( final Long aplicativoId )
	{
		Assert.notNull( aplicativoId, "O aplicativo não pode ser nulo" );

		try
		{
			final MetaFile metaFile = this.metaFileRepository.findById( String.format( Aplicativo.ICONE_PATH, aplicativoId ), true );

			return new FileTransfer( metaFile.getName(), metaFile.getContentType(), metaFile.getInputStream() );

		}
		catch ( RepositoryException e )
		{
			e.printStackTrace();
			return null;
			// throw new RuntimeException( "Não foi possível realizar o upload do arquivo." );
			// Segundo a diretiva do front-end, deve retornar null caso não exista ícone
		}
	}

	/**
	 * 
	 * @param aplicativoId
	 * @throws RepositoryException
	 */
	public void removeIcone( Long aplicativoId )
	{
		try
		{
			this.metaFileRepository.remove( String.format( Aplicativo.ICONE_PATH, aplicativoId ) );
		}
		catch ( RepositoryException e )
		{
			throw new RuntimeException( "Não foi possível remover o arquivo." );
		}
	}

	/**
	 * 
	 * @param aplicativosId
	 */
	public void removeAplicativos( List<Long> aplicativosId )
	{
		final List<Aplicativo> aplicativos = new ArrayList<>();

		for ( Long aplicativoId : aplicativosId )
		{
			aplicativos.add( new Aplicativo( aplicativoId ) );
		}

		this.aplicativoRepository.deleteInBatch( aplicativos );

		for ( Long aplicativoId : aplicativosId )
		{
			try
			{
				this.removeIcone( aplicativoId );
			}
			catch ( Exception e )
			{
				// Estoura exceção quando o aplicativo não tem ícone
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param aplicativosId
	 * @param mensagemDesativacao
	 */
	public void disableAplicativos( List<Long> aplicativosId, String mensagemDesativacao )
	{
		for ( Aplicativo aplicativo : this.aplicativoRepository.findAll( aplicativosId ) )
		{
			aplicativo.setAtivo( false );
			aplicativo.setMensagemDesativacao( mensagemDesativacao );
			this.updateAplicativo( aplicativo );
		}
	}

	/**
	 * 
	 * @param aplicativosId
	 */
	public void enableAplicativos( List<Long> aplicativosId )
	{
		for ( Aplicativo aplicativo : this.aplicativoRepository.findAll( aplicativosId ) )
		{
			aplicativo.setAtivo( true );
			aplicativo.setMensagemDesativacao( null );
			this.updateAplicativo( aplicativo );
		}
	}

	/**
	 * @param request
	 * @return
	 */
	public Aplicativo findAplicativoByEndereco( HttpServletRequest request )
	{

		// Ajusta o endereço
		String endereco = extractAddress( request );

		return this.aplicativoRepository.findByEnderecoIgnoreCase( endereco );
	}

	private String extractAddress( HttpServletRequest request )
	{		
		String endereco = request.getProtocol().toLowerCase().contains( "https" ) ? "https://" : "http://";
		endereco += request.getServerName() + ":" + request.getServerPort();
		return endereco;
	}

}
