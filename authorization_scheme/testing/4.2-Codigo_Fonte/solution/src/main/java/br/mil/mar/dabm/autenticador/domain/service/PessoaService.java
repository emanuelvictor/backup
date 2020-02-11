/**
 * 
 */
package br.mil.mar.dabm.autenticador.domain.service;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mil.mar.dabm.autenticador.domain.entity.usuario.Usuario;

/**
 * @author Boz
 *
 */

@Service
@Transactional
@RemoteProxy(name = "pessoaService")
public class PessoaService
{
	/**
	 * 
	 * @param cpf
	 * @return
	 */
	public Usuario findUsuarioByPessoaCPF( String cpf )
	{
		return null;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Usuario findUsuarioByPessoaId( Long id )
	{
		return null;
	}

	/**
	 * 
	 * @param nip
	 * @return
	 */
	public Usuario findUsuarioByPessoaNIP( String nip )
	{
		return null;
	}

	/**
	 * 
	 * @param filters
	 * @param pageable
	 * @return
	 */
	public Page<Usuario> listUsuarioByFilters( String filters, PageRequest pageable )
	{
		return null;
	}

}
