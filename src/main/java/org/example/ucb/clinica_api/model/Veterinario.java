package org.example.ucb.clinica_api.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo; // IMPORT ADICIONADO
import com.fasterxml.jackson.annotation.ObjectIdGenerators; // IMPORT ADICIONADO
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "veterinario")
// --- ANOTAÇÃO ADICIONADA ---
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "crmv"
)
// -----------------------------
public class Veterinario {

    @Id
    @Column(name = "CRMV")
    private String crmv;

    @Column(name = "nome")
    private String nome;

    @Column(name = "idade")
    private int idade;

    @Column(name = "data_graduacao")
    private LocalDate dataGraduacao;

    @OneToMany(mappedBy = "veterinario")
    // @JsonBackReference e @JsonManagedReference FORAM REMOVIDOS
    private List<Consulta> consultas;

    @OneToMany(mappedBy = "veterinario")
    // @JsonManagedReference FOI REMOVIDO
    private List<Certificacao> certificacoes;

    // Construtor vazio
    public Veterinario() {}

    // Construtor principal
    public Veterinario(String crmv, String nome, int idade, LocalDate dataGraduacao) {
        this.crmv = crmv;
        this.nome = nome;
        this.idade = idade;
        this.dataGraduacao = dataGraduacao;
    }

    // --- Getters e Setters ---
    public String getCrmv() { return crmv; }
    public void setCrmv(String crmv) { this.crmv = crmv; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
    public LocalDate getDataGraduacao() { return dataGraduacao; }
    public void setDataGraduacao(LocalDate dataGraduacao) { this.dataGraduacao = dataGraduacao; }
    public List<Consulta> getConsultas() { return consultas; }
    public void setConsultas(List<Consulta> consultas) { this.consultas = consultas; }
    public List<Certificacao> getCertificacoes() { return certificacoes; }
    public void setCertificacoes(List<Certificacao> certificacoes) { this.certificacoes = certificacoes; }
}