package com.sshchipanov.parser.configurations;

import com.sshchipanov.parser.web.BCPListsParser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "configuration.web-access")
public class WebConfiguration {

    private String bcpListsUrl;

    @Bean
    @ConditionalOnProperty(name = "bcp-lists-url")
    public BCPListsParser bcpListsParser() {
        return new BCPListsParser(bcpListsUrl);
    }
}
