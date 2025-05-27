package com.sshchipanov.parser.configurations;

import com.sshchipanov.parser.storage.KustoRostersStorage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "storage")
public class StorageConfiguration {

    private String kustoConnectionString;

    @Bean
    @ConditionalOnProperty(name = "storage.kusto-connection-string")
    public KustoRostersStorage kustoRostersStroage() {
        return new KustoRostersStorage(kustoConnectionString);
    }
}
