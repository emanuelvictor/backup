package br.com.emanuelvictor.funcionario2.repository;

import br.com.emanuelvictor.funcionario2.entity.position.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface DAOPosition extends JpaRepository<Position, Integer> {

}
