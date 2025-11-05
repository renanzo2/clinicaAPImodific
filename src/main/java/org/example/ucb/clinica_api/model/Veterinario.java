package org.example.ucb.clinica_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "veterinario") // 1. Diz que o nome da tabela no SQL é "veterinario" (com 'v' minúsculo)
public class Veterinario {

    @Id
    @Column(name = "CRMV") // 2. "DE-PARA": O campo 'crmv' no Java é a coluna 'CRMV' (maiúsculo) no SQL
    private String crmv;

    @Column(name = "nome") // 3. "DE-PARA" (Boa prática, mesmo sendo igual)
    private String nome;

    @Column(name = "idade") // 4. "DE-PARA"
    private int idade;

    @Column(name = "data_graduacao") // 5. "DE-PARA" (O mais importante!)
    private LocalDate dataGraduacao; //    Campo 'dataGraduacao' no Java -> Coluna 'data_graduacao' no SQL

    // --- RELACIONAMENTOS ---
    // (O 'mappedBy' aponta para o nome do *campo Java* na outra classe)

    @OneToMany(mappedBy = "veterinario")
    @JsonBackReference
    private List<Consulta> consultas;

    @OneToMany(mappedBy = "veterinario")
    private List<Certificacao> certificacoes;

    // Construtor vazio (Obrigatório)
    public Veterinario() {}

    // Construtor principal (Obrigatório para o seu DAO)
    public Veterinario(String crmv, String nome, int idade, LocalDate dataGraduacao) {
        this.crmv = crmv;
        this.nome = nome;
        this.idade = idade;
        this.dataGraduacao = dataGraduacao;
    }

    // --- Getters e Setters ---
    // (Não precisa mexer aqui)

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