/**
 * 
 */
package br.mil.mar.dabm.autenticador.domain.service;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mil.mar.dabm.singra.domain.entity.OrganizacaoMilitar;

/**
 * @author Boz
 *
 */
@Service
@Transactional
@RemoteProxy(name = "organizacaoMilitarService")
public class OrganizacaoMilitarService
{
	/**
	 * 
	 * @param id
	 * @return
	 */
	public OrganizacaoMilitar findOrganizacaoMilitarById( Long id )
	{
		return null;
	}

	/**
	 * 
	 * @param filters
	 * @param pageable
	 * @return
	 */
	public Page<OrganizacaoMilitar> listOrganizacaoMilitarByFilters( String filters, PageRequest pageable )
	{
		return null;
	}
}
