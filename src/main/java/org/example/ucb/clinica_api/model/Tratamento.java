package org.example.ucb.clinica_api.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name = "tratamento")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Tratamento {

    @Id
    @Column(name = "id") // Coluna 'id' agora é VARCHAR(36)
    private String id; // MUDADO DE Integer

    // @GeneratedValue FOI REMOVIDO

    @Column(name = "descricao_tratamento")
    private String descricao;

    private boolean antibiotico;

    @ManyToOne
    @JoinColumn(name = "id_consulta") // Esta FK agora aponta para a PK VARCHAR(36) de Consulta
    private Consulta consulta;

    public Tratamento() {}

    // --- Getters e Setters ATUALIZADOS ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public boolean isAntibiotico() { return antibiotico; }
    public void setAntibiotico(boolean antibiotico) { this.antibiotico = antibiotico; }
    public Consulta getConsulta() { return consulta; }
    public void setConsulta(Consulta consulta) { this.consulta = consulta; }
}