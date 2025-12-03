package org.example.ucb.clinica_api.control;

import org.example.ucb.clinica_api.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RepositorioDeAnimal extends JpaRepository<Animal, String> { // MUDADO DE Integer
    @Query( value = "SELECT fnc_gerar_proximo_rfid_animal()", nativeQuery = true)
    String gerarProximoRfid();

}