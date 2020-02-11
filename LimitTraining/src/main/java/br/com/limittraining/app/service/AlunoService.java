package br.com.limittraining.app.service;

import java.util.List;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.limittraining.app.entity.core.Aluno;
import br.com.limittraining.app.repository.IAlunoRepository;

@Service
@Transactional
@RemoteProxy(name = "alunoService")
public class AlunoService {

	@Autowired
	IAlunoRepository alunoRepository;

	public Aluno save(Aluno aluno) {
		return this.alunoRepository.save(aluno);
	}

	public void destroy(Long id) {
		alunoRepository.delete(id);
	}

	public List<Aluno> find() {
		return alunoRepository.findAll();
	}

	public Aluno find(Long id) {
		return alunoRepository.findOne(id);
	}

}