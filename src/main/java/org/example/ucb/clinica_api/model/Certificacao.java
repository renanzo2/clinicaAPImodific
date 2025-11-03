package org.example.ucb.clinica_api.model;

import jakarta.persistence.*;  // ou javax.persistence.*
import java.time.LocalDate;

@Entity
public class Certificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  // ← ADICIONA ISSO!

    private String numeroRegistro;
    private LocalDate dataObtencao;
    private String instituicaoCertificadora;

    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;

    @ManyToOne
    @JoinColumn(name = "especialidade_id")
    private Especialidade especialidade;

    public Certificacao(){
    }

    public Certificacao(String numeroRegistro, LocalDate dataObtencao, String instituicaoCertificadora, Veterinario veterinario) {
        this.numeroRegistro = numeroRegistro;
        this.dataObtencao = dataObtencao;
        this.instituicaoCertificadora = instituicaoCertificadora;
        this.veterinario = veterinario;
    }

    // ← ADICIONA OS GETTERS E SETTERS DO ID!
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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