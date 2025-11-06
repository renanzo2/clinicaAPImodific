package org.example.ucb.clinica_api.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo; // IMPORT ADICIONADO
import com.fasterxml.jackson.annotation.ObjectIdGenerators; // IMPORT ADICIONADO
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "consulta")
// --- ANOTAÇÃO ADICIONADA ---
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
// -----------------------------
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate dataConsulta;
    private LocalTime horaConsulta;
    private String diagnostico;

    // (Lembre-se de adicionar o campo 'tipo' aqui e no seu SQL
    // se o seu frontend estiver enviando, como no index.html que corrigimos)
    // private String tipo;

    // --- Relacionamentos ---

    @ManyToOne
    @JoinColumn(name = "id_animal")
    // @JsonBackReference FOI REMOVIDO
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "CRMV_veterinario")
    // @JsonBackReference FOI REMOVIDO
    private Veterinario veterinario;

    @OneToMany(mappedBy = "consulta")
    // @JsonManagedReference FOI REMOVIDO
    private List<Tratamento> tratamentos;

    // OBRIGATÓRIO: Construtor vazio
    public Consulta() {}

    // --- Getters e Setters ---
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDate getDataConsulta() {
        return dataConsulta;
    }
    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }
    public LocalTime getHoraConsulta() {
        return horaConsulta;
    }
    public void setHoraConsulta(LocalTime horaConsulta) {
        this.horaConsulta = horaConsulta;
    }
    public String getDiagnostico() {
        return diagnostico;
    }
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
    public Animal getAnimal() {
        return animal;
    }
    public void setAnimal(Animal animal) {
        this.animal = animal;
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

    // (Getters/Setters para 'tipo' se você o adicionou)
}