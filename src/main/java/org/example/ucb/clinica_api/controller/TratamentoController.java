package org.example.ucb.clinica_api.controller;

import org.example.ucb.clinica_api.control.RepositorioDeTratamento;
import org.example.ucb.clinica_api.model.Tratamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID; // Import para gerar UUID

@RestController
@RequestMapping("/api/tratamentos")
@CrossOrigin(origins = "*")
public class TratamentoController {
    @Autowired
    private RepositorioDeTratamento repositorioDeTratamento;

    @GetMapping
    public List<Tratamento> ListarTratamentos(){
        return repositorioDeTratamento.findAll();
    }

    @PostMapping
    public Tratamento salvarTratamento(@RequestBody Tratamento tratamento){
        // Como o ID é um UUID (VARCHAR(36)), ele deve ser gerado antes de salvar
        if (tratamento.getId() == null || tratamento.getId().isEmpty()) {
            tratamento.setId(UUID.randomUUID().toString());
        }
        return repositorioDeTratamento.save(tratamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tratamento> buscarTratamentoPorID(@PathVariable String id){ // MUDADO DE int
        Optional<Tratamento> tratamento = repositorioDeTratamento.findById(id);

        if(tratamento.isPresent()){
            return ResponseEntity.ok(tratamento.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tratamento> atualizarTratamento(@PathVariable String id, @RequestBody Tratamento tratamentoAtualizado){ // MUDADO DE int
        if(!repositorioDeTratamento.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        tratamentoAtualizado.setId(id);
        Tratamento tratamentoSalvo = repositorioDeTratamento.save(tratamentoAtualizado);
        return ResponseEntity.ok(tratamentoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tratamento> removerTratamento(@PathVariable String id){ // MUDADO DE int
        if(!repositorioDeTratamento.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }

        repositorioDeTratamento.deleteById(id);
        return ResponseEntity.ok().build();
    }
}