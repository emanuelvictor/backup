/**
 * 
 */
package br.mil.mar.dabm.autenticador.domain.repository.aplicativo;

import org.springframework.data.jpa.repository.JpaRepository;

import br.mil.mar.dabm.autenticador.domain.entity.aplicativo.DependenciaPerfilAcesso;

/**
 * @author emanuelvictor
 *
 */
public interface IDependenciaPerfilAcesso extends JpaRepository<DependenciaPerfilAcesso, Long>
{

}
