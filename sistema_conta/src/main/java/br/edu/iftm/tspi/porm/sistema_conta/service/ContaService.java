package br.edu.iftm.tspi.porm.sistema_conta.service;

import org.springframework.stereotype.Service;

import br.edu.iftm.tspi.porm.sistema_conta.domain.Conta;
import br.edu.iftm.tspi.porm.sistema_conta.dto.TransferenciaDto;
import br.edu.iftm.tspi.porm.sistema_conta.repository.ContaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ContaService {
    
    private final ContaRepository repository;

    public ContaService(ContaRepository repository) {
        this.repository = repository;
    }

    public void transferencia(TransferenciaDto dto) {
        Conta contaOrigem = findById(dto.getOrigem());
        Conta contaDestino = findById(dto.getDestino());
        Double valor = dto.getValor();
        contaOrigem.saque(valor);        
        repository.save(contaOrigem);
        if (valor == 50) {
            throw new RuntimeException("ih rapaz");
        }
        contaDestino.deposita(valor);
        repository.save(contaDestino);
    }


    public Conta findById(Long id) {
        return repository.findById(id)
                         .orElseThrow(() -> new EntityNotFoundException("Conta não existe: " + id));
    }

}
