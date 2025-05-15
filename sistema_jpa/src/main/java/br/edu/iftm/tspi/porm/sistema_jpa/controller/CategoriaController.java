package br.edu.iftm.tspi.porm.sistema_jpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.tspi.porm.sistema_jpa.domain.Categoria;
import br.edu.iftm.tspi.porm.sistema_jpa.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;


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

    @PostMapping
    public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria) {
        Categoria categoriaSalva = repository.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Integer id,
                                        @Valid @RequestBody Categoria categoria) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Categoria com id "+id+" não encontrada");
        }
        categoria.setId(id);
        Categoria categoriaAtualizada = repository.save(categoria);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirDoNovoTestamento(@PathVariable Integer id) {
        return repository.findById(id)
                         .map(categoria -> {
                            repository.delete(categoria);
                            return ResponseEntity.noContent().build();
                         })
                         .orElseThrow(
                            () -> new EntityNotFoundException(
                                        "Categoria com id "+id+" não encontrada"));
    }

    @DeleteMapping("/velhoTestamento/{id}")
    public ResponseEntity<?> excluirDoVelhoTestamento(@PathVariable Integer id) {
        Optional<Categoria> categoria = repository.findById(id);
        if (!categoria.isPresent()) {
            throw new EntityNotFoundException("Categoria com id "+id+" não encontrada");
        }
        Categoria categoriaBanco = categoria.get();
        repository.delete(categoriaBanco);
        return ResponseEntity.noContent().build();
    }

}
