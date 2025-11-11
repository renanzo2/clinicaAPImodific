package org.example.ucb.clinica_api.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        // Pega o "nome" do usuário ("ADMIN" ou "FUNCIONARIO")
        // que foi salvo no TenantContext
        return TenantContext.getCurrentTenant();
    }
}