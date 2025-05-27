package com.sshchipanov.parser.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({StorageConfiguration.class, WebConfiguration.class})
public class ApplicationConfiguration {
}
