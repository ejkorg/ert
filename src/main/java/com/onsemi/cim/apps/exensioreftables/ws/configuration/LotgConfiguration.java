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
        entityManagerFactoryRef = "lotGEntityManager",
        transactionManagerRef = "lotGTransactionManager",
        basePackages = "com.onsemi.cim.apps.exensioreftables.ws.repository.lotg")
public class LotgConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(LotgConfiguration.class);

    private final Environment env;

    public LotgConfiguration(Environment env) {
        this.env = env;
    }

    @Bean("lotGEntityManager")
    public LocalContainerEntityManagerFactoryBean getLotGEntityManager(
            EntityManagerFactoryBuilder builder, @Qualifier("lotGDataSource") DataSource lotGDataSource) {

        return builder
                .dataSource(lotGDataSource)
                .packages("com.onsemi.cim.apps.exensioreftables.ws.entity.lotg")
                .persistenceUnit("lotg")
                .build();
    }

    @Bean("lotGDataSource")
    @ConfigurationProperties("lotg.datasource")
    public DataSource getLotGDataSource() {
        if (env.acceptsProfiles(Profiles.of("local", "local_prod"))) {
            return DataSourceBuilder.create().type(HikariDataSource.class).build();
        } else {
            final String datasourceName = env.getProperty("lotg.datasource.jndi-name");
            try {
                if (StringUtils.isNotBlank(datasourceName)) {
                    return (HikariDataSource) new JndiTemplate().lookup(datasourceName);
                } else {
                    throw new RuntimeException("JNDI name 'lotg.datasource.jndi-name' not present in active profile");
                }
            } catch (NamingException ex) {
                LOG.error(String.format("JNDI lookup of application datasource failed for name='%s'.", datasourceName), ex);
                throw new RuntimeException(ex);
            }
        }
    }

    @Bean("lotGTransactionManager")
    public JpaTransactionManager getLotGTransactionManager(
            @Qualifier("lotGEntityManager") EntityManagerFactory lotGEntityManager) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(lotGEntityManager);

        return transactionManager;
    }
}
