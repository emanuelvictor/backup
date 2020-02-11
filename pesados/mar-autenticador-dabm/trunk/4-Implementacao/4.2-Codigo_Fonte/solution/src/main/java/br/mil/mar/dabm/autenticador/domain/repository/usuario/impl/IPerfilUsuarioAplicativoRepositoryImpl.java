package br.mil.mar.dabm.autenticador.domain.repository.usuario.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import br.mil.mar.dabm.common.domain.entity.aplicativo.PerfilAcesso;
import br.mil.mar.dabm.common.domain.entity.usuario.PerfilUsuarioAplicativo;

/**
 * 
 * @author emanuelvictor
 * @version 1.0
 * @category Repository
 */
public class IPerfilUsuarioAplicativoRepositoryImpl
{
	/**
	 * 
	 */
	@Autowired
	private EntityManager entityManager;
	
	/**
	 * 
	 * @param usuarioId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PerfilUsuarioAplicativo> findRevisionsByUsuarioId(Long usuarioId) {

		List<Object[]> perfisUsuarioAplicativosRevisions = entityManager.createNativeQuery( "SELECT * FROM auditoria.perfil_usuario_aplicativo_auditado WHERE (auditoria.perfil_usuario_aplicativo_auditado.usuario_id = ?1)" ).setParameter( 1, usuarioId ).getResultList();

		List<PerfilUsuarioAplicativo> perfisUsuarioAplicativos = new ArrayList<PerfilUsuarioAplicativo>();
		for ( Object[] perfilUsuarioAplicativoAuditado : perfisUsuarioAplicativosRevisions )
		{
			PerfilUsuarioAplicativo perfilUsuarioAplicativo = new PerfilUsuarioAplicativo();
			perfilUsuarioAplicativo.setPerfilAcesso(this.entityManager.find(PerfilAcesso.class, Long.parseLong( perfilUsuarioAplicativoAuditado[3].toString() )) );
			perfisUsuarioAplicativos.add( perfilUsuarioAplicativo );			
		}
		
		return perfisUsuarioAplicativos; 
	}
	
	
}