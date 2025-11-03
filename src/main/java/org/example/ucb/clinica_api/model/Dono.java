package org.example.ucb.clinica_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="dono")
public class Dono {

    //Declaração dos atributos
    @Id
    private String CPF;
    private String nome;
    private String endereco;
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "dono")
    private List<Animal> animais;

    //Construtores
    public Dono(String CPF, LocalDate dataNascimento, String endereco, String nome) {
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.nome = nome;
    }


    //Gets
    public String getCPF() {
        return CPF;
    }
    public String  getNome() {
        return nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    //Sets
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
