package org.example.ucb.clinica_api.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "consulta")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Consulta {

    @Id
    @Column(name = "id") // Coluna 'id' agora é VARCHAR(36)
    private String id; // MUDADO DE Integer

    // @GeneratedValue FOI REMOVIDO

    private LocalDate dataConsulta;
    private LocalTime horaConsulta;
    private String diagnostico;

    private String tipo; // CAMPO ADICIONADO (do frontend)

    @ManyToOne
    // Referencia a PK 'RFID' da tabela 'Animal'
    @JoinColumn(name = "id_animal", referencedColumnName = "RFID")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "CRMV_veterinario")
    private Veterinario veterinario;

    @OneToMany(mappedBy = "consulta")
    private List<Tratamento> tratamentos;

    public Consulta() {}

    // --- Getters e Setters ATUALIZADOS ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public LocalDate getDataConsulta() { return dataConsulta; }
    public void setDataConsulta(LocalDate dataConsulta) { this.dataConsulta = dataConsulta; }
    public LocalTime getHoraConsulta() { return horaConsulta; }
    public void setHoraConsulta(LocalTime horaConsulta) { this.horaConsulta = horaConsulta; }
    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
    public Animal getAnimal() { return animal; }
    public void setAnimal(Animal animal) { this.animal = animal; }
    public Veterinario getVeterinario() { return veterinario; }
    public void setVeterinario(Veterinario veterinario) { this.veterinario = veterinario; }
    public List<Tratamento> getTratamentos() { return tratamentos; }
    public void setTratamentos(List<Tratamento> tratamentos) { this.tratamentos = tratamentos; }
}