package br.mil.mar.dabm.autenticador.domain.repository.aplicativo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.mil.mar.dabm.autenticador.domain.entity.aplicativo.Permissao;

/**
 * 
 * @author rodrigo@eits.com.br 
 * @since 22/04/2014
 * @version 1.0
 */
public interface IPermissaoRepository extends JpaRepository<Permissao, Long>
{

	/**
	 * 
	 * @param aplicativoId
	 * @return
	 */
	@Query(value="SELECT permissao " +
				  "FROM Permissao permissao " +
				  "WHERE ( permissao.aplicativo.id = :aplicativoId )"
				  + "AND (permissao.permissaoSuperior.id is null)")
	List<Permissao> findAllByAplicativoId( @Param("aplicativoId") Long aplicativoId );
}
