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

    @GetMapping("/{rfid}")
    public ResponseEntity<Animal> buscarAnimalPorRfid(@PathVariable String rfid) {
        Optional<Animal> animal = repositorioDeAnimal.findById(rfid);

        if (animal.isPresent()) {
            return ResponseEntity.ok(animal.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{rfid}")
    public ResponseEntity<Animal> atualizarAnimal(@PathVariable String rfid, @RequestBody Animal animalAtualizado) {
        if (!repositorioDeAnimal.existsById(rfid)) {
            return ResponseEntity.notFound().build();
        }

        animalAtualizado.setRfid(rfid);
        Animal animalSalvo = repositorioDeAnimal.save(animalAtualizado);
        return ResponseEntity.ok(animalSalvo);
    }

    @DeleteMapping("/{rfid}")
    public ResponseEntity<Void> removerAnimal(@PathVariable String rfid) {
        if (!repositorioDeAnimal.existsById(rfid)) {
            return ResponseEntity.notFound().build();
        }
        repositorioDeAnimal.deleteById(rfid);
        return ResponseEntity.noContent().build();
    }

}
