package br.edu.iftm.tspi.porm.sistema_jpa.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.edu.iftm.tspi.porm.sistema_jpa.dto.ErroDto;
import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErroDto> tratarNaoEncontrado(EntityNotFoundException e) {
        ErroDto erro = ErroDto.builder()
                                .message(e.getMessage())
                                .dataHora(LocalDateTime.now())
                                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

}
