package br.com.limittraining.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.limittraining.app.entity.core.Aluno;

@Transactional
public interface IAlunoRepository extends JpaRepository<Aluno, Long> {

}