package br.mil.mar.dabm.autenticador.domain.repository.usuario;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;

import br.mil.mar.dabm.common.domain.entity.usuario.PerfilUsuarioAplicativo;


/**
 * 
 * @author emanuelvictor
 * @version 1.0
 * @category Repository
 */
public interface IPerfilUsuarioAplicativoRepository extends JpaRepository<PerfilUsuarioAplicativo, Long>/*, RevisionRepository<PerfilUsuarioAplicativo, Long, Integer>*/
{
	/**
	 * 
	 * @param usuarioId
	 * @param pageable
	 * @return
	 */
	public Page<PerfilUsuarioAplicativo> findPerfilUsuarioAplicativosByUsuarioId( Long usuarioId, Pageable pageable );
	
//	@Query(value = "SELECT p.perfilAcesso.aplicativo FROM PerfilUsuarioAplicativo p WHERE p.usuario.id = :usuarioId")
//	public Set<Aplicativo> listAplicativoByUsuarioId( @Param("usuarioId") Long usuarioId);
	
//	@Query(nativeQuery = true, value = "SELECT * FROM auditoria.perfil_usuario_aplicativo_auditado WHERE (auditoria.perfil_usuario_aplicativo_auditado.usuario_id = ?1)")
	/**
	 * 
	 * @param usuarioId
	 * @return
	 */
//	public Revisions<Integer, PerfilUsuarioAplicativo> findRevisionsByUsuarioId(Long usuarioId);
	
//	@Query(nativeQuery = true, value = "SELECT * FROM auditoria.perfil_usuario_aplicativo_auditado WHERE (auditoria.perfil_usuario_aplicativo_auditado.usuario_id = ?1)")
	public List<PerfilUsuarioAplicativo> findPerfilUsuarioAplicativoByPerfilAcessoId(Long perfilAcessoId);
	
	
	public List<PerfilUsuarioAplicativo> findRevisionsByUsuarioId(Long usuarioId);
	/**
	 * 
	 * @param usuarioId
	 * @param perfilAcessoId
	 */
	void deleteByUsuarioIdAndPerfilAcessoId(@Param("usuarioId") Long usuarioId, @Param("perfilAcessoId") Long perfilAcessoId);	
	/**
	 * 
	 * @param usuarioId
	 * @param aplicativoId
	 */
	@Modifying
	@Query(value = "DELETE FROM PerfilUsuarioAplicativo p WHERE (p.usuario.id = :usuarioId AND p.perfilAcesso.aplicativo.id = :aplicativoId)")
	void deleteByUsuarioIdAndAplicativoId(@Param("usuarioId") Long usuarioId, @Param("aplicativoId") Long aplicativoId);
}