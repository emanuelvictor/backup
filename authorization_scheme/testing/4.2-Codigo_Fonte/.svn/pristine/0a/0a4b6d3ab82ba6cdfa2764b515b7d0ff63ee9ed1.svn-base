package br.mil.mar.dabm.autenticador.domain.repository.usuario;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;

import br.mil.mar.dabm.autenticador.domain.entity.aplicativo.Aplicativo;
import br.mil.mar.dabm.autenticador.domain.entity.usuario.PerfilUsuarioAplicativo;


/**
 * 
 * @author emanuelvictor
 * @version 1.0
 * @category Repository
 */
public interface IPerfilUsuarioAplicativoRepository extends /*RevisionRepository<PerfilUsuarioAplicativo, Long, Integer>, */JpaRepository<PerfilUsuarioAplicativo, Long>
{
	/**
	 * 
	 * @param usuarioId
	 * @param pageable
	 * @return
	 */
	public Page<PerfilUsuarioAplicativo> findPerfilUsuarioAplicativosByUsuarioId( Long usuarioId, Pageable pageable );
	
	@Query(value = "SELECT p.perfilAcesso.aplicativo FROM PerfilUsuarioAplicativo p WHERE p.usuario.id = :usuarioId")
	public Set<Aplicativo> listAplicativoByUsuarioId( @Param("usuarioId") Long usuarioId);
	
//	Page<Revision<Integer, PerfilUsuarioAplicativo>> findRevisionsByUsuarioId(@Param("usuarioId") Long usuarioId, Pageable pageable);
	
//	@Query(nativeQuery = true, value = "SELECT * FROM auditoria.perfil_usuario_aplicativo_auditado "
//			+ "WHERE (auditoria.perfil_usuario_aplicativo_auditado.usuario_id = ?1)")
//	public List<Object> listPerfilUsuarioAplicativoHistoricoByUsuarioId(Long usuarioId);
	
	void deleteByUsuarioIdAndPerfilAcessoId(@Param("usuarioId") Long usuarioId, @Param("perfilAcessoId") Long perfilAcessoId);	
	
}