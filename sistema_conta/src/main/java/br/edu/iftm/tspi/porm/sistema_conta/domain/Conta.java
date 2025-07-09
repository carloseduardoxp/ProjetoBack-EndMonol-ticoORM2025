package br.edu.iftm.tspi.porm.sistema_conta.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TB_CONTA")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="COD_CONTA")
    private Long id;

    @Column(name="NOM_CLIENTE")
    private String nomeCliente;

    @Column(name="VLR_SALDO")
    private Double saldo;

    public void deposita(Double valor) {
        this.saldo += valor;
    }

    public void saque(Double valor) {
        this.saldo -= valor;
    }

}
 