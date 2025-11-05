package org.example.ucb.clinica_api.controller;

import org.example.ucb.clinica_api.control.RepositorioDeDono;
import org.example.ucb.clinica_api.model.Dono;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/donos")
@CrossOrigin(origins = "*")

public class DonoController {
    @Autowired
    private RepositorioDeDono repositorioDeDono;

    @GetMapping
    public List<Dono> listarDonos() {
        return repositorioDeDono.findAll();
    }

    @PostMapping
    public Dono adicionarDono(@RequestBody Dono dono) {
        return repositorioDeDono.save(dono);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Dono> buscarDono(@PathVariable String cpf) {
        Optional<Dono> dono = repositorioDeDono.findById(cpf);

        if (dono.isPresent()){
            return ResponseEntity.ok(dono.get());
        }else  {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Dono> atualizarDono(@PathVariable String cpf, @RequestBody Dono donoAtualizado) {
        if (!repositorioDeDono.existsById(cpf)) {
            return ResponseEntity.notFound().build();
        }

        donoAtualizado.setCpf(cpf);
        Dono donoSalvo = repositorioDeDono.save(donoAtualizado);
        return ResponseEntity.ok(donoSalvo);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> removerDono(@PathVariable String cpf) {
        if (!repositorioDeDono.existsById(cpf)) {
            return ResponseEntity.notFound().build();
        }

        repositorioDeDono.deleteById(cpf);

        return ResponseEntity.noContent().build();
    }
}
