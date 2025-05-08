package br.edu.iftm.tspi.porm.sistema_jpa.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.tspi.porm.sistema_jpa.domain.Categoria;
import br.edu.iftm.tspi.porm.sistema_jpa.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaRepository repository;

    public CategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listar(@RequestParam(required = false) String nome) {
        List<Categoria> categorias;
        if (nome == null) {
            categorias = repository.findAll();
        } else {
            categorias = repository.findByNomeContainingIgnoreCase(nome);
        }
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Integer id) {
        Categoria categoria = repository.findById(id)
                                .orElseThrow(
                                     () -> 
                                     new EntityNotFoundException("Categoria com id "+ id + " não encontrado"));
        return ResponseEntity.ok(categoria);
    }
    

}
