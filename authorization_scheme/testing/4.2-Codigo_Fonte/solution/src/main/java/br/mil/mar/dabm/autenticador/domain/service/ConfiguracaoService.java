/**
 * 
 */
package br.mil.mar.dabm.autenticador.domain.service;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mil.mar.dabm.autenticador.domain.entity.usuario.Configuracao;
import br.mil.mar.dabm.autenticador.domain.repository.usuario.IConfiguracaoRepository;

/**
 * @author emanuelvictor
 *
 */
@Service
@Transactional
@RemoteProxy(name = "configuracaoService")
public class ConfiguracaoService
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	@Autowired
	private IConfiguracaoRepository configuracaoRepository;

	/*-------------------------------------------------------------------
	 *				 		     SERVICES
	 *-------------------------------------------------------------------*/

	/**
	 * TODO verificar se o postgres tem alguma forma de salvar configurações em algum tipo de constant
	 * 
	 * @param diasDeExpiracao
	 * @return
	 */
	// TESTADO
	public Configuracao updateDiasExpiracaoSenha( Configuracao configuracao)
	{
		// Setando os novos dias na entidade recém criada
		configuracao.setDiasExpiracaoSenha( configuracao.getDiasExpiracaoSenha() );

		// Salvando a entidade recém criada
		return this.configuracaoRepository.save( configuracao );
	}

	/**
	 * 
	 * @return
	 */
	public Configuracao getDiasExpiracaoSenha()
	{
		return this.configuracaoRepository.findOne( 1L );
	}
}
