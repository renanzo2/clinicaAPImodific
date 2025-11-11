package org.example.ucb.clinica_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JpaConfig {

    // 1. Injeta os dois DataSources que criamos
    @Autowired
    @Qualifier("adminDataSource")
    private DataSource adminDataSource;

    @Autowired
    @Qualifier("funcionarioDataSource")
    private DataSource funcionarioDataSource;

    // 2. Cria o Roteador e informa quais são os DataSources disponíveis
    @Bean
    @Primary // Marca este como o DataSource principal para o Spring
    public DataSource dataSource() {
        RoutingDataSource routingDataSource = new RoutingDataSource();

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("ADMIN", adminDataSource);
        targetDataSources.put("FUNCIONARIO", funcionarioDataSource);

        routingDataSource.setTargetDataSources(targetDataSources);
        // Define o admin como padrão
        routingDataSource.setDefaultTargetDataSource(adminDataSource);

        return routingDataSource;
    }

    // --- Configurações padrão do JPA para usar o Roteador ---

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource()); // Usa o Roteador
        em.setPackagesToScan("org.example.ucb.clinica_api.model");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}