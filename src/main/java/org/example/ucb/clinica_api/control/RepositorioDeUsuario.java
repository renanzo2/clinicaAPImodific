package org.example.ucb.clinica_api.control;

import org.example.ucb.clinica_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RepositorioDeUsuario extends JpaRepository<Usuario, String>{
    Optional<Usuario> findByLogin(String login);
}
