package com.onsemi.cim.apps.exensioreftables.ws.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "dataWarehouseEntityManager",
        transactionManagerRef = "dataWarehouseTransactionManager",
        basePackages = "com.onsemi.cim.apps.exensioreftables.ws.repository.datawarehouse")
public class DataWarehouseConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(DataWarehouseConfiguration.class);

    private final Environment env;

    public DataWarehouseConfiguration(Environment env) {
        this.env = env;
    }

    @Bean("dataWarehouseEntityManager")
    public LocalContainerEntityManagerFactoryBean getDataWarehouseEntityManager(
            EntityManagerFactoryBuilder builder, @Qualifier("dataWarehouseDataSource") DataSource dataWarehouseDataSource) {

        return builder
                .dataSource(dataWarehouseDataSource)
                .packages("com.onsemi.cim.apps.exensioreftables.ws.entity.datawarehouse")
                .persistenceUnit("datawarehouse")
                .build();
    }

    @Bean("dataWarehouseDataSource")
    @ConfigurationProperties("datawarehouse.datasource")
    public DataSource getDataWarehouseDataSource() {
        if (env.acceptsProfiles(Profiles.of("local", "local_prod"))) {
            return DataSourceBuilder.create().type(HikariDataSource.class).build();
        } else {
            final String datasourceName = env.getProperty("datawarehouse.datasource.jndi-name");
            try {
                if (StringUtils.isNotBlank(datasourceName)) {
                    return (HikariDataSource) new JndiTemplate().lookup(datasourceName);
                } else {
                    throw new RuntimeException("JNDI name 'datawarehouse.datasource.jndi-name' not present in active profile");
                }
            } catch (NamingException ex) {
                LOG.error(String.format("JNDI lookup of application datasource failed for name='%s'.", datasourceName), ex);
                throw new RuntimeException(ex);
            }
        }
    }


    @Bean("dataWarehouseTransactionManager")
    public JpaTransactionManager getDataWarehouseTransactionManager(
            @Qualifier("dataWarehouseEntityManager") EntityManagerFactory dataWarehouseEntityManager) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(dataWarehouseEntityManager);

        return transactionManager;
    }
}
