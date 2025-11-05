package org.example.ucb.clinica_api.control;

import org.example.ucb.clinica_api.model.Certificacao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioDeCertificacao extends JpaRepository<Certificacao, String> {


}
