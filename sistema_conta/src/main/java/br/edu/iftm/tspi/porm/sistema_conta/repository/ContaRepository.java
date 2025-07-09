package br.edu.iftm.tspi.porm.sistema_conta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.porm.sistema_conta.domain.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta,Long> {

}
