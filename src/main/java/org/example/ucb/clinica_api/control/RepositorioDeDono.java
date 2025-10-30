package org.example.ucb.clinica_api.control;

import org.example.ucb.clinica_api.model.Dono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioDeDono extends JpaRepository<Dono,String> {

}
