package org.example.ucb.clinica_api.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo; // IMPORT ADICIONADO
import com.fasterxml.jackson.annotation.ObjectIdGenerators; // IMPORT ADICIONADO
import jakarta.persistence.*;

@Entity
@Table(name = "tratamento")
// --- ANOTAÇÃO ADICIONADA ---
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
// -----------------------------
public class Tratamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // CORREÇÃO: Mapeia 'descricao' para 'descricao_tratamento' no SQL
    @Column(name = "descricao_tratamento")
    private String descricao;

    private boolean antibiotico;

    // --- Relacionamentos ---

    @ManyToOne
    @JoinColumn(name = "id_consulta")
    // @JsonBackReference FOI REMOVIDO
    private Consulta consulta;

    // OBRIGATÓRIO: Construtor vazio
    public Tratamento() {}

    // --- Getters e Setters ---
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public boolean isAntibiotico() {
        return antibiotico;
    }
    public void setAntibiotico(boolean antibiotico) {
        this.antibiotico = antibiotico;
    }
    public Consulta getConsulta() {
        return consulta;
    }
    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
}