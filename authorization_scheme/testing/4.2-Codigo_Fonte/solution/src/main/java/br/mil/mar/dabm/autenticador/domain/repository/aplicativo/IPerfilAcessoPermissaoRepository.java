package br.mil.mar.dabm.autenticador.domain.repository.aplicativo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.mil.mar.dabm.autenticador.domain.entity.aplicativo.PerfilAcessoPermissao;
import br.mil.mar.dabm.autenticador.domain.entity.aplicativo.Permissao;

/**
 * 
 * @author rodrigo@eits.com.br 
 * @since 22/04/2014
 * @version 1.0
 */
public interface IPerfilAcessoPermissaoRepository extends JpaRepository<PerfilAcessoPermissao, Long>
{

	/**
	 * @param perfilAcessoId
	 * @return
	 */
	List<Permissao> findAllPerfilAcessoPermissoesByPerfilAcessoId( Long perfilAcessoId );
}
