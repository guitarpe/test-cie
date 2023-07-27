package br.com.clima.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "cieDbEntityManagerFactory",
        transactionManagerRef = "cieDbTransactionManager",
        basePackages = {"br.com.clima.application.domain.repository.cie"})
public class PostgreeDBCieDataSourceConfig {
    @Primary
    @Bean(name = "cieDbDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource-ciedb")
    public DataSourceProperties cieDbDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "cieDbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource-first.configuration")
    public DataSource cieDbDataSource(@Qualifier("cieDbDataSourceProperties") DataSourceProperties cieDbDataSourceProperties) {
        return cieDbDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "cieDbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean cieDbEntityManagerFactory(
            EntityManagerFactoryBuilder cieDbEntityManagerFactoryBuilder, @Qualifier("cieDbDataSource") DataSource cieDbDataSource) {

        Map<String, String> cieDbJpaProperties = new HashMap<>();
        cieDbJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");

        return cieDbEntityManagerFactoryBuilder
                .dataSource(cieDbDataSource)
                .packages("br.com.clima.application.domain.model.cie")
                .persistenceUnit("cieDbDataSource")
                .properties(cieDbJpaProperties)
                .build();
    }

    @Primary
    @Bean(name = "cieDbTransactionManager")
    public PlatformTransactionManager cieDbTransactionManager(
            @Qualifier("cieDbEntityManagerFactory") EntityManagerFactory cieDbEntityManagerFactory) {

        return new JpaTransactionManager(cieDbEntityManagerFactory);
    }
}
