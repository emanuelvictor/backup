/**
 * 
 */
package br.mil.mar.dabm.common.infrastructure.delegate;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;

import br.mil.mar.dabm.common.domain.entity.aplicativo.Aplicativo;

/**
 * @author emanuelvictor
 *
 */
@Component
public class AplicativoResourceDelegate extends AbstractResourceDelegate
{

	/*-------------------------------------------------------------------
	 *				 		     SERVICES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param aplicativo
	 * @return
	 */
	public Aplicativo updateAplicativo( Aplicativo aplicativo )
	{
		return this.restTemplate.postForObject( this.baseUrl + "/api/aplicativo/", aplicativo, Aplicativo.class );
	}

	/**
	 * 
	 * @return
	 */
	public Aplicativo findAplicativo()
	{
		return this.restTemplate.getForObject( this.baseUrl + "/api/aplicativo", Aplicativo.class );
	}

	/**
	 * 
	 * @return
	 */
	public ByteArrayResource findIconeAplicativo()
	{
		return this.restTemplate.getForObject( this.baseUrl + "/api/aplicativo/icone", ByteArrayResource.class );
	}

}
