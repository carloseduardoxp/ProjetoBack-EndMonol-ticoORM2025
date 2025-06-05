package br.edu.iftm.tspi.porm.sistema_escolar.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TB_DISCIPLINA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Disciplina {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="COD_DISCIPLINA")
    private Integer id;

    @Column(name="NOM_DISCIPLINA",nullable=false)
    private String nome;

    @Column(name="NUM_HORAS",nullable=false)
    private Integer cargaHoraria;

    @Column(name="DES_EMENTA")
    private String ementa;

    @JsonBackReference
    @ManyToMany(mappedBy="disciplinasEmCurso")
    private List<Aluno> alunosMatriculados;

}
