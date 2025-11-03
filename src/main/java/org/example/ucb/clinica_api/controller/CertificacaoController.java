package org.example.ucb.clinica_api.controller;

import org.example.ucb.clinica_api.control.RepositorioDeCertificacao;
import org.example.ucb.clinica_api.model.Certificacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/certificacoes")
@CrossOrigin(origins = "*")

public class CertificacaoController {
    @Autowired
    private RepositorioDeCertificacao repositorioDeCertificacao;

    @GetMapping
    public List<Certificacao> listarCertificacoes() {
        return repositorioDeCertificacao.findAll();
    }

    @PostMapping
    public Certificacao adicionarCertificacao(@RequestBody Certificacao certificacao) {
        return repositorioDeCertificacao.save(certificacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificacao> buscarCertificacaoPorId(@PathVariable int id) {
        Optional<Certificacao> certificacao = repositorioDeCertificacao.findById(id);
        if (certificacao.isPresent()) {
            return ResponseEntity.ok(certificacao.get());
        }else  {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Certificacao> atualizarCertificacao(@PathVariable int id, @RequestBody Certificacao certificacaoAtualizada) {
        if (!repositorioDeCertificacao.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        certificacaoAtualizada.setId(id);
        Certificacao certificacaoSalva = repositorioDeCertificacao.save(certificacaoAtualizada);
        return ResponseEntity.ok(certificacaoSalva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Certificacao> removerCertificacao(@PathVariable int id) {
        if (!repositorioDeCertificacao.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repositorioDeCertificacao.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
