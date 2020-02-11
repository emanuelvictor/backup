package br.com.emanuelvictor.funcionario2.service;

import br.com.emanuelvictor.funcionario2.entity.position.Position;
import br.com.emanuelvictor.funcionario2.repository.DAOPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicePosition {

	@Autowired
	DAOPosition daoPosition;

	public Position save(Position position) {
		// TODO Auto-generated method stub
		return this.daoPosition.save(position);
	}

	/**
	 * @param id
	 * @return
	 */
	public Position find(Integer id) {
		// TODO Auto-generated method stub
		return this.daoPosition.findOne(id);
	}

	/**
	 * @param id
	 */
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.daoPosition.delete(id);
	}

	/**
	 * @return
	 */
	public List<Position> find() {
		// TODO Auto-generated method stub
		return this.daoPosition.findAll();
	}

}
