package org.example.ucb.clinica_api.controller;

import org.example.ucb.clinica_api.control.RepositorioDeAnimal;
import org.example.ucb.clinica_api.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/animais")
@CrossOrigin(origins = "*")
public class AnimalController {

    @Autowired
    private RepositorioDeAnimal repositorioDeAnimal;

    @GetMapping
    public List<Animal> listarAnimais() {
        return repositorioDeAnimal.findAll();
    }

    @PostMapping
    public Animal adicionarAnimal(@RequestBody Animal animal) {
        // O RFID (ex: 'PET-0001') deve ser enviado pelo frontend
        // ou gerado aqui (ex: com a Function 'fnc_gerar_proximo_rfid_animal()')
        return repositorioDeAnimal.save(animal);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> buscarAnimalPorId(@PathVariable String id) { // MUDADO DE int
        Optional<Animal> animal = repositorioDeAnimal.findById(id);

        if (animal.isPresent()) {
            return ResponseEntity.ok(animal.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> atualizarAnimal(@PathVariable String id, @RequestBody Animal animalAtualizado) { // MUDADO DE int
        if (!repositorioDeAnimal.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        animalAtualizado.setRfid(id); // MUDADO DE setId
        Animal animalSalvo = repositorioDeAnimal.save(animalAtualizado);
        return ResponseEntity.ok(animalSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerAnimal(@PathVariable String id) { // MUDADO DE int
        if (!repositorioDeAnimal.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repositorioDeAnimal.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}