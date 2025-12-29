package org.example.ucb.clinica_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "grupos_usuarios")
public class GrupoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgrupo")
    private Integer id;

    @Column(name = "nome_grupo")
    private String nome;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

}
