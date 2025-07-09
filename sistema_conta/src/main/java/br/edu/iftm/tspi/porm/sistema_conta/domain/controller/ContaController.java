package br.edu.iftm.tspi.porm.sistema_conta.domain.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.tspi.porm.sistema_conta.dto.TransferenciaDto;
import br.edu.iftm.tspi.porm.sistema_conta.service.ContaService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/conta")
public class ContaController {

    private final ContaService service;

    public ContaController(ContaService service) {
        this.service = service;
    }

    @PostMapping("/transferencia")
    public ResponseEntity<?> transferir(@Valid @RequestBody TransferenciaDto dto) {
        service.transferencia(dto);
        return ResponseEntity.ok("Transferência realizada com sucesso.");
    }

}
