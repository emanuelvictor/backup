package br.mil.mar.dabm.autenticador.application.restful;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.mil.mar.dabm.autenticador.domain.service.OrganizacaoMilitarService;
import br.mil.mar.dabm.singra.domain.entity.OrganizacaoMilitar;

/**
 * 
 * @author rodrigo
 */
@RestController
@RequestMapping("/api")
public class OrganizacaoMilitarResource
{
	/*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	OrganizacaoMilitarService organizacaoMilitarService;

	/*-------------------------------------------------------------------
	 * 		 					CONTROLLERS
	 *-------------------------------------------------------------------*/


	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/organizacoesMilitares/{id}", method = RequestMethod.GET)
	public OrganizacaoMilitar findUserById( @PathVariable Long id )
	{
		return this.organizacaoMilitarService.findOrganizacaoMilitarById( id );
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/organizacoesMilitares", method = RequestMethod.GET)
	public Set<OrganizacaoMilitar> listUsersByFilters( @RequestParam(required = false ) String filters, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "100") int size)
	{
		return this.organizacaoMilitarService.listOrganizacaoMilitarByFilters( filters, new PageRequest( page, size ) );
	}

}
