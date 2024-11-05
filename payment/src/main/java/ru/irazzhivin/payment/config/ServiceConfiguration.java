package ru.irazzhivin.payment.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ExecutorsProperties.class)
public class ServiceConfiguration {
}
