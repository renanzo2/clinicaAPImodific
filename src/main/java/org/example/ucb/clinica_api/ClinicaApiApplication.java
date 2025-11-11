package org.example.ucb.clinica_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.example.ucb.clinica_api.control.RepositorioDeDono;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class ClinicaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicaApiApplication.class, args);
	}

    @Bean
    public CommandLineRunner testDatabaseConnections(
            RepositorioDeDono repoDono,
            MongoTemplate mongoTemplate
    ) {
        return args -> {
            System.out.println("====================");
            System.out.println("Iniciando a conexão com bancos...");

            try {
                long donoCount = repoDono.count();
                System.out.println("Sucesso na conexão com o MySQL");
                System.out.println("Donos encontrados em MySQL" + donoCount);
            } catch (Exception e) {
                System.out.println("Falaha ao conectar com MySQL");
                System.err.println("Erro MySQL: " + e.getMessage());
            }

            try {
                mongoTemplate.getCollectionNames();
                System.out.println("Sucesso na conexão com o MongoDB");
            } catch (Exception e) {
                System.out.println("Erro ao conectar com o MongoDB");
                System.err.println("Erro MongoDb: " + e.getMessage());
            }
            System.out.println("=============================");
        };
    }

}
