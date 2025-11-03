package org.example.ucb.clinica_api.controller;

import org.example.ucb.clinica_api.control.RepositorioDeTratamento;
import org.example.ucb.clinica_api.model.Tratamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return repositorioDeTratamento.save(tratamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tratamento> buscarTratamentoPorID(@PathVariable int id){
        Optional<Tratamento> tratamento = repositorioDeTratamento.findById(id);

        if(tratamento.isPresent()){
            return ResponseEntity.ok(tratamento.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tratamento> atualizarTratamento(@PathVariable int id, @RequestBody Tratamento tratamentoAtualizado){
        if(!repositorioDeTratamento.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        tratamentoAtualizado.setId(id);
        Tratamento tratamentoSalvo = repositorioDeTratamento.save(tratamentoAtualizado);
        return ResponseEntity.ok(tratamentoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tratamento> removerTratamento(@PathVariable int id){
        if(!repositorioDeTratamento.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }

        repositorioDeTratamento.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
