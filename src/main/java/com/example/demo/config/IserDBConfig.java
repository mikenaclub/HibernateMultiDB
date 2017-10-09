package com.example.demo.config;

import com.example.demo.models.Iser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "iserEntityManager",
        transactionManagerRef = "iserTransactionManager",
        basePackages = "com.example.demo.DAO.Iser"
)
public class IserDBConfig {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.iser.datasource")
    public DataSource iserDataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }
    @Primary
    @Bean(name = "iserEntityManager")
    public LocalContainerEntityManagerFactoryBean iserEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(iserDataSource())
                .properties(hibernateProperties())
                .packages(Iser.class)
                .persistenceUnit("iserPU")
                .build();
    }
    @Primary
    @Bean(name = "iserSessionFactory")
    public HibernateJpaSessionFactoryBean iserSessionFactory(@Qualifier("iserEntityManager") EntityManagerFactory entityManagerFactory) {
        HibernateJpaSessionFactoryBean fact = new HibernateJpaSessionFactoryBean();
        fact.setEntityManagerFactory(entityManagerFactory);
        return fact;
    }
    @Primary
    @Bean(name = "iserTransactionManager")
    public PlatformTransactionManager iserTransactionManager(@Qualifier("iserEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
    private Map<String, Object> hibernateProperties() {

        Resource resource = new ClassPathResource("hibernate.properties");

        try {
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            return properties.entrySet().stream()
                    .collect(Collectors.toMap(
                            e -> e.getKey().toString(),
                            e -> e.getValue())
                    );
        } catch (IOException e) {
            return new HashMap<String, Object>();
        }
    }
}
