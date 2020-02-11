package br.org.pti.senha.application.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class ApplicationReadyListenerConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyListenerConfiguration.class);

    @Bean
    public ApplicationListener<ApplicationReadyEvent> getApplicationReadyEvent() {
        return applicationReadyEvent -> {
            LOGGER.info("----------------------------------------------");
            LOGGER.info("PTI-SENHA iniciado nos perfis de configuração: ");
            Arrays.asList(applicationReadyEvent.getApplicationContext().getEnvironment().getActiveProfiles()).forEach(LOGGER::info);
//            LOGGER.info("Acesse localhost:8080 "); TODO
            LOGGER.info("----------------------------------------------");
        };
    }
}