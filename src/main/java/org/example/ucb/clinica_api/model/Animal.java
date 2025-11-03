package org.example.ucb.clinica_api.model;

public class Animal {
    //Declarando os atributos
    private String rfid;
    private String nome;
    private String porte;
    private int idade;
    private String especie;
    private Dono dono;

    //Construtores

    public Animal(){

    }

    public Animal (int id, String nome){

    }
    public Animal(int id, String nome, String porte, int idade, String especie, Dono dono) {
        this.rfid = rfid;
        this.nome = nome;
        this.porte = porte;
        this.idade = idade;
        this.especie = especie;
        this.dono = dono;
    }

    //Gets e Sets
    public String getRfid() {
        return rfid;
    }
    public void setRfid(String rfid) {
        this.rfid = rfid;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getPorte() {
        return porte;
    }
    public void setPorte(String porte) {
        this.porte = porte;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public String getEspecie() {
        return especie;
    }
    public void setEspecie(String especie) {
        this.especie = especie;
    }
    public Dono getDono() {
        return dono;
    }
    public void setDono(Dono dono) {
        this.dono = dono;
    }
}
