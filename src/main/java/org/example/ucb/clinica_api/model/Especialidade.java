package org.example.ucb.clinica_api.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "especialidade")
public class Especialidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDespecialidade")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @OneToMany(mappedBy = "especialidade")
    private List<Certificacao> certificacoes;

    // OBRIGATÓRIO: Construtor vazio
    public Especialidade() {}

    public Especialidade(int id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public int  getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}
