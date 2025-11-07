package org.example.ucb.clinica_api.control;

import org.example.ucb.clinica_api.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioDeConsulta extends JpaRepository<Consulta, String>{ // MUDADO DE Integer

}