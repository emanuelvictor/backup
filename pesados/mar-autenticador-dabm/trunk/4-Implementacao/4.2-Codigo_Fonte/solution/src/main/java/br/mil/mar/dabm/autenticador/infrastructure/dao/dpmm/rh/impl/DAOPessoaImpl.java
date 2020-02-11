/**
 * 
 */
package br.mil.mar.dabm.autenticador.infrastructure.dao.dpmm.rh.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import br.mil.mar.dabm.autenticador.infrastructure.dao.Memory;
import br.mil.mar.dabm.autenticador.infrastructure.dao.dpmm.rh.DAOPessoa;
import br.mil.mar.dpmm.rh.domain.entity.Pessoa;

/**
 * @author emanuelvictor
 *
 */
@Repository
public class DAOPessoaImpl implements DAOPessoa
{
	/**
	 * 
	 */
	Set<Pessoa> pessoas = new HashSet<Pessoa>( Arrays.asList( new Memory().PESSOAS ) );

	/*
	 * (non-Javadoc)
	 * @see br.mil.mar.dabm.autenticador.infrastructure.dao.dpmm.rh.DAOPessoa#findById(java.lang.Long)
	 */
	@Override
	public Pessoa findById( Long id )
	{
		if ( id != null )
		{
			for ( Pessoa pessoa : pessoas )
			{
				if ( pessoa.getId() == id || pessoa.getId().equals( id ) )
				{
					return pessoa;
				}
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see br.mil.mar.dabm.autenticador.infrastructure.dao.dpmm.rh.DAOPessoa#findByNIP(java.lang.String)
	 */
	@Override
	public Pessoa findByNIP( String nip )
	{
		if ( nip != null )
		{
			for ( Pessoa pessoa : pessoas )
			{
				if ( pessoa.getNip().toLowerCase() == nip || pessoa.getNip().toLowerCase().equals( nip.toLowerCase() ) )
				{
					return pessoa;
				}
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see br.mil.mar.dabm.autenticador.infrastructure.dao.dpmm.rh.DAOPessoa#listPessoaByFilters(java.lang.String, org.springframework.data.domain.PageRequest)
	 */
	@Override
	public Set<Pessoa> listPessoaByFilters( String filters )
	{
		if ( filters != null )
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
			Set<Pessoa> pessoasReturn = new HashSet<>();

			this.pessoas.forEach( pessoa -> {

				terms.forEach( term -> {

					if (( pessoa.getTelefone() != null && pessoa.getTelefone().toLowerCase().contains( term.toLowerCase() ) || (pessoa.getEmail() != null && pessoa.getEmail().toLowerCase().contains( term.toLowerCase() )) ||(pessoa.getPostoGraduacao() != null &&  pessoa.getPostoGraduacao().toLowerCase().contains( term.toLowerCase() ))) )
					{
						pessoasReturn.add( pessoa );
					}

				} );

			} );

			return pessoasReturn;
		}
		else
		{
			return this.pessoas;
		}

	}

}
