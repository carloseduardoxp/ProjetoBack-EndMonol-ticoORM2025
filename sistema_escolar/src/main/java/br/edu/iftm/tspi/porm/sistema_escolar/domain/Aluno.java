package br.edu.iftm.tspi.porm.sistema_escolar.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TB_ALUNO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="COD_ALUNO")
    private Integer id;

    @Column(name="NOM_ALUNO",nullable=false)
    private String nome;

    @Column(name="DES_EMAIL")
    private String email;

    @ManyToMany
    @JoinTable(name="TB_ALUNOS_CURSANDO",
               joinColumns = { @JoinColumn(name = "COD_ALUNO") }, 
        inverseJoinColumns = { @JoinColumn(name = "COD_DISCIPLINA") })
    private List<Disciplina> disciplinasEmCurso;
}
