package br.mil.mar.autenticador.dabm.test.domain.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.jcr.RepositoryException;

import org.directwebremoting.io.FileTransfer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import br.mil.mar.autenticador.dabm.test.domain.AbstractIntegrationTests;
import br.mil.mar.dabm.autenticador.domain.repository.usuario.IPerfilUsuarioAplicativoRepository;
import br.mil.mar.dabm.autenticador.domain.repository.usuario.IUsuarioRepository;
import br.mil.mar.dabm.autenticador.domain.service.UsuarioService;
import br.mil.mar.dabm.common.domain.entity.usuario.PerfilUsuarioAplicativo;
import br.mil.mar.dabm.common.domain.entity.usuario.Usuario;

/**
 * 
 * @author rodrigo@eits.com.br
 */
public class UsuarioServiceTests extends AbstractIntegrationTests
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private UsuarioService usuarioService;
	/**
	 * 
	 */
	@Autowired
	private IPerfilUsuarioAplicativoRepository perfilUsuarioAplicativoRepository;
	/**
	 * 
	 */
	@Autowired
	private IUsuarioRepository usuarioRepository;
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

	/*-------------------------------------------------------------------
	 *				 		      TESTS
	 *-------------------------------------------------------------------*/
	@Before
	public void autenticate()
	{
		this.authenticate();
	}

	/**
	 * 
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value =
	{ "/dataset/aplicativo/AplicativoDataSet.xml", "/dataset/aplicativo/PerfilAcessoDataSet.xml", "/dataset/usuario/ConfiguracaoDataSet.xml" })
	public void insertUsuarioMustPass()
	{

		Calendar dataBloqueio = Calendar.getInstance();
		Calendar dataDesbloqueio = Calendar.getInstance();
		Calendar dataExpiracaoSenha = Calendar.getInstance();

		dataExpiracaoSenha.set( Calendar.DAY_OF_YEAR, 3 );

		dataBloqueio.set( Calendar.DAY_OF_YEAR, 350 );

		dataDesbloqueio.set( Calendar.DAY_OF_YEAR, 355 );

		// Testa se o nome do usuário é igual a váriavel nomeUsuario (Comportamento de usuário sem pessoa)
		Usuario usuario = new Usuario( null, null, "nome do Usuário", "login", "test@email", Calendar.getInstance() /* dataAlteracaoSenha */, dataBloqueio, dataDesbloqueio, Calendar.getInstance() /* dataExclusao */, Calendar.getInstance() /* dataExpiracaoSenha */, Calendar.getInstance() /* dataUltimoAcesso */, null );
		usuario.setSenha( "test" );
		usuario = this.usuarioService.insertUsuario( usuario );

