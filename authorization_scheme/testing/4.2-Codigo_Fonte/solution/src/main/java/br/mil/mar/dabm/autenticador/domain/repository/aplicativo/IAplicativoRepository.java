package br.mil.mar.dabm.autenticador.domain.repository.aplicativo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.mil.mar.dabm.autenticador.domain.entity.aplicativo.Aplicativo;

/**
 * 
 * @author rodrigo@eits.com.br 
 * @since 22/04/2014
 * @version 1.0
 */
public interface IAplicativoRepository extends JpaRepository<Aplicativo, Long>  
{

	/**
	 * 
	 * @param filters
	 * @param ativo
	 * @param pageable
	 * @return
	 */
	//new Aplicativo( aplicativo.id, aplicativo.nome, aplicativo.ativo, aplicativo.perfisDinamicos, aplicativo.codigo, aplicativo.versao, aplicativo.descricao, aplicativo.versaoEstavel, aplicativo.endereco, aplicativo.mensagemDesativacao, aplicativo.refreshToken )
	@Query(value="SELECT aplicativo " +
				   "FROM Aplicativo aplicativo " +
				  "WHERE ( (FILTER(aplicativo.endereco, :filters) = TRUE " +
				  "OR  FILTER(aplicativo.nome, :filters) = TRUE) " +
				  "AND ( aplicativo.ativo = :ativo ) ) ")
	Page<Aplicativo> listByFilters( @Param("filters") String filters, @Param("ativo") Boolean ativo, Pageable pageable );
	
}
