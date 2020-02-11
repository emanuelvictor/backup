/**
 * 
 */
package br.mil.mar.dabm.autenticador.infrastructure.dao.singra;

import java.util.Set;

import br.mil.mar.dabm.singra.domain.entity.OrganizacaoMilitar;

/**
 * @author emanuelvictor
 *
 */
public interface DAOOrganizacaoMilitar
{

	/**
	 * @param id
	 * @return
	 */
	OrganizacaoMilitar findOrganizacaoMilitarById( Long id );

	/**
	 * @param filters
	 * @param pageable
	 * @return
	 */
	Set<OrganizacaoMilitar> listOrganizacaoMilitarByFilters( String filters );

}
