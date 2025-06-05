package br.edu.iftm.tspi.porm.sistema_escolar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.iftm.tspi.porm.sistema_escolar.domain.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

}
