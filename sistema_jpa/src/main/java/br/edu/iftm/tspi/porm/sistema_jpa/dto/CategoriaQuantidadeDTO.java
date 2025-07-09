package br.edu.iftm.tspi.porm.sistema_jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaQuantidadeDTO {

    private Integer idCategoria;
    private String nomeCategoria;
    private Long quantosProdutos;
    
}
