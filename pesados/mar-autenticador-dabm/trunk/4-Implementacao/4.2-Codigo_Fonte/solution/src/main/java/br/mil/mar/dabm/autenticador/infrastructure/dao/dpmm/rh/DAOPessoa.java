/**
 * 
 */
package br.mil.mar.dabm.autenticador.infrastructure.dao.dpmm.rh;

import java.util.Set;

import br.mil.mar.dpmm.rh.domain.entity.Pessoa;

/**
 * @author emanuelvictor
 *
 */
public interface DAOPessoa
{

	/**
	 * @param id
	 * @return
	 */
	Pessoa findById( Long id );

	/**
	 * @param id
	 * @return
	 */
	Pessoa findByNIP( String nip );

	/**
	 * @param filters
	 * @param pageRequest
	 */
	Set<Pessoa> listPessoaByFilters( String filters );

}