//		Assert.assertNotNull( usuario );
//		Assert.assertNotNull( usuario.getId() );
//		Assert.assertNotNull( usuario.getCreated() );
//		Assert.assertFalse( usuario.isEnabled() );
//		Assert.assertFalse( usuario.getPassword().equals( "usuario" ) );
	}

	/**
	 * 
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value =
	{ "/dataset/usuario/UsuarioDataSet.xml", })
	public void updateUsuarioMustPass()
	{

		Usuario usuario = this.usuarioService.findUsuarioById( 1l );

		usuario.setNomeCompleto( "outro NOME" );

		Assert.assertNotNull( usuario );
		Assert.assertNotNull( usuario.getId() );
		Assert.assertNotNull( usuario.getCreated() );
		Assert.assertEquals( usuario.getNomeCompleto(), "outro NOME" );
	}

	/**
	 * 
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value =
	{ "/dataset/usuario/UsuarioDataSet.xml", })
	public void findUsuarioByIdMustPass()
	{
		final Usuario usuario = this.usuarioService.findUsuarioById( 1L );

		Assert.assertNotNull( usuario );
		Assert.assertNotNull( usuario.getId() );
		Assert.assertNotNull( usuario.getCreated() );
	}

	/**
	 * 
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value =
	{ "/dataset/usuario/UsuarioDataSet.xml", })
	public void listUsuariosByFiltersMustReturn0()
	{
		final Page<Usuario> usuarios = this.usuarioService.listUsuariosByFilters( "000001", null, null, null );

		Assert.assertNotNull( usuarios );
		Assert.assertEquals( 0, usuarios.getSize() );
	}

	/**
	 * 
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value =
	{ "/dataset/usuario/UsuarioDataSet.xml" })
	public void listUsuariosByFiltersMustReturn1()
	{
		final Page<Usuario> usuarios = this.usuarioService.listUsuariosByFilters( "04", null, null, null );

		Assert.assertNotNull( usuarios );
		Assert.assertEquals( 1, usuarios.getSize() );
		Assert.assertNotNull( usuarios.getContent().get( 0 ).getId() );
		Assert.assertNotNull( usuarios.getContent().get( 0 ).getLogin() );
		Assert.assertNotNull( usuarios.getContent().get( 0 ).getNomeCompleto() );

	}

	/**
	 * 
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value =
	{ "/dataset/usuario/UsuarioDataSet.xml" })
	public void listUsuariosByFiltersAndDataBloqueioMustReturn1()
	{

		final Page<Usuario> usuarios = this.usuarioService.listUsuariosByFiltersAndBloqueados( "03", null, null, null );

		Assert.assertNotNull( usuarios );
		Assert.assertEquals( 1, usuarios.getSize() );
	}

	/**
	 * 
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value =
	{ "/dataset/usuario/UsuarioDataSet.xml", })
	public void listUsuariosByFiltersAndBloqueadosMustReturn2()
	{
		final Page<Usuario> usuarios = this.usuarioService.listUsuariosByFiltersAndBloqueados( "02,03", null, null, null );

		Assert.assertNotNull( usuarios );
		Assert.assertEquals( 2, usuarios.getSize() );
	}

	/**
	 * 
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value =
	{ "/dataset/usuario/UsuarioDataSet.xml", })
	public void listUsuariosByFiltersMustAllDesbloqueados()
	{
		final Page<Usuario> usuarios = this.usuarioService.listUsuariosByFilters( null, null, null, null );

		Assert.assertNotNull( usuarios );
		Assert.assertEquals( 3, usuarios.getSize() );
	}

	/**
	 * 
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value =
	{ "/dataset/usuario/UsuarioDataSet.xml", "/dataset/usuario/ConfiguracaoDataSet.xml" })
	public void resetPasswordAndSendEmailMustPass() throws Exception
	{
		this.usuarioService.resetSenhaUsuario( 1L, "123456" );
		Usuario usuario = this.usuarioService.findUsuarioById( 1L );
		Assert.assertEquals( this.passwordEncoder.encodePassword( "123456", saltSource.getSalt( usuario ) ), usuario.getSenha() );
	}

	/**
	 * 
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value =
	{ "/dataset/usuario/UsuarioDataSet.xml", "/dataset/usuario/ConfiguracaoDataSet.xml" })
	public void changePasswordAndSendEmailMustPass() throws Exception
	{
		this.usuarioService.changeSenhaUsuario( 5L, "123456", "654321" );
		Usuario usuario = this.usuarioService.findUsuarioById( 5L );
		Assert.assertEquals( this.passwordEncoder.encodePassword( "654321", saltSource.getSalt( usuario ) ), usuario.getSenha() );
	}

	/**
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	@DatabaseSetup(type = DatabaseOperation.INSERT, value =
	{ "/dataset/usuario/UsuarioDataSet.xml" })
	public void changePasswordWithConfirmationPasswordMustFail() throws Exception
	{
		Usuario usuario = this.usuarioService.findUsuarioById( 5L );
		Assert.assertEquals( this.passwordEncoder.encodePassword( "123456", saltSource.getSalt( usuario ) ), usuario.getSenha() );

		this.usuarioService.changeSenhaUsuario( 5L, "senha incorreta", "novaSenha" );
		usuario = this.usuarioService.findUsuarioById( 5L );
		Assert.assertEquals( this.passwordEncoder.encodePassword( "novaSenha", saltSource.getSalt( usuario ) ), usuario.getSenha() );
	}

	/**
	 * 
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value =
	{ "/dataset/usuario/UsuarioDataSet.xml", "/dataset/aplicativo/AplicativoDataSet.xml", "/dataset/aplicativo/PerfilAcessoDataSet.xml" })
	public void insertPerfilUsuarioAplicativoMustPass()
	{
		final PerfilUsuarioAplicativo perfilUsuarioAplicativo = this.usuarioService.insertPerfilUsuarioAplicativo( 1L, 1L );
		Assert.assertNotNull( perfilUsuarioAplicativo );
	}

	/**
	 * 
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value =
	{ "/dataset/usuario/UsuarioDataSet.xml", "/dataset/aplicativo/AplicativoDataSet.xml", "/dataset/aplicativo/PerfilAcessoDataSet.xml", "/dataset/usuario/PerfilUsuarioAplicativoDataSet.xml", })
	public void removePerfilUsuarioAplicativoMustPass()
	{
		this.usuarioService.removePerfilUsuarioAplicativo( 1L, 1L );

		Assert.assertNull( this.perfilUsuarioAplicativoRepository.findOne( 1L ) );
	}

	/**
	 * 
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value =
	{ "/dataset/usuario/UsuarioDataSet.xml", "/dataset/aplicativo/AplicativoDataSet.xml", "/dataset/aplicativo/PerfilAcessoDataSet.xml", "/dataset/usuario/PerfilUsuarioAplicativoDataSet.xml", })
	public void listPerfilUsuarioAplicativosByUsuarioAplicativoIdMustPass()
	{
		Page<PerfilUsuarioAplicativo> perfisUsuarioAplicativo = this.usuarioService.listPerfilUsuarioAplicativoByUsuarioId( 1L, null );
		Assert.assertNotNull( perfisUsuarioAplicativo );
		Assert.assertEquals( perfisUsuarioAplicativo.getTotalElements(), 2 );

		perfisUsuarioAplicativo = this.usuarioService.listPerfilUsuarioAplicativoByUsuarioId( 2L, null );
		Assert.assertNotNull( perfisUsuarioAplicativo );
		Assert.assertEquals( perfisUsuarioAplicativo.getTotalElements(), 1 );

		perfisUsuarioAplicativo = this.usuarioService.listPerfilUsuarioAplicativoByUsuarioId( 3L, null );
		Assert.assertNotNull( perfisUsuarioAplicativo );
		Assert.assertEquals( perfisUsuarioAplicativo.getTotalElements(), 2L );
	}

	/**
	 * 
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value =
	{ "/dataset/usuario/UsuarioDataSet.xml" })
	public void bloquearAndDesbloquearUsuariosMustPass()
	{

		// Pegando todos os usuários da base de dados
		List<Usuario> usuarios = this.usuarioRepository.findAll();
		boolean testIsAccountNonLocked = false;
		boolean testIsEnable = false;
		final List<Long> listIds = new ArrayList<Long>();
		// Pegando todos os ids dos usuários
		for ( final Usuario usuario : usuarios )
		{
			listIds.add( usuario.getId() );
		}

		// Instanciado datas de bloqueio
		Calendar dataBloqueio = Calendar.getInstance();
		Calendar dataDesbloqueio = Calendar.getInstance();
		dataBloqueio.set( Calendar.DAY_OF_YEAR, 02 );
		dataBloqueio.set( Calendar.YEAR, 2015 );
		dataDesbloqueio.set( Calendar.DAY_OF_YEAR, 300 );
		dataDesbloqueio.set( Calendar.YEAR, 2015 );

		// Bloqueando todo mundo
		this.usuarioService.bloquearUsuarios( listIds, dataBloqueio, null );

		usuarios = this.usuarioService.listUsuariosByFiltersAndBloqueados( null, null, null, null ).getContent();
		for ( final Usuario usuario : usuarios )
		{
			testIsAccountNonLocked = usuario.isAccountNonLocked();
			testIsEnable = usuario.isEnabled();
		}

		// Verificando se todo mundo esta bloqueado e inativo
		Assert.assertNotNull( testIsAccountNonLocked );
		Assert.assertNotNull( testIsEnable );
		Assert.assertFalse( testIsAccountNonLocked );
		Assert.assertFalse( testIsEnable );

		// Desbloqueando todo mundo
		this.usuarioService.desbloquearUsuarios( listIds );
		// Restaurando todo mundo (para que o usuário esteja como 'isEnabled' ele não pode estar excluído)
		this.usuarioService.restaurarUsuarios( listIds );

		usuarios = this.usuarioService.listUsuariosByFilters( null, null, null, null ).getContent();
		for ( final Usuario usuario : usuarios )
		{
			testIsAccountNonLocked = usuario.isAccountNonLocked();
			testIsEnable = usuario.isEnabled();
		}

		// Verificando se todo mundo esta bloqueado e inativo
		Assert.assertNotNull( testIsAccountNonLocked );
		Assert.assertNotNull( testIsEnable );
		Assert.assertTrue( testIsAccountNonLocked );
		Assert.assertTrue( testIsEnable );

	}

	/**
	 * 
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value =
	{ "/dataset/usuario/UsuarioDataSet.xml" })
	public void excluirAndRestaurarUsuariosMustPass()
	{
		// Pegando todos os usuários da base de dados
		List<Usuario> usuarios = this.usuarioRepository.findAll();
		boolean testIsEnable = false;
		final List<Long> listIds = new ArrayList<Long>();
		// Pegando todos os ids dos usuários
		for ( final Usuario usuario : usuarios )
		{
			listIds.add( usuario.getId() );
		}

		// Excluindo todo mundo
		this.usuarioService.excluirUsuarios( listIds );

		usuarios = this.usuarioService.listUsuariosByFiltersAndExcluidos( null, null, null, null ).getContent();
		for ( final Usuario usuario : usuarios )
		{
			testIsEnable = usuario.isEnabled();
		}

		// Verificando se todo mundo esta excluido
		Assert.assertNotNull( testIsEnable );
		Assert.assertFalse( testIsEnable );

		// Restaurando todo mundo
		this.usuarioService.restaurarUsuarios( listIds );
		// Desbloqueando todo mundo (para que o usuário esteja como 'isEnabled' ele não pode estar bloqueado)
		this.usuarioService.desbloquearUsuarios( listIds );

		usuarios = this.usuarioService.listUsuariosByFilters( null, null, null, null ).getContent();
		for ( final Usuario usuario : usuarios )
		{
			testIsEnable = usuario.isEnabled();
		}

		// Verificando se todo mundo esta bloqueado e inativo
		Assert.assertNotNull( testIsEnable );
		Assert.assertTrue( testIsEnable );
	}

	/**
	 * 
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value =
	{ "/dataset/usuario/UsuarioDataSet.xml", "/dataset/aplicativo/AplicativoDataSet.xml", "/dataset/aplicativo/PerfilAcessoDataSet.xml", "/dataset/usuario/PerfilUsuarioAplicativoDataSet.xml" })
	public void replicarUsuariosMustPass()
	{
		this.usuarioService.replicarPerfisAcesso( 1L, Arrays.asList( 4L ) );
		Assert.assertEquals( 2, this.usuarioService.listPerfilUsuarioAplicativoByUsuarioId( 1L, null ).getContent().size() );
		Assert.assertEquals( 2, this.usuarioService.listPerfilUsuarioAplicativoByUsuarioId( 4L, null ).getContent().size() );
	}

	/**
	 * 
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value =
	{ "/dataset/usuario/UsuarioDataSet.xml" })
	public void uploadAndRemoveFotoMustPass() throws IOException
	{
		final String path = this.getClass().getResource( "/foto.jpg" ).getPath().replaceAll( "%20", " " );
		
		final FileInputStream file = new FileInputStream( path );
		final FileTransfer fileTransfer = new FileTransfer( "test.jpg", MediaType.IMAGE_JPEG_VALUE, file );

		this.usuarioService.uploadFotoUsuario( fileTransfer, 1L );

		this.usuarioService.removeFotoUsuario( 1L );
	}

	/**
	 * Teste de tratamento do mimeType, formato do arquivo
	 */
	@Test(expected = IllegalArgumentException.class)
	@DatabaseSetup(type = DatabaseOperation.INSERT, value =
	{ "/dataset/usuario/UsuarioDataSet.xml" })
	public void uploadFotoInPDFMustFail() throws IOException
	{

		final String path = this.getClass().getResource( "/example.pdf" ).getPath().replaceAll( "%20", " " );
		
		final FileInputStream file = new FileInputStream( path );
		
		final FileTransfer fileTransferSave = new FileTransfer( "test.pdf", "application/pdf", file );

		this.usuarioService.uploadFotoUsuario( fileTransferSave, 1L );

	}

	/**
	 * 
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value =
	{ "/dataset/usuario/UsuarioDataSet.xml" })
	public void findFotoByIdMustPass() throws IOException, RepositoryException
	{
		final String path = this.getClass().getResource( "/foto.jpg" ).getPath().replaceAll( "%20", " " );
		final FileInputStream file = new FileInputStream( path );
		final FileTransfer fileTransferSave = new FileTransfer( "test.jpg", MediaType.IMAGE_JPEG_VALUE, file );

		this.usuarioService.uploadFotoUsuario( fileTransferSave, 1L );

		final FileTransfer fileTransfer = this.usuarioService.findFotoUsuarioById( 1L );

		Assert.assertNotNull( fileTransfer );
		Assert.assertEquals( fileTransfer.getFilename(), "test.jpg" );
		Assert.assertEquals( fileTransfer.getMimeType(), "image/jpeg" );
	}

	/**
	 * 
	 */
	@Test
	public void listPerfilUsuarioAplicativoHistoricoByUsuarioIdMustReturnZero()
	{
		Assert.assertTrue( this.usuarioService.listPerfilUsuarioAplicativoHistoricoByUsuarioId( 106L ).size() == 0 );
	}
}