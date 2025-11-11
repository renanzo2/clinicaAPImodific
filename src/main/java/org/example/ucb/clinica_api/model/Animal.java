package org.example.ucb.clinica_api.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Animal")
public class Animal {

    @Id
    @Column(name = "RFID") // MUDADO DE "ID"
    private String rfid; // MUDADO DE Integer id

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
    private Dono dono;

    @OneToMany(mappedBy = "animal")
    @JsonIgnore
    private List<Consulta> consultas;

    public Animal() {}

    public Animal(String rfid, String nome, String porte, int idade, String especie, Dono dono) {
        this.rfid = rfid;
        this.nome = nome;
        this.porte = porte;
        this.idade = idade;
        this.especie = especie;
        this.dono = dono;
    }

    // Getters e Setters ATUALIZADOS
    public String getRfid() { return rfid; }
    public void setRfid(String rfid) { this.rfid = rfid; }

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