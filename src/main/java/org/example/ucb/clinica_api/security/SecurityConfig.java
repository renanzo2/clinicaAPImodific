package org.example.ucb.clinica_api.security;

import org.example.ucb.clinica_api.config.TenantContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter; // Import necessário
import org.springframework.security.web.context.SecurityContextHolderFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Bean para criptografar as senhas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Cria os usuários de login "na memória"
    @Bean
    public UserDetailsService userDetailsService() {
        String adminPassword = passwordEncoder().encode("admin123");
        String funcPassword = passwordEncoder().encode("func123");

        UserDetails admin = User.builder()
                .username("admin")
                .password(adminPassword)
                .roles("ADMIN")
                .build();

        UserDetails funcionario = User.builder()
                .username("funcionario")
                .password(funcPassword)
                .roles("FUNCIONARIO")
                .build();

        return new InMemoryUserDetailsManager(admin, funcionario);
    }

    // Filtro que intercepta CADA requisição para definir o DataSource
    @Bean
    public OncePerRequestFilter tenantFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain filterChain) throws ServletException, IOException {
                try {
                    var auth = SecurityContextHolder.getContext().getAuthentication();

                    if (auth != null && auth.isAuthenticated()) {
                        boolean isAdmin = auth.getAuthorities().stream()
                                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

                        if (isAdmin) {
                            TenantContext.setCurrentTenant("ADMIN");
                        } else {
                            TenantContext.setCurrentTenant("FUNCIONARIO");
                        }
                    } else {
                        TenantContext.setCurrentTenant("FUNCIONARIO");
                    }
                    filterChain.doFilter(request, response);
                } finally {
                    TenantContext.clear();
                }
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Regra do Spring: Só permite DELETE se tiver a role "ADMIN"
                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")

                        // Permite qualquer outra requisição na API para ADMIN ou FUNCIONARIO
                        .requestMatchers("/api/**").hasAnyRole("ADMIN", "FUNCIONARIO")

                        // ================== ALTERAÇÃO AQUI ==================
                        // REMOVEMOS O .permitAll() DO index.html
                        // Agora, TUDO (incluindo a página) exige autenticação.
                        .anyRequest().authenticated()
                )
                // ================== FIM DA ALTERAÇÃO ================

                // Garante que nosso filtro de "tenant" rode DEPOIS da autenticação
                .addFilterAfter(tenantFilter(), BasicAuthenticationFilter.class)

                // Usa o popup de login (HTTP Basic)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        //Libera o endpoint de login e arquivos estáticos
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers("/index.html", "/", "/static/**").permitAll()
                        // Permissao de DELETE para administradores
                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                        // Qualquer outra requisição além dessas precisam de estar logado
                        .anyRequest().authenticated()
                )
                .addFilterAfter(tenantFilter(), BasicAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}