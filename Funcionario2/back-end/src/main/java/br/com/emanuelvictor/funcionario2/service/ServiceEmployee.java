package br.com.emanuelvictor.funcionario2.service;

import br.com.emanuelvictor.funcionario2.entity.user.Employee;
import br.com.emanuelvictor.funcionario2.repository.DAOEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceEmployee implements UserDetailsService {

	@Autowired
	DAOEmployee daoEmployee;
	
	public Employee save(Employee employee) {

        if (employee.getPassword() == null) {
            //Handler pra atualizar demais informações do funcionário
			employee.setPassword(this.daoEmployee.findOne(employee.getEsmployeeId()).getPassword());
		} else {
            //Handler pra novo funcionário
            employee.setPassword(new ShaPasswordEncoder().encodePassword(employee.getPassword(), null));
        }
        return this.daoEmployee.save(employee);
    }
	
	public List<Employee> find() {
		return this.daoEmployee.findAll();
	}
	
	public Employee find(Integer id){
		return this.daoEmployee.findOne(id);
	}
	
	public void delete(Integer id){
		this.daoEmployee.delete(id);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		System.out.println("Procurando por usuario " + email);
		Employee employee = daoEmployee.findByEmail(email);
		if (employee == null) {
			System.out.println("Usuario" + email + " não encontrado");
			throw new UsernameNotFoundException(String.format("User %s does not exist!", email));
		}
		return employee;
	}

}
