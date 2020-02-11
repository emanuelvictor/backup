/**
 * 
 */
package br.mil.mar.dabm.autenticador.infrastructure.dao.singra.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import br.mil.mar.dabm.autenticador.infrastructure.dao.Memory;
import br.mil.mar.dabm.autenticador.infrastructure.dao.singra.DAOOrganizacaoMilitar;
import br.mil.mar.dabm.singra.domain.entity.OrganizacaoMilitar;

/**
 * @author emanuelvictor
 *
 */
@Repository
public class DAOOrganizacaoMilitarImpl implements DAOOrganizacaoMilitar
{
	/**
	 * 
	 */
	Set<OrganizacaoMilitar> organizacoesMilitares = new HashSet<OrganizacaoMilitar>( Arrays.asList( new Memory().ORGANIZACOES_MILITARES ) );

	/**
	 * 
	 */
	@Override
	public OrganizacaoMilitar findOrganizacaoMilitarById( Long id )
	{
		if ( id != null )
		{
			for ( OrganizacaoMilitar organizacaoMilitar : organizacoesMilitares )
			{
				if ( organizacaoMilitar.getId() == id || organizacaoMilitar.getId().equals( id ) )
				{
					return organizacaoMilitar;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 */
	@Override
	public Set<OrganizacaoMilitar> listOrganizacaoMilitarByFilters( String filters )
	{
		if ( filters != null && filters.length() > 0 )
		{
			// Lista os termos
			List<String> terms = new ArrayList<>();

			while ( filters.indexOf( "," ) > 0 )
			{
				terms.add( filters.substring( 0, filters.indexOf( "," ) ) );
				filters = filters.substring( filters.indexOf( "," ) + 1, filters.length() );
			}
			// Se acabaram as vírgulas mas ainda existem termos 
			if ( filters.length() >0  )
			{
				terms.add(filters);
			}

			// Faz a busca
			Set<OrganizacaoMilitar> omsReturn = new HashSet<>();

			this.organizacoesMilitares.forEach( om -> {

				terms.forEach( term -> {

					if ( om.getNome().toLowerCase().contains( term.toLowerCase() ) || om.getSigla().toLowerCase().contains( term.toLowerCase() ) )
					{
						omsReturn.add( om );
					}

				} );

			} );

			return omsReturn;
		}
		else
		{
			return this.organizacoesMilitares;
		}

	}

}
