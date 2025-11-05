package org.example.ucb.clinica_api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Dono") // Mapeia para a tabela "Dono" (com 'D' maiúsculo)
public class Dono {

    @Id
    @Column(name = "CPF") // "DE-PARA": Campo Java 'cpf' -> Coluna SQL 'CPF'
    private String cpf;

    @Column(name = "Nome") // "DE-PARA": Campo Java 'nome' -> Coluna SQL 'Nome'
    private String nome;

    @Column(name = "Endereco") // "DE-PARA"
    private String endereco;

    @Column(name = "data_nasc") // "DE-PARA": Campo Java 'dataNascimento' -> Coluna SQL 'data_nasc'
    private LocalDate dataNascimento;

    @Column(name = "telefone") // "DE-PARA"
    private String telefone; // MUDANÇA: De 'Long' para 'String'

    @Column(name = "email") // "DE-PARA"
    private String email;

    @OneToMany(mappedBy = "dono")
    @JsonManagedReference
    private List<Animal> animais;

    // Construtor vazio (Obrigatório)
    public Dono() {}

    // Construtor (opcional, mas bom ter)
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