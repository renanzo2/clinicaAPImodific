package org.example.ucb.clinica_api.controller;

import org.example.ucb.clinica_api.control.RepositorioDeConsulta;
import org.example.ucb.clinica_api.model.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return repositorioDeConsulta.save(consulta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarConsultaporID(@PathVariable int id){
        Optional<Consulta> consulta = repositorioDeConsulta.findById(id);

        if(consulta.isPresent()){
            return ResponseEntity.ok(consulta.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Consulta> atualizarConsulta(@PathVariable int id, @RequestBody Consulta consultaAtualizada){
        if(!repositorioDeConsulta.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        consultaAtualizada.setid(id);
        Consulta consultaSalva = repositorioDeConsulta.save(consultaAtualizada);
        return ResponseEntity.ok(consultaSalva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerConsulta(@PathVariable int id){
        if(!repositorioDeConsulta.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        repositorioDeConsulta.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
