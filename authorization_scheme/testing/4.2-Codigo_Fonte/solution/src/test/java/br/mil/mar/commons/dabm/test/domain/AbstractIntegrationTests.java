package br.mil.mar.commons.dabm.test.domain;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestExecutionListeners.MergeMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.eits.common.infrastructure.dbunit.DBUnitOperationLookup;
import br.mil.mar.TestApplication;
import br.mil.mar.dabm.autenticador.Application;
import br.mil.mar.dabm.autenticador.domain.entity.usuario.Usuario;
import br.mil.mar.dabm.autenticador.domain.repository.usuario.IUsuarioRepository;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

/**
 * 
 * @author rodrigo@eits.com.br
 */
@IntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestApplication.class, Application.class})
@TestExecutionListeners(mergeMode=MergeMode.MERGE_WITH_DEFAULTS, 
	listeners={
		TransactionDbUnitTestExecutionListener.class,
	}
)
@DatabaseSetup(value="/dataset/AbstractDataSet.xml", type=DatabaseOperation.TRUNCATE_TABLE)
@DbUnitConfiguration( databaseOperationLookup=DBUnitOperationLookup.class, databaseConnection="dbUnitDatabaseDataSourceConnectionFactoryBean")
public abstract class AbstractIntegrationTests
{
	/*-------------------------------------------------------------------
     *                           ATTRIBUTES
     *-------------------------------------------------------------------*/
    /**
     *
     */
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	
	/*-------------------------------------------------------------------
     *                           BEHAVIORS
     *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param userId
	 */
	protected void authenticate( long userId )
    {
		final Usuario user = this.usuarioRepository.findOne( userId );
		final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken( user, null, user.getAuthorities() );
		SecurityContextHolder.createEmptyContext();
		SecurityContextHolder.getContext().setAuthentication( token );
    }
}
