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

    @GetMapping("/{numeroRegistro}")
    public ResponseEntity<Certificacao> buscarCertificacaoPorNumeroRegistro(@PathVariable String numeroRegistro) {
        Optional<Certificacao> certificacao = repositorioDeCertificacao.findById(numeroRegistro);
        return certificacao.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{numeroRegistro}")
    public ResponseEntity<Certificacao> atualizarCertificacao(@PathVariable String numeroRegistro,
                                                              @RequestBody Certificacao certificacaoAtualizada) {
        if (!repositorioDeCertificacao.existsById(numeroRegistro)) {
            return ResponseEntity.notFound().build();
        }

        certificacaoAtualizada.setNumeroRegistro(numeroRegistro);
        Certificacao certificacaoSalva = repositorioDeCertificacao.save(certificacaoAtualizada);
        return ResponseEntity.ok(certificacaoSalva);
    }

    @DeleteMapping("/{numeroRegistro}")
    public ResponseEntity<Void> removerCertificacao(@PathVariable String numeroRegistro) {
        if (!repositorioDeCertificacao.existsById(numeroRegistro)) {
            return ResponseEntity.notFound().build();
        }

        repositorioDeCertificacao.deleteById(numeroRegistro);
        return ResponseEntity.ok().build();
    }
}
