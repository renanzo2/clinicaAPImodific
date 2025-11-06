package org.example.ucb.clinica_api.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo; // IMPORT ADICIONADO
import com.fasterxml.jackson.annotation.ObjectIdGenerators; // IMPORT ADICIONADO
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Especialidade") // CORREÇÃO: "especialidade" -> "Especialidade"
// --- ANOTAÇÃO ADICIONADA ---
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
// -----------------------------
public class Especialidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDespecialidade")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @OneToMany(mappedBy = "especialidade")
    // @JsonManagedReference FOI REMOVIDO
    private List<Certificacao> certificacoes;

    // OBRIGATÓRIO: Construtor vazio
    public Especialidade() {}

    public Especialidade(int id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public int  getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    // Getter/Setter para certificacoes estava faltando no original
    public List<Certificacao> getCertificacoes() { return certificacoes; }
    public void setCertificacoes(List<Certificacao> certificacoes) { this.certificacoes = certificacoes; }
}