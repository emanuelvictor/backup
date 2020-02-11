/**
 * 
 */
package br.mil.mar.dabm.common.infrastructure.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

/**
 * @author emanuelvictor
 *
 */
public abstract class AbstractResourceDelegate
{

	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Value("${oauth.resource:http://marinha.sbox.me}")
	protected String baseUrl;

	/**
	 *
	 */
	protected OAuth2RestOperations restTemplate;

	/*-------------------------------------------------------------------
	 *				 		     GETTERS AND SETTERS
	 *-------------------------------------------------------------------*
	
	/**
	 * 
	 * @param clientCredentialsResourceDetails
	 */
	@Autowired
	public void setClientCredentialsResourceDetails( ClientCredentialsResourceDetails clientCredentialsResourceDetails )
	{
		this.restTemplate = new OAuth2RestTemplate( clientCredentialsResourceDetails );
	}

}
