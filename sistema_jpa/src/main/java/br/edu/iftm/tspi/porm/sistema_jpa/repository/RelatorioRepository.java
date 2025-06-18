package br.edu.iftm.tspi.porm.sistema_jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.porm.sistema_jpa.domain.Produto;
import br.edu.iftm.tspi.porm.sistema_jpa.dto.RelatorioProdutoClienteDto;

@Repository
public interface RelatorioRepository extends JpaRepository<Produto, Long> {
    
    @Query(nativeQuery = true,value= """
            select c.nome as nomeCliente,
                   pr.produtoNome as nomeProduto,
                   sum((dp.precoVenda * quantidade) - desconto) as valor
            from pedidos p, detalhes_pedido dp, clientes c,produtos pr
            where p.pedidoId = dp.pedidoId
              and p.clienteId = c.clienteId
              and dp.produtoId = pr.produtoId
              and c.clienteID = :clienteID
            group by c.nome,pr.produtoNome,p.pedidoId
          """)
    List<RelatorioProdutoClienteDto> getProdutosPorCliente(@Param("clienteID") String clienteID);
}
