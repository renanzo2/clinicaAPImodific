package org.example.ucb.clinica_api.controller;

import org.example.ucb.clinica_api.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest dados) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dados.login(), dados.senha()));

            return ResponseEntity.ok().body("Login realizado com sucesso! Toekn (simulado): user-auth-token");
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Usuário ou senha incorretos.");
        }
    }
    public record LoginRequest(String login, String senha){}
}
