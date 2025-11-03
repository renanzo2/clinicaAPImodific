package org.example.ucb.clinica_api.controller;

import org.example.ucb.clinica_api.control.RepositorioDeVeterinario;
import org.example.ucb.clinica_api.model.Veterinario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/veterinarios")
@CrossOrigin(origins = "*")

public class VeterinarioController {
    @Autowired
    private RepositorioDeVeterinario repositorioDeveterinario;

    @GetMapping
    public List<Veterinario> ListarVeterinarios() {
        return repositorioDeveterinario.findAll();
    }

    @PostMapping
    public Veterinario salvarVeterinario(@RequestBody Veterinario veterinario) {
        return repositorioDeveterinario.save(veterinario);
    }

    @GetMapping("/{crmv}")
    public ResponseEntity<Veterinario> buscarProCrmv(@PathVariable String crmv) {
        Optional<Veterinario> vet = repositorioDeveterinario.findById(crmv);

        if (vet.isPresent()) {
            return ResponseEntity.ok().body(vet.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{crmv}")
    public ResponseEntity<Veterinario> atualizarVeterinario(@PathVariable String crmv, @RequestBody Veterinario vetAtualizado) {
        if  (!repositorioDeveterinario.existsById(crmv)) {
            return ResponseEntity.notFound().build();
        }
        vetAtualizado.setCrmv(crmv);

        Veterinario vetSalvo = repositorioDeveterinario.save(vetAtualizado);
        return ResponseEntity.ok(vetSalvo);
    }

    @DeleteMapping("/{crmv}")
    public ResponseEntity<Void> deletarVeterinario(@PathVariable String crmv) {
        if (!repositorioDeveterinario.existsById(crmv)) {
            return ResponseEntity.notFound().build();
        }

        repositorioDeveterinario.deleteById(crmv);
        return ResponseEntity.noContent().build();
    }
}