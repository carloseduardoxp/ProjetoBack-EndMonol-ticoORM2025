package br.edu.iftm.tspi.porm.sistema_escolar.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.tspi.porm.sistema_escolar.domain.Aluno;
import br.edu.iftm.tspi.porm.sistema_escolar.repository.AlunoRepository;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private AlunoRepository repository;

    public AlunoController(AlunoRepository repository) {
        this.repository = repository;
    } 

    @GetMapping
    public List<Aluno> findAll() {
        return repository.findAll();
    }

    @PostMapping
    public Aluno insert(@RequestBody Aluno aluno) {
        return repository.save(aluno);
    }   

}
