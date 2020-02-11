/**
 * 
 */
package br.mil.mar.dabm.autenticador.domain.repository.aplicativo;

import org.springframework.data.jpa.repository.JpaRepository;

import br.mil.mar.dabm.autenticador.domain.entity.aplicativo.DependenciaPermissao;

/**
 * @author emanuelvictor
 *
 */
public interface IDependenciaPermissaoRepository extends JpaRepository<DependenciaPermissao, Long>
{

}
