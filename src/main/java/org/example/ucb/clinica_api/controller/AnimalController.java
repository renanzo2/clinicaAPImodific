package org.example.ucb.clinica_api.controller;

import com.sun.jdi.event.ExceptionEvent;
import org.apache.coyote.Response;
import org.aspectj.apache.bcel.Repository;
import org.example.ucb.clinica_api.control.RepositorioDeAnimal;
import org.example.ucb.clinica_api.control.SyncService;
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

    @Autowired
    private SyncService syncService;

    @GetMapping
    public List<Animal> listarAnimais() {
        return repositorioDeAnimal.findAll();
    }

    @PostMapping
    public Animal adicionarAnimal(@RequestBody Animal animal) {
        // Chama a função do banco de dados para gerar a ID de forma automáztica (EX: PET-0001)
        String novoRfid = repositorioDeAnimal.gerarProximoRfid();
        //Define o ID no objeto antes do salvamento
        animal.setRfid(novoRfid);

        //Salva no MySQL
        Animal animalSalvo = repositorioDeAnimal.save(animal);
        //sincronização automatica com o MongoDB
        syncService.sincronizarAnimalEspecifico(animalSalvo.getRfid());

        return animalSalvo;
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
        syncService.sincronizarAnimalEspecifico(animalSalvo.getRfid());
        return ResponseEntity.ok(animalSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerAnimal(@PathVariable String id) { // MUDADO DE int
        if (!repositorioDeAnimal.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        try {
            //Remove do MySQL
            repositorioDeAnimal.deleteById(id);
            //Se deletar do SQl, retira também do Mongo
            syncService.removerAnimalMongo(id);
            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            System.err.println("Erro ao excluir: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}