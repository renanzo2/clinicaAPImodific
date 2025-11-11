package org.example.ucb.clinica_api.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo; // IMPORT ADICIONADO
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators; // IMPORT ADICIONADO
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Dono")
public class Dono {

    @Id
    @Column(name = "CPF")
    private String cpf;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Endereco")
    private String endereco;

    @Column(name = "data_nasc")
    private LocalDate dataNascimento;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "dono")
    @JsonIgnore
    private List<Animal> animais;

    // Construtor vazio
    public Dono() {}

    // Construtor
    public Dono(String cpf, String nome, String endereco, LocalDate dataNascimento, String telefone, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
    }

    // --- Getters e Setters ---
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public List<Animal> getAnimais() { return animais; }
    public void setAnimais(List<Animal> animais) { this.animais = animais; }
}