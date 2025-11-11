package org.example.ucb.clinica_api.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource adminDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/clinica")
                .username("clinica_app") // Usuário ADM
                .password("senha_forte_do_app_123")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    @Bean
    public DataSource funcionarioDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/clinica")
                .username("clinica_funcionario") // Usuário COMUM
                .password("senha_leitura_456")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
}