package com.mmhealth.MiIngresoBack.config;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) Todos  los derechos reservados.
 * Creado por JOSE GOMEZ en 27/02/25
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "com.mmhealth.MiIngresoBack.repositories",
        entityManagerFactoryRef = "mainEntityManagerFactory",
        transactionManagerRef = "mainTransactionManager"
)
public class MainJpaConfig {

    /**
     * Configura y crea el EntityManagerFactory principal para la aplicación.
     *
     * @param dataSource Fuente de datos cualificada como "dataSourcePublico" para la conexión a BD
     * @return LocalContainerEntityManagerFactoryBean configurado para gestionar entidades JPA
     * @Primary Indica que este es el EntityManagerFactory por defecto en caso de múltiples configuraciones
     * @Bean Define este método como un bean de Spring con nombre "mainEntityManagerFactory"
     */
    @Primary
    @Bean(name = "mainEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mainEntityManagerFactory(
            @Qualifier("dataSourcePublico") DataSource dataSource) {
        // Crea una nueva instancia del factory de EntityManager
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        // Configura la fuente de datos a utilizar
        em.setDataSource(dataSource);
        // Establece el paquete base donde buscar las entidades
        em.setPackagesToScan("com.mmhealth.MiIngresoBack.entities");
        // Configura Hibernate como el proveedor de JPA
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        // Configura propiedades específicas de Hibernate
        Map<String, Object> properties = new HashMap<>();
        // Establece PostgreSQL como el dialecto de base de datos
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        // Configura la actualización automática del esquema
        properties.put("hibernate.hbm2ddl.auto", "update");
        // Aplica las propiedades configuradas
        em.setJpaPropertyMap(properties);

        return em;
    }

    /**
     * Configura y crea el administrador de transacciones principal.
     *
     * @param entityManagerFactory Factory de EntityManager inyectado y cualificado
     * @return PlatformTransactionManager configurado para gestionar transacciones JPA
     * @Primary Indica que este es el TransactionManager por defecto
     * @Bean Define este método como un bean de Spring con nombre "mainTransactionManager"
     */
    @Primary
    @Bean(name = "mainTransactionManager")
    public PlatformTransactionManager mainTransactionManager(
            @Qualifier("mainEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        // Crea y retorna un nuevo administrador de transacciones JPA
        return new JpaTransactionManager(entityManagerFactory);
    }
}
