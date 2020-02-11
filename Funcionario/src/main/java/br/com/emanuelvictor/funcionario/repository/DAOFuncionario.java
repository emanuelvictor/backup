package br.com.emanuelvictor.funcionario.repository;

import br.com.emanuelvictor.funcionario.entity.public_schema.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DAOFuncionario extends JpaRepository<Funcionario, Long> {

	@Transactional(readOnly = true)
	public Funcionario findByEmailIgnoreCase(String email);
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