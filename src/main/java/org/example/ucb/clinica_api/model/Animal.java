package org.example.ucb.clinica_api.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo; // IMPORT ADICIONADO
import com.fasterxml.jackson.annotation.ObjectIdGenerators; // IMPORT ADICIONADO
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Animal") // CORREÇÃO: "animal" -> "Animal"
// --- ANOTAÇÃO ADICIONADA ---
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
// -----------------------------
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Porte")
    private String porte;

    @Column(name = "Idade")
    private int idade;

    @Column(name = "Especie")
    private String especie;

    @ManyToOne
    @JoinColumn(name = "CPF_dono")
    // @JsonBackReference e @JsonManagedReference FORAM REMOVIDOS
    private Dono dono;

    @OneToMany(mappedBy = "animal")
    // @JsonBackReference e @JsonManagedReference FORAM REMOVIDOS
    private List<Consulta> consultas;

    public Animal() {}

    public Animal(Integer id, String nome, String porte, int idade, String especie, Dono dono) {
        this.id = id;
        this.nome = nome;
        this.porte = porte;
        this.idade = idade;
        this.especie = especie;
        this.dono = dono;
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getPorte() { return porte; }
    public void setPorte(String porte) { this.porte = porte; }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }
    public Dono getDono() { return dono; }
    public void setDono(Dono dono) { this.dono = dono; }
    public List<Consulta> getConsultas() { return consultas; }
    public void setConsultas(List<Consulta> consultas) { this.consultas = consultas; }
}