package br.mil.mar.autenticador.dabm.test.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestExecutionListeners.MergeMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import br.com.eits.common.infrastructure.dbunit.DBUnitOperationLookup;
import br.mil.mar.TestApplication;
import br.mil.mar.dabm.autenticador.Application;
import br.mil.mar.dabm.common.domain.entity.usuario.Usuario;

/**
 * 
 * @author rodrigo@eits.com.br
 */
@IntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes =
{ TestApplication.class, Application.class })
@TestExecutionListeners(mergeMode = MergeMode.MERGE_WITH_DEFAULTS, listeners =
{ TransactionDbUnitTestExecutionListener.class, })
@DatabaseSetup(value = "/dataset/AbstractDataSet.xml", type = DatabaseOperation.TRUNCATE_TABLE)
@DbUnitConfiguration(databaseOperationLookup = DBUnitOperationLookup.class, databaseConnection = "dbUnitDatabaseDataSourceConnectionFactoryBean")
public abstract class AbstractIntegrationTests
{
	/*-------------------------------------------------------------------
	 *                           ATTRIBUTES
	 *-------------------------------------------------------------------*/
//	/**
//	 *
//	 */
//	@Autowired
//	private IUsuarioRepository usuarioRepository;

	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/

	/*-------------------------------------------------------------------
	 *                           BEHAVIORS
	 *-------------------------------------------------------------------*/
//	/**
//	 * 
//	 * @param userId
//	 */
//	protected void authenticate( long userId )
//	{
//		final Usuario user = this.usuarioRepository.findOne( userId );
//
//		final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken( user, null, user.getAuthorities() );
//		SecurityContextHolder.createEmptyContext();
//		SecurityContextHolder.getContext().setAuthentication( token );
//	}

	/**
	 * 
	 * @param userId
	 */
	protected void authenticate()
	{
		final Usuario user = new Usuario( 5L, "test", "test", "test@test", null, null, null, null, null, null, null );

		// Fake authorities
		Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();

		Set<String> authorities = new HashSet<String>( Arrays.asList( "Autenticador.usuarioService.findUsuarioById.desbloquearUsuarios", "Autenticador.usuarioService.findUsuarioById.excluirUsuarios", "Autenticador.usuarioService.findUsuarioById.insertUsuario", "Autenticador.usuarioService.getLoggedUser.listAplicativosByUserLogged", "Autenticador.usuarioService.findUsuarioById.findFotoUsuarioById", "Autenticador.usuarioService.findUsuarioById.removeFotoUsuario",
				"Autenticador.usuarioService.findUsuarioById.changeSenhaUsuario", "Autenticador.usuarioService.findUsuarioById.enviarEmail", "Autenticador.usuarioService.findUsuarioById.listUsuariosByFiltersAndBloqueados", "Autenticador.usuarioService.findUsuarioById.listAplicativoByUsuarioId", "Autenticador.usuarioService.findUsuarioById.insertPerfilUsuarioAplicativo", "Autenticador.usuarioService.findUsuarioById.replicarPerfisAcesso", "Autenticador.configuracaoService",
				"Autenticador.usuarioService.findUsuarioById", "Autenticador.usuarioService.findUsuarioById.excluirUsuario", "Autenticador.usuarioService.findUsuarioById.restaurarUsuarios", "Autenticador.usuarioService.getLoggedUser", "Autenticador.usuarioService.checkEmail", "Autenticador.usuarioService.findUsuarioById.uploadFotoUsuario", "Autenticador.usuarioService.findUsuarioById.updateUsuario", "Autenticador.usuarioService.findUsuarioById.resetSenhaUsuario",
				"Autenticador.usuarioService.findUsuarioById.bloquearUsuarios", "Autenticador.usuarioService.findUsuarioById.listUsuariosByFiltersAndExcluidos", "Autenticador.usuarioService.findUsuarioById.listPerfilUsuarioAplicativoByUsuarioId", "Autenticador.usuarioService.findUsuarioById.removePerfilUsuarioAplicativo", "Autenticador.usuarioService.findUsuarioById.listPerfilUsuarioAplicativoHistoricoByUsuarioId", "Autenticador.usuarioService.checkLogin", "Autenticador.aplicativoService",
				"Autenticador.usuarioService", "Autenticador.usuarioService.findUsuarioById.listUsuariosByFilters", "Autenticador.usuarioService.getUserLogged.uploadFotoUsuario", "Autenticador.usuarioService.getUserLogged.removeFotoUsuario", "Autenticador.usuarioService.getUserLogged.changeSenhaUsuario", "Autenticador.usuarioService.getUserLogged.findFotoUsuarioById" ) );

		authorities.forEach( authority -> {
			grantedAuthorities.add( new SimpleGrantedAuthority( authority ) );
		} );

		final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken( user, null, grantedAuthorities );
		SecurityContextHolder.createEmptyContext();
		SecurityContextHolder.getContext().setAuthentication( token );
	}
}
