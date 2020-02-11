package br.com.emanuelvictor.funcionario.repository;

import br.com.emanuelvictor.funcionario.entity.tenant_schema.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface DAOEmpresa extends JpaRepository<Empresa, Long> {

	@Transactional(readOnly = true)
	@Query("select e from Empresa e WHERE (e.CNPJ LIKE %:CNPJ% OR :CNPJ IS NULL)")
	List<Empresa> find(@Param("CNPJ") String CNPJ);

//    @Transactional(readOnly = true)
//    List<Empresa> findByCnpjIgnoreCaseContaining(String CNPJ);
}