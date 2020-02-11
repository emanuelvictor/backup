package br.mil.mar.dabm.autenticador.domain.repository.aplicativo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.mil.mar.dabm.common.domain.entity.aplicativo.Aplicativo;

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
	@Query(value = "SELECT new Aplicativo( aplicativo.id, aplicativo.nome, aplicativo.ativo, aplicativo.perfisDinamicos, aplicativo.identificador, aplicativo.versao, aplicativo.descricao, aplicativo.endereco, aplicativo.mensagemDesativacao, aplicativo.refreshToken ) " 
			+ "FROM Aplicativo aplicativo " + "WHERE ( (FILTER(aplicativo.nome, :filters) = TRUE " + "OR FILTER(aplicativo.descricao, :filters) = TRUE " + "OR  FILTER(aplicativo.endereco, :filters) = TRUE) " + "AND ( aplicativo.ativo = :ativo ) ) ")
	Page<Aplicativo> listByFilters( @Param("filters" ) String filters, @Param("ativo") Boolean ativo, Pageable pageable);

	/**
	 * 
	 * @param identificador
	 * @return
	 */
	Aplicativo findByIdentificador( String identificador );

	/**
	 * 
	 * @param endereco
	 * @return
	 */
	Aplicativo findByEnderecoIgnoreCase( String endereco );

}
