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
        return repositorioDeAnimal.save(animal);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> buscarAnimalPorId(@PathVariable int id) {
        Optional<Animal> animal = repositorioDeAnimal.findById(id);

        if (animal.isPresent()) {
            return ResponseEntity.ok(animal.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> atualizarAnimal(@PathVariable int id, @RequestBody Animal animalAtualizado) {
        if (!repositorioDeAnimal.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        animalAtualizado.setId(id);
        Animal animalSalvo = repositorioDeAnimal.save(animalAtualizado);
        return ResponseEntity.ok(animalSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerAnimal(@PathVariable int id) {
        if (!repositorioDeAnimal.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repositorioDeAnimal.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
