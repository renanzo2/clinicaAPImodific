package org.example.ucb.clinica_api.controller;

import org.example.ucb.clinica_api.control.RepositorioDeEspecialidade;
import org.example.ucb.clinica_api.model.Especialidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/especialidades")
@CrossOrigin(origins = "*")

public class EspecialidadeController {

    @Autowired
    private RepositorioDeEspecialidade repositorioDeEspecialidade;

    @GetMapping
    public List<Especialidade> listar(){
        return repositorioDeEspecialidade.findAll();
    }

    @PostMapping
    public Especialidade  salvar(@RequestBody Especialidade especialidade){
        return repositorioDeEspecialidade.save(especialidade);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidade> buscarPorID(@PathVariable int id){
        Optional<Especialidade> especialidade = repositorioDeEspecialidade.findById(id);
        if(especialidade.isPresent()){
            return ResponseEntity.ok(especialidade.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especialidade> atualizarEspecialidade(@PathVariable int id, @RequestBody Especialidade especialidadeAtualizada){
        if (!repositorioDeEspecialidade.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        especialidadeAtualizada.setId(id);
        Especialidade especialidadeSalva = repositorioDeEspecialidade.save(especialidadeAtualizada);
        return ResponseEntity.ok(especialidadeSalva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerEspecialidade(@PathVariable int id){
        if (!repositorioDeEspecialidade.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repositorioDeEspecialidade.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
