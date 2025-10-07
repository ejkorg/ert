package com.onsemi.cim.apps.exensioreftables.ws.logging;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Objects;

/**
 * Loads logging configuration from location defined in properties and sets it up. If not found, uses logback-default.xml from resources.
 *
 * @author fg6zdy
 */
@Component
public class LoggerConfigurator {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoggerConfigurator.class);
    private final static String PROFILE_LOCAL = "local";
    private final static String PROFILE_ORACLE = "oracle";
    private final static String LOGBACK_DEFAULT_LOCAL = "logback-default-local.xml";
    private final static String LOGBACK_DEFAULT_ORACLE = "logback-default-oracle.xml";

    @Value("${ert.log_conf_path}")
    private String logConfigurationFilePath;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @PostConstruct
    public boolean configureLogging() {

        final LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        final JoranConfigurator joranConfigurator = new JoranConfigurator();
        joranConfigurator.setContext(context);
        context.reset();

        try {
            LOGGER.info("Configuring logging using external configuration file {}", logConfigurationFilePath);
            joranConfigurator.doConfigure(logConfigurationFilePath);
            LOGGER.info("Logging using external configuration file {} configured", logConfigurationFilePath);
        } catch (JoranException e) {
            LOGGER.info("Could not configure logging using configuration file {}", logConfigurationFilePath);
            // was not able to load explicit logback configuration - use the default one as fallback
            try {
                // if the current profile is local use the default-local logback configuration
                final File defaultLogback;
                if (PROFILE_LOCAL.equals(activeProfile)) {
                    defaultLogback = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(LOGBACK_DEFAULT_LOCAL)).getFile());
                } else {
                    defaultLogback = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(LOGBACK_DEFAULT_ORACLE)).getFile());
                }
                LOGGER.info("Configuring logging using default configuration file {}", defaultLogback.getAbsolutePath());
                joranConfigurator.doConfigure(defaultLogback);
                LOGGER.info("Logging using default configuration file {} configured", defaultLogback.getAbsolutePath());
            } catch (JoranException e1) {
                //was not able to init neither default logback - no logging is set, should never occur
                throw new RuntimeException(e1);
            }
            LOGGER.error("Was not able to load explicit logging configuration from: " + logConfigurationFilePath + " , using default logging (Tomcat console)", e);
            return false;
        }
        return true;
    }

    public String getLogConfigurationFilePath() {
        return logConfigurationFilePath;
    }

    public void setLogConfigurationFilePath(String logConfigurationFilePath) {
        this.logConfigurationFilePath = logConfigurationFilePath;
    }

    public String getActiveProfile() {
        return activeProfile;
    }

    public void setActiveProfile(String activeProfile) {
        this.activeProfile = activeProfile;
    }
}
