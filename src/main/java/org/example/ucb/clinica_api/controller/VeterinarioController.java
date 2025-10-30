package org.example.ucb.clinica_api.controller;

import org.example.ucb.clinica_api.control.RepositorioDeVeterinario;
import org.example.ucb.clinica_api.model.Veterinario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 1. Diz ao Spring que esta classe é um "Garçom" (Controlador de API)
@RequestMapping("/api/veterinarios") // 2. O endereço base (ex: http://localhost:8080/api/veterinarios)
@CrossOrigin(origins = "*")

public class VeterinarioController {
    @Autowired
    private RepositorioDeVeterinario repositorioDeveterinario;

    @GetMapping
    public List<Veterinario> ListarVeterinarios() {
        return repositorioDeveterinario.findAll();
    }

    @PostMapping Veterinario salvarVeterinario(@RequestBody Veterinario veterinario) {
        return repositorioDeveterinario.save(veterinario);
    }

    @GetMapping
    public Veterinario buscarProCrmv(@RequestParam String id) {
        return repositorioDeveterinario.findById(id).get();
    }

    @DeleteMapping("/{crmv}")
    public void deletarVeterinario(@PathVariable String crmv, RequestBody Veterinario vetAtualizado) {
        return repositorioDeveterinario.save(vetAtualizado);
    }

}
