package org.example.ucb.clinica_api.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "animal")
public class Animal {
    @Id
    private String rfid;

    private String nome;
    private String porte;
    private int idade;
    private String especie;

    @ManyToOne
    @JoinColumn(name = "id_dono")
    private Dono dono;

    @OneToMany(mappedBy = "animal")
    private List<Consulta> consultas;

    public Animal() {}

    public Animal(String rfid, String nome) {
        this.rfid = rfid;
        this.nome = nome;
    }

    public Animal(String rfid, String nome, String porte, int idade, String especie, Dono dono) {
        this.rfid = rfid;
        this.nome = nome;
        this.porte = porte;
        this.idade = idade;
        this.especie = especie;
        this.dono = dono;
    }

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
}
