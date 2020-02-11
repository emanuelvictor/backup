package br.com.emanuelvictor.funcionario.repository;

import br.com.emanuelvictor.funcionario.entity.public_schema.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface DAOCargo extends JpaRepository<Cargo, Long> {

//	@Transactional(readOnly = true)
//	@Query("select c from Cargo c WHERE (c.nome LIKE %:nome% or :nome is null)")
//	public List<Cargo> find(@Param("nome") String nome);

    @Transactional(readOnly = true)
    List<Cargo> findByNomeIgnoreCaseContaining(String nome);
	
}