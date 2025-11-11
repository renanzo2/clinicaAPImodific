package org.example.ucb.clinica_api.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.*;
// Removidos imports de LocalTime e LocalDate
import java.util.List;

@Entity
@Table(name = "consulta")
public class Consulta {

    @Id
    @Column(name = "id")
    private String id;

    private String diagnostico;

    @Column(name = "data_consulta")
    private LocalDate dataConsulta;

    @Column(name = "hora_consulta")
    private LocalTime horaConsulta;

    @ManyToOne
    @JoinColumn(name = "id_animal", referencedColumnName = "RFID")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "CRMV_veterinario")
    private Veterinario veterinario;

    @Column(name = "tipo")
    private String tipo;

    @OneToMany(mappedBy = "consulta")
    @JsonIgnore
    private List<Tratamento> tratamentos;


    public Consulta() {}

    // --- Getters e Setters ATUALIZADOS ---
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDiagnostico() {
        return diagnostico;
    }
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
    public LocalDate getDataConsulta() {return dataConsulta; }
    public void setDataConsulta(LocalDate dataConsulta) {this.dataConsulta = dataConsulta; }
    public LocalTime getHoraConsulta() {return horaConsulta; }
    public void setHoraConsulta(LocalTime horaConsulta) {this.horaConsulta = horaConsulta; }
    public Animal getAnimal() {
        return animal;
    }
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public Veterinario getVeterinario() {
        return veterinario;
    }
    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }
    public List<Tratamento> getTratamentos() {
        return tratamentos;
    }
    public void setTratamentos(List<Tratamento> tratamentos) {
        this.tratamentos = tratamentos;
    }
}