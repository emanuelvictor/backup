package br.mil.mar.dabm.autenticador.application.restful;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.mil.mar.dabm.autenticador.domain.service.AplicativoService;
import br.mil.mar.dabm.common.application.security.ContextHolder;
import br.mil.mar.dabm.common.domain.entity.aplicativo.Aplicativo;

/**
 * 
 * @author emanuelvictor
 *
 */
@RestController
@RequestMapping("/api")
public class AplicativoResource
{
	/*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/
	@Autowired
	AplicativoService aplicativoService;

	/*-------------------------------------------------------------------
	 * 		 					CONTROLLERS
	 *-------------------------------------------------------------------*/

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/aplicativo", method = RequestMethod.GET)
	public Aplicativo findAplicativoByIdentificador()
	{
		Aplicativo aplicativo = this.aplicativoService.findAplicativoByIdentificador( ContextHolder.getAuthenticatedUser().getLogin() );
		aplicativo.setPerfisAcesso( null );
		aplicativo.setVersaoEstavel( null );
		aplicativo.setNullAuthorities();
		return aplicativo;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/aplicativo", method = RequestMethod.PUT)
	public Aplicativo updateAplicativo( @RequestBody Aplicativo aplicativo )
	{
		aplicativo.setIdentificador( ContextHolder.getAuthenticatedUser().getLogin() );
		aplicativo = this.aplicativoService.updateAplicativo( aplicativo );
		aplicativo.setPerfisAcesso( null );
		aplicativo.setVersaoEstavel( null );
		return aplicativo;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/aplicativo/icone", method = RequestMethod.GET)
	public ByteArrayResource findIconeUsuario()
	{
		Aplicativo aplicativo = this.aplicativoService.findAplicativoByIdentificador( ContextHolder.getAuthenticatedUser().getLogin() );

		try
		{
			return new ByteArrayResource( IOUtils.toByteArray( this.aplicativoService.findIconeAplicativoById( aplicativo.getId() ).getInputStream() ) );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return null;
//			throw new RuntimeException( "Não foi possível realizar a requisição da foto do usuário" );
		}
	}

}
