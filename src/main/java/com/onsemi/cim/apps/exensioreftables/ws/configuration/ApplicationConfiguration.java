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
import org.springframework.context.annotation.Primary;
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
        entityManagerFactoryRef = "applicationEntityManager",
        transactionManagerRef = "applicationTransactionManager",
        basePackages = "com.onsemi.cim.apps.exensioreftables.ws.repository.application")
public class ApplicationConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationConfiguration.class);

    private final Environment env;

    public ApplicationConfiguration(Environment env) {
        this.env = env;
    }

    @Bean("applicationEntityManager")
    @Primary
    public LocalContainerEntityManagerFactoryBean getApplicationEntityManager(EntityManagerFactoryBuilder builder,
            @Qualifier("applicationDataSource") DataSource applicationDataSource) {

        return builder
                .dataSource(applicationDataSource)
                .packages("com.onsemi.cim.apps.exensioreftables.ws.entity.application")
                .persistenceUnit("application")
                .build();
    }

    @Bean("applicationDataSource")
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSource getApplicationDataSource() {
        if (env.acceptsProfiles(Profiles.of("local"))) {
            return DataSourceBuilder.create().type(HikariDataSource.class).build();
        } else {
            final String datasourceName = env.getProperty("spring.datasource.jndi-name");
            try {
                if (StringUtils.isNotBlank(datasourceName)) {
                    return (HikariDataSource) new JndiTemplate().lookup(datasourceName);
                } else {
                    throw new RuntimeException("JNDI name 'spring.datasource.jndi-name' not present in active profile");
                }
            } catch (NamingException ex) {
                LOG.error(String.format("JNDI lookup of application datasource failed for name='%s'.", datasourceName), ex);
                throw new RuntimeException(ex);
            }
        }
    }

    @Bean("applicationTransactionManager")
    @Primary
    public JpaTransactionManager getApplicationTransactionManager(
            @Qualifier("applicationEntityManager") EntityManagerFactory applicationEntityManager) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(applicationEntityManager);

        return transactionManager;
    }
}
