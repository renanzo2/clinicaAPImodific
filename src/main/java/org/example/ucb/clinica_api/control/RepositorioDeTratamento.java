package org.example.ucb.clinica_api.control;

import org.example.ucb.clinica_api.model.Tratamento;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioDeTratamento extends JpaRepository<Tratamento, Integer> {

}
