package org.example.ucb.clinica_api.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime; // Para guardar a hora
import java.util.List;

@Entity // 1. Diz que é uma tabela
@Table(name = "consulta") // 2. O nome da tabela no banco
public class Consulta {

    @Id // 3. Chave Primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 4. É AUTO_INCREMENT
    private Integer id;

    private LocalDate dataConsulta; // Use LocalDate para a data
    private LocalTime horaConsulta; // Use LocalTime para a hora
    private String diagnostico;

    // --- Relacionamentos ---

    // 5. MUITAS Consultas podem pertencer a UM Animal
    @ManyToOne
    @JoinColumn(name = "id_animal") // O nome da sua coluna FK que aponta para Animal
    private Animal animal;

    // 6. MUITAS Consultas podem pertencer a UM Veterinario
    @ManyToOne
    @JoinColumn(name = "crmv_vet") // O nome da sua coluna FK que aponta para Veterinario
    private Veterinario veterinario;

    // 7. UMA Consulta pode ter MUITOS Tratamentos
    @OneToMany(mappedBy = "consulta")
    private List<Tratamento> tratamentos;

    // 8. OBRIGATÓRIO: Construtor vazio
    public Consulta() {}

    // --- Getters e Setters ---
    // (Adicione aqui todos os getters e setters para os campos)

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
}