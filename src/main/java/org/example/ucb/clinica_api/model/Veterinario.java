package org.example.ucb.clinica_api.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "veterinario")
public class Veterinario {

    @Id
    private String crmv;

    private String nome;
    private int idade;
    private LocalDate dataGraduacao; // <- O nome no Java (dataGraduacao)
    //    pode ser diferente do banco (data_graduacao)
    //    O Spring/JPA converte automaticamente.

    // --- RELACIONAMENTOS (Onde a especialidade "mora") ---
    @OneToMany(mappedBy = "veterinario")
    private List<Consulta> consultas;

    @OneToMany(mappedBy = "veterinario")
    private List<Certificacao> certificacoes; // <-- A lista de especialidades vem daqui

    // Construtor vazio (Obrigatório)
    public Veterinario() {}

    // Construtor principal (para o DAO)
    public Veterinario(String crmv, String nome, int idade, LocalDate dataGraduacao) {
        this.crmv = crmv;
        this.nome = nome;
        this.idade = idade;
        this.dataGraduacao = dataGraduacao;
    }

    // --- Getters e Setters ---
    // (Apenas para os campos que existem na tabela)

    public String getCrmv() { return crmv; }
    public void setCrmv(String crmv) { this.crmv = crmv; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
    public LocalDate getDataGraduacao() { return dataGraduacao; }
    public void setDataGraduacao(LocalDate dataGraduacao) { this.dataGraduacao = dataGraduacao; }

    // Getters/Setters para os relacionamentos
    public List<Consulta> getConsultas() { return consultas; }
    public void setConsultas(List<Consulta> consultas) { this.consultas = consultas; }
    public List<Certificacao> getCertificacoes() { return certificacoes; }
    public void setCertificacoes(List<Certificacao> certificacoes) { this.certificacoes = certificacoes; }
}