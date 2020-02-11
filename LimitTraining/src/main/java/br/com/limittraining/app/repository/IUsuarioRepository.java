package br.com.limittraining.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.limittraining.app.entity.user.Usuario;


@Transactional
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

	@Transactional(readOnly = true)
	public Usuario findByUsernameIgnoreCase(String username);
//
//	@Transactional(readOnly = true)
//	@Query("select e from Empresa e where e.nome like %?1%")
//	public List<Empresa> getByEmpresa(String nome);
//
//	public List<Empresa> findByNomeContainingAndCNPJContaining(String nome, String CNPJ);
//	
//	@Transactional(readOnly = true)
//	@Query("select e from Empresa e where e.nome LIKE %:nome% and (e.CNPJ LIKE %:CNPJ% or e.CNPJ is null)")
//	public List<Empresa> getByEmpresa(@Param("nome") String nome, @Param("CNPJ") String CNPJ);



}