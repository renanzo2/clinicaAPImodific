package org.example.ucb.clinica_api.model;

import jakarta.persistence.*;  // ou javax.persistence.*
import java.time.LocalDate;

@Entity
@Table(name = "certificacao")
public class Certificacao {

    @Id // 1. O @Id (Chave Primária) é o NumeroRegistro
    @Column(name = "NumeroRegistro") // 2. "DE-PARA": O campo Java 'numeroRegistro' é a coluna 'NumeroRegistro' no SQL
    private String numeroRegistro;

    @Column(name = "DataObtencao")
    private LocalDate dataObtencao;

    @Column(name = "InstituicaoCertificadora")
    private String instituicaoCertificadora;

    @ManyToOne
    @JoinColumn(name = "CRMV_certif") // 3. "DE-PARA": O nome da sua coluna FK para veterinario
    private Veterinario veterinario;

    @ManyToOne
    @JoinColumn(name = "ID_especialidade") // 4. "DE-PARA": O nome da sua coluna FK para especialidade
    private Especialidade especialidade;

    // 5. OBRIGATÓRIO: Construtor vazio
    public Certificacao() {}

    public Certificacao(String numeroRegistro, LocalDate dataObtencao, String instituicaoCertificadora, Veterinario veterinario) {
        this.numeroRegistro = numeroRegistro;
        this.dataObtencao = dataObtencao;
        this.instituicaoCertificadora = instituicaoCertificadora;
        this.veterinario = veterinario;
    }

    // ← ADICIONA OS GETTERS E SETTERS DO ID!

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public LocalDate getDataObtencao() {
        return dataObtencao;
    }

    public void setDataObtencao(LocalDate dataObtencao) {
        this.dataObtencao = dataObtencao;
    }

    public String getInstituicaoCertificadora() {
        return instituicaoCertificadora;
    }

    public void setInstituicaoCertificadora(String instituicaoCertificadora) {
        this.instituicaoCertificadora = instituicaoCertificadora;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }
}