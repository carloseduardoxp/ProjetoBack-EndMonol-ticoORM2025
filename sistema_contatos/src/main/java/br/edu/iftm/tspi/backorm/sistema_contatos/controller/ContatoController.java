package br.edu.iftm.tspi.backorm.sistema_contatos.controller;

import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.tspi.backorm.sistema_contatos.domain.Contato;
import br.edu.iftm.tspi.backorm.sistema_contatos.dto.ErroDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/contatos")
public class ContatoController {

    private List<Contato> contatos = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Contato>> buscar
                      (@RequestParam(required = false) String nome) {
        if (nome == null) {
            return ResponseEntity.ok(contatos);
        }
        List<Contato> retorno = contatos.stream()
            .filter(contato -> contato.getNome().toLowerCase().contains(nome.toLowerCase()))
            .toList();
        return ResponseEntity.ok(retorno);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        for (Contato contato: contatos) {
            if (contato.getCodigo().equals(id)) {
                return ResponseEntity.ok().body(contato);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ErroDTO("Contato não encontrado: "+id, LocalDateTime.now()));
    }

    @PostMapping()
    public ResponseEntity<?> salvar(@RequestBody Contato novoContato) {
        if (novoContato.getNome() == null || 
            novoContato.getNome().equals("")) {
            return ResponseEntity.badRequest().body(
                new ErroDTO("Não recebi o nome",LocalDateTime.now()));
        }
        boolean existe = contatos.stream().anyMatch(
            contato -> contato.getCodigo().equals(novoContato.getCodigo()));

        if (existe) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ErroDTO("Código já existe "+novoContato.getCodigo(),
                      LocalDateTime.now()));
        }
        contatos.add(novoContato);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoContato);       
    }

    @PutMapping("{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, 
                                        @RequestBody Contato contatoAtualizado) {
        for (Contato contato: contatos) {
            if (contato.getCodigo().equals(contatoAtualizado.getCodigo())) {
                contato.setNome(contatoAtualizado.getNome());
                return ResponseEntity.ok(contato);                                    
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDTO(
                            "Contato "+id+" não encontrado", 
                            LocalDateTime.now()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        boolean removido = contatos.removeIf(
            contato -> contato.getCodigo().equals(id));
        if (removido) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ErroDTO("Contato "+id+" não encontrado ", LocalDateTime.now()));
    }
    
}
