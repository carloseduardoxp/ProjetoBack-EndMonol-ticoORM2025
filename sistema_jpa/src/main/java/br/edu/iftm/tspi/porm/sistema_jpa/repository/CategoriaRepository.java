package br.edu.iftm.tspi.porm.sistema_jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.porm.sistema_jpa.domain.Categoria;
import br.edu.iftm.tspi.porm.sistema_jpa.dto.CategoriaQuantidadeDTO;

@Repository
public interface CategoriaRepository extends 
                            JpaRepository<Categoria, Integer>{

    public List<Categoria> findByNomeContainingIgnoreCase(String nome);

    @Query("""
        SELECT new br.edu.iftm.tspi.porm.sistema_jpa.dto.CategoriaQuantidadeDTO(
            c.id, 
            c.nome, 
            COUNT(p)
        )
        FROM Produto p
        JOIN p.categoria c
        GROUP BY c.id, c.nome
        """)
    List<CategoriaQuantidadeDTO> contarProdutosPorCategoria();

}
