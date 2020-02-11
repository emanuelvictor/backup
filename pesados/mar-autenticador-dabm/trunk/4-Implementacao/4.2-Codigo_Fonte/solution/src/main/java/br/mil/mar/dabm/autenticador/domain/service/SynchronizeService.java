/**
 * 
 */
package br.mil.mar.dabm.autenticador.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.util.Assert;

import br.mil.mar.dabm.autenticador.domain.repository.IUsuarioMailRepository;
import br.mil.mar.dabm.autenticador.domain.repository.usuario.IConfiguracaoRepository;
import br.mil.mar.dabm.autenticador.domain.repository.usuario.ITokenRecuperacaoSenhaRepository;
import br.mil.mar.dabm.autenticador.domain.repository.usuario.IUsuarioRepository;
import br.mil.mar.dabm.autenticador.infrastructure.dao.dpmm.rh.DAOPessoa;
import br.mil.mar.dabm.common.domain.entity.usuario.Usuario;

/**
 * 
 * @author emanuelvictor
 *
 */
public abstract class SynchronizeService
{
	/**
	 * 
	 */
	@Autowired
	private DAOPessoa daoPessoa;

	/**
	 * 
	 */
	@Autowired
	protected IUsuarioRepository usuarioRepository;

	/**
	 * Password encoder
	 */
	@Autowired
	protected ITokenRecuperacaoSenhaRepository iTokenRecuperacaoSenhaRepository;

	/**
	 * Password encoder
	 */
	@Autowired
	protected ShaPasswordEncoder passwordEncoder;

	/**
	 * Hash generator for encryption
	 */
	@Autowired
	protected SaltSource saltSource;

	/**
	 * repositório para data default de expiração de senha
	 */
	@Autowired
	protected IConfiguracaoRepository configuracaoRepository;

	// Repositories
	/**
	 * repositório utilizado para enviar emails
	 */
	@Autowired
	protected IUsuarioMailRepository usuarioMailRepository;

	/**
	 * Faz a sincronização entre usuário e pessoa, se houver pessoa com esse login o usuário será sincronizado.
	 * Estoura exceção caso o usuário não tenha email para contato.
	 * 
	 * @param login
	 * @return
	 */
	protected Usuario synchronizePessoa( Usuario usuario )
	{
		usuario.setPessoa( daoPessoa.findByNIP( usuario.getLogin() ) );

		Assert.isTrue( !( ( usuario.getPessoa() == null ) && ( usuario.getNomeCompleto() == null ) ), "O usuário deve ter um nome" );
		Assert.isTrue( !( ( usuario.getPessoa() == null ) && ( usuario.getEmail() == null ) ), "O usuário deve ter um email" );
		return usuario;
	}

	/**
	 * Faz a sincronização entre usuário e pessoa, se houver pessoa com esse login o usuário será sincronizado.
	 * 
	 * @param usuarios
	 * @param filters
	 * @return
	 */
	protected List<Usuario> synchronizePessoa( List<Usuario> usuarios )
	{
		for ( Usuario usuario : usuarios )
		{
			this.synchronizePessoa( usuario );
		}
		return usuarios;
	}

	/**
	 * Faz a sincronização entre usuário e pessoa, se houver pessoa com esse login o usuário será sincronizado.
	 * 
	 * @param usuarios
	 * @param filters
	 * @return
	 */
	protected Page<Usuario> synchronizePessoa( Page<Usuario> usuarios, String filters )
	{
		for ( Usuario usuario : usuarios.getContent() )
		{
			this.synchronizePessoa( usuario );
		}
		return usuarios;
	}
}
