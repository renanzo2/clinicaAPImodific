package org.example.ucb.clinica_api.model;

import jakarta.persistence.*;

@Entity // 1. Diz que é uma tabela
@Table(name = "tratamento") // 2. O nome da tabela no banco
public class Tratamento {

    @Id // 3. Chave Primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 4. É AUTO_INCREMENT
    private Integer id;

    private String descricao;
    private boolean antibiotico; // Tipo boolean para Sim/Não

    // --- Relacionamentos ---

    // 5. MUITOS Tratamentos podem pertencer a UMA Consulta
    @ManyToOne
    @JoinColumn(name = "id_consulta") // O nome da sua coluna FK que aponta para Consulta
    private Consulta consulta;

    // 6. OBRIGATÓRIO: Construtor vazio
    public Tratamento() {}

    // --- Getters e Setters ---
    // (Adicione aqui todos os getters e setters para os campos)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAntibiotico() {
        return antibiotico;
    }

    public void setAntibiotico(boolean antibiotico) {
        this.antibiotico = antibiotico;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
}