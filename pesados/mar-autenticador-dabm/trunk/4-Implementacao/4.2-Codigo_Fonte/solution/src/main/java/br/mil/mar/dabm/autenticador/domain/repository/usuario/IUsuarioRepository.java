package br.mil.mar.dabm.autenticador.domain.repository.usuario;

import java.util.Calendar;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.mil.mar.dabm.common.domain.entity.usuario.Usuario;

/**
 * 
 * @author emanuelvictor 
 * @version 1.0
 * @category Repository
 */
public interface IUsuarioRepository extends JpaRepository<Usuario, Long>
{
		
	/**
	 * 
	 * @param login
	 * @return
	 */
	Usuario findByLoginIgnoreCase( String login);
	
	/**
	 * 
	 * @param filters
	 * @param dataHoje
	 * @param pageable
	 * @return
	 */  
	@Query(value="SELECT new Usuario ( usuario.id, usuario.nomeCompleto, usuario.login, usuario.email, usuario.dataAlteracaoSenha, usuario.dataBloqueio, usuario.dataDesbloqueio, usuario.dataExclusao, usuario.dataExpiracaoSenha, usuario.dataUltimoAcesso, usuario.alterarSenha) " +
			   "FROM Usuario usuario " +
			   "WHERE ( "
			   			+ "("
			   				+ "(FILTER(usuario.nomeCompleto, :filters) = TRUE OR FILTER(usuario.login, :filters) = TRUE) AND "
						  	+" ( (:perfil = NULL OR :perfil = '') OR usuario.id in ( SELECT perfilUsuarioAplicativo.usuario.id FROM PerfilUsuarioAplicativo perfilUsuarioAplicativo "
			  				+ "			WHERE ( (FILTER(perfilUsuarioAplicativo.perfilAcesso.nome, :perfil) = TRUE) AND (FILTER(perfilUsuarioAplicativo.perfilAcesso.aplicativo.endereco, :aplicativo) = TRUE) ) ) )"
		  				+ ")"
				  	+ "AND ((usuario.dataBloqueio > :dataHoje OR usuario.dataDesbloqueio < :dataHoje OR usuario.dataBloqueio IS NULL) AND (usuario.dataExclusao IS NULL))"
				  	+ ")")
	Page<Usuario> listByFilters( 	@Param("filters") String filters, 
									@Param("perfil") String perfil, 
									@Param("aplicativo") String aplicativo,
									@Param("dataHoje") Calendar dataHoje, 
									Pageable pageable );
	/**
	 * 
	 * @param filters
	 * @param dataHoje
	 * @param pageable	
	 * @return
	 */
	@Query(value="SELECT new Usuario ( usuario.id, usuario.nomeCompleto, usuario.login, usuario.email, usuario.dataAlteracaoSenha, usuario.dataBloqueio, usuario.dataDesbloqueio, usuario.dataExclusao, usuario.dataExpiracaoSenha, usuario.dataUltimoAcesso, usuario.alterarSenha) " +
			   "FROM Usuario usuario " +
			  "WHERE ("
					  + "("
						  + "(FILTER(usuario.nomeCompleto, :filters) = TRUE OR FILTER(usuario.login, :filters) = TRUE) AND "
						  +" ( (:perfil = NULL OR :perfil = '') OR usuario.id in ( SELECT perfilUsuarioAplicativo.usuario.id FROM PerfilUsuarioAplicativo perfilUsuarioAplicativo "
						  + "			WHERE ( (FILTER(perfilUsuarioAplicativo.perfilAcesso.nome, :perfil) = TRUE) AND (FILTER(perfilUsuarioAplicativo.perfilAcesso.aplicativo.endereco, :aplicativo) = TRUE) ) ) )"
					  + ")"
				  	 + "AND (((:dataHoje > usuario.dataBloqueio AND usuario.dataDesbloqueio IS NULL) OR (:dataHoje BETWEEN usuario.dataBloqueio AND usuario.dataDesbloqueio)) AND (usuario.dataExclusao IS NULL))"
			  	 + ")")
	Page<Usuario> listByFiltersAndBloqueados( 	@Param("filters") String filters, 
												@Param("perfil") String perfil, 
												@Param("aplicativo") String aplicativo,
												@Param("dataHoje") Calendar dataHoje,
												Pageable pageable );
	/**
	 * 
	 * @param filters
	 * @param pageable
	 * @return
	 */
	@Query(value="SELECT new Usuario ( usuario.id, usuario.nomeCompleto, usuario.login, usuario.email, usuario.dataAlteracaoSenha, usuario.dataBloqueio, usuario.dataDesbloqueio, usuario.dataExclusao, usuario.dataExpiracaoSenha, usuario.dataUltimoAcesso, usuario.alterarSenha) " +
			   "FROM Usuario usuario " +
			  "WHERE ( "
		  				+ "("
							   +" (FILTER(usuario.nomeCompleto, :filters) = TRUE OR FILTER(usuario.login, :filters) = TRUE) AND "
							   +" ( (:perfil = NULL OR :perfil = '') OR usuario.id in ( SELECT perfilUsuarioAplicativo.usuario.id FROM PerfilUsuarioAplicativo perfilUsuarioAplicativo "
							   + "			WHERE ( (FILTER(perfilUsuarioAplicativo.perfilAcesso.nome, :perfil) = TRUE) AND (FILTER(perfilUsuarioAplicativo.perfilAcesso.aplicativo.endereco, :aplicativo) = TRUE) ) ) )"
					     + ")"
				  	 + "AND (usuario.dataExclusao IS NOT NULL)"
			  	 + ")")
	Page<Usuario> listByFiltersAndExcluidos( 	@Param("filters") String filters, 
												@Param("perfil") String perfil, 
												@Param("aplicativo") String aplicativo,
												Pageable pageable );
	
}
