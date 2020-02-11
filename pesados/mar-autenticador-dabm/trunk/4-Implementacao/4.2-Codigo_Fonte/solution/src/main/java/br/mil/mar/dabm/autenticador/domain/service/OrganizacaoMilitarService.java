/**
 * 
 */
package br.mil.mar.dabm.autenticador.domain.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.mil.mar.dabm.autenticador.infrastructure.dao.singra.DAOOrganizacaoMilitar;
import br.mil.mar.dabm.singra.domain.entity.OrganizacaoMilitar;

/**
 * @author Boz
 *
 */
@Service
public class OrganizacaoMilitarService
{
	/**
	 * 
	 */
	@Autowired
	DAOOrganizacaoMilitar daoOrganizacaoMilitar;

	/**
	 * 
	 * @param id
	 * @return
	 */
	public OrganizacaoMilitar findOrganizacaoMilitarById( Long id )
	{
		return daoOrganizacaoMilitar.findOrganizacaoMilitarById( id );
	}

	/**
	 * 
	 * @param filters
	 * @param pageable
	 * @return
	 */
	public Set<OrganizacaoMilitar> listOrganizacaoMilitarByFilters( String filters, PageRequest pageable /* TODO IMPLEMENTAR PAGEABLE PARA O BANCO LEGADO */ )
	{
		return daoOrganizacaoMilitar.listOrganizacaoMilitarByFilters( filters );
	}
}
