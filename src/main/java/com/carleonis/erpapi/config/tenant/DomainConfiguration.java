package com.carleonis.erpapi.config.tenant;


import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.carleonis.erpapi.config.tenant.properties.DataSourceProperties;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = {"com.carleonis.erpapi"})
@EnableJpaRepositories(basePackages = {"com.carleonis.erpapi"})
public class DomainConfiguration {

    @Bean
    @Primary
    public DataSource dataSource(DataSourceProperties dataSourceProperties) {
        AbstractRoutingDataSource tenantRoutingDataSource = new TenantRoutingDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        dataSourceProperties.getDatasources().forEach(dataSourceProperty -> {
            DataSource dataSource = DataSourceBuilder.create()
                    .url(dataSourceProperty.getUrl())
                    .username(dataSourceProperty.getUsername())
                    .password(dataSourceProperty.getPassword())
                    .driverClassName(dataSourceProperty.getDriverClassName())
                    .build();
            if (dataSourceProperty.getName().equalsIgnoreCase("default")) {
                tenantRoutingDataSource.setDefaultTargetDataSource(dataSource);
            }
            targetDataSources.put(dataSourceProperty.getName(), dataSource);
        });
        tenantRoutingDataSource.setTargetDataSources(targetDataSources);
        tenantRoutingDataSource.afterPropertiesSet();
        return tenantRoutingDataSource;
    }
}
