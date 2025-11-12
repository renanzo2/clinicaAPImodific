package org.example.ucb.clinica_api.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class HistoricoConsulta {

    private String consultaId;
    private String diagnostico;
    private String tipo;
    private LocalDate dataConsulta;
    private LocalTime horaConsulta;
    private String nomeVeterinario;
    private TratamentoEmbed tratamento;



    public String getConsultaId() { return consultaId; }
    public void setConsultaId(String consultaId) { this.consultaId = consultaId; }
    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public LocalDate getDataConsulta() { return dataConsulta; }
    public void setDataConsulta(LocalDate dataConsulta) { this.dataConsulta = dataConsulta; }
    public LocalTime getHoraConsulta() { return horaConsulta; }
    public void setHoraConsulta(LocalTime horaConsulta) { this.horaConsulta = horaConsulta; }
    public String getNomeVeterinario() { return nomeVeterinario; }
    public void setNomeVeterinario(String nomeVeterinario) { this.nomeVeterinario = nomeVeterinario; }
    public TratamentoEmbed getTratamento() { return tratamento; }
    public void setTratamento(TratamentoEmbed tratamento) { this.tratamento = tratamento; }
}

