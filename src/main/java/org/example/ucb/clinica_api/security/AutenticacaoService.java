package org.example.ucb.clinica_api.security;

import lombok.*;
import org.example.ucb.clinica_api.control.RepositorioDeUsuario;
import org.example.ucb.clinica_api.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@ToString
@Service
public class AutenticacaoService implements UserDetailsService{

    private final RepositorioDeUsuario repositorio;

    @Override
    public UserDetails loadUserByName(String username) throws UsernameNotFoundException {
        Usuario usuario = repositorio.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));

        String role = "ROLE_" + usuario.getGrupo().getNome().toUpperCase();

        return User.builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .authorities(role)
                .disabled(!usuario.isAtivo())
                .build();
    }
}
