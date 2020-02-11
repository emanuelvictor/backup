package br.mil.mar.dabm.autenticador.domain.repository.aplicativo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.mil.mar.dabm.common.domain.entity.aplicativo.PerfilAcesso;

/**
 * 
 * @author rodrigo@eits.com.br
 * @since 22/04/2014
 * @version 1.0
 */
public interface IPerfilAcessoRepository extends JpaRepository<PerfilAcesso, Long>
{
	/**
	 * 
	 * @param nome
	 * @param aplicativoId
	 * @param pageable
	 * @return
	 */
	@Query(value="SELECT new PerfilAcesso( perfilAcesso.id, perfilAcesso.nome, perfilAcesso.aplicativo) " +
				  "FROM PerfilAcesso perfilAcesso " +
				  "WHERE ( FILTER(perfilAcesso.nome, :filters) = TRUE "
				  + "AND ( (perfilAcesso.aplicativo.id = :aplicativoId) OR (:aplicativoId IS NULL) ) )")
	Page<PerfilAcesso> listByFilters( @Param("filters") String filters, @Param("aplicativoId") Long aplicativoId, Pageable pageable );

	/**
	 * @param aplicativoId
	 * @return
	 */
	List<PerfilAcesso> findAllByAplicativoId( Long aplicativoId );
	
	
	/**
	 * @param aplicativoId
	 * @return
	 */
	@Query(value="SELECT perfilUsuarioAplicativo.perfilAcesso FROM PerfilUsuarioAplicativo perfilUsuarioAplicativo WHERE ( perfilUsuarioAplicativo.perfilAcesso.id = :perfilAcessoId) ")
	List<PerfilAcesso> isUsed( @Param("perfilAcessoId") Long perfilAcessoId );
	
}
