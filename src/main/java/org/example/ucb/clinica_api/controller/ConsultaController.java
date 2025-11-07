package org.example.ucb.clinica_api.controller;

import org.example.ucb.clinica_api.control.RepositorioDeConsulta;
import org.example.ucb.clinica_api.model.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID; // Import para gerar UUID

@RestController
@RequestMapping("/api/consultas")
@CrossOrigin(origins = "*")
public class ConsultaController {
    @Autowired
    private RepositorioDeConsulta repositorioDeConsulta;

    @GetMapping
    public List<Consulta> listarConsultas(){
        return repositorioDeConsulta.findAll();
    }

    @PostMapping
    public Consulta adicionarConsulta(@RequestBody Consulta consulta){
        // Como o ID é um UUID (VARCHAR(36)), ele deve ser gerado antes de salvar
        if (consulta.getId() == null || consulta.getId().isEmpty()) {
            consulta.setId(UUID.randomUUID().toString());
        }
        return repositorioDeConsulta.save(consulta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarConsultaporID(@PathVariable String id){ // MUDADO DE int
        Optional<Consulta> consulta = repositorioDeConsulta.findById(id);

        if(consulta.isPresent()){
            return ResponseEntity.ok(consulta.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> atualizarConsulta(@PathVariable String id, @RequestBody Consulta consultaAtualizada){ // MUDADO DE Integer
        if(!repositorioDeConsulta.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        consultaAtualizada.setId(id);
        Consulta consultaSalva = repositorioDeConsulta.save(consultaAtualizada);
        return ResponseEntity.ok(consultaSalva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerConsulta(@PathVariable String id){ // MUDADO DE int
        if(!repositorioDeConsulta.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        repositorioDeConsulta.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}