package br.edu.iftm.tspi.backorm.sistema_contatos.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErroDTO {

    private String mensagem;

    private LocalDateTime data;

}
