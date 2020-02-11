/**
 * 
 */
package br.mil.mar.dabm.common.infrastructure.delegate;

import java.util.Arrays;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageableImpl;
import org.springframework.stereotype.Component;

import br.mil.mar.dabm.singra.domain.entity.OrganizacaoMilitar;

/**
 * @author emanuelvictor
 *
 */
@Component
public class OrganizacaoMilitarResourceDelegate extends AbstractResourceDelegate
{
	
	/*-------------------------------------------------------------------
	 *				 		     SERVICES
	 *-------------------------------------------------------------------*/

	/**
	 * 
	 * @param id
	 * @return
	 */
	public OrganizacaoMilitar findOrganizacaoMilitarById( Long id )
	{
		return this.restTemplate.getForObject( this.baseUrl + "/api/organizacoesMilitares/{id}", OrganizacaoMilitar.class, id );
	}

	/**
	 * 
	 * @param filters
	 * @param pageRequest
	 * @return
	 */
	public Page<OrganizacaoMilitar> listOrganizacoesMilitaresByFilters( String filters, PageRequest pageRequest )
	{
		OrganizacaoMilitar[] organizacoesMilitares = null;

		if ( pageRequest == null )
		{
			organizacoesMilitares = this.restTemplate.getForObject( this.baseUrl + "/api/organizacoesMilitares?filters={filters}", OrganizacaoMilitar[].class, filters );
			return new PageImpl<OrganizacaoMilitar>( Arrays.asList( organizacoesMilitares ) );
		}
		else
		{
			organizacoesMilitares = this.restTemplate.getForObject( this.baseUrl + "/api/organizacoesMilitares?filters={filters}&page={page}&size={size}", OrganizacaoMilitar[].class, filters, pageRequest.getPageNumber(), pageRequest.getPageSize() );
			pageRequest.setSize( organizacoesMilitares.length );
			return new PageImpl<OrganizacaoMilitar>( Arrays.asList( organizacoesMilitares ), new PageableImpl( pageRequest ), organizacoesMilitares.length );
		}

	}

}
