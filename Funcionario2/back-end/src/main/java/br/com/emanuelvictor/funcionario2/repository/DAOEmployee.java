package br.com.emanuelvictor.funcionario2.repository;

import br.com.emanuelvictor.funcionario2.entity.user.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface DAOEmployee extends JpaRepository<Employee, Integer> {

	Employee findByEmail(String email);
//	@Transactional(readOnly = true)
//	public Employee findByEmailIgnoreCase(String email);
}
