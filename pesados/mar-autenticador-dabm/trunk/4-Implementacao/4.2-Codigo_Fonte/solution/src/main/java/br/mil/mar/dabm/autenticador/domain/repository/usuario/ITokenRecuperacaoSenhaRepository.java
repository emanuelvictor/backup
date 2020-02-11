package br.mil.mar.dabm.autenticador.domain.repository.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import br.mil.mar.dabm.common.domain.entity.usuario.TokenRecuperacaoSenha;

/**
 * 
 * @author emanuelvictor 
 * @version 1.0
 * @category Repository
 */
public interface ITokenRecuperacaoSenhaRepository extends JpaRepository<TokenRecuperacaoSenha, Long>
{

	/**
	 * @param token
	 */
	TokenRecuperacaoSenha findByToken( String token );

	/**
	 * 
	 * @return
	 */
	TokenRecuperacaoSenha findByUsuarioLogin(String login);

	
}
