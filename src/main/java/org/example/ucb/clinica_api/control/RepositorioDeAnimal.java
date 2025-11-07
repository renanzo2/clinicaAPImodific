package org.example.ucb.clinica_api.control;

import org.example.ucb.clinica_api.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RepositorioDeAnimal extends JpaRepository<Animal, String> { // MUDADO DE Integer

}