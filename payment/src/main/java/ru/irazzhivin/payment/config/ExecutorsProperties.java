package ru.irazzhivin.payment.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "integrations.executors")
public class ExecutorsProperties {
    private final RestTemplateProperties productsExecutorClient;

    public RestTemplateProperties getProductsExecutorClient() {
        return productsExecutorClient;
    }

    public ExecutorsProperties(RestTemplateProperties productsExecutorClient) {
        this.productsExecutorClient = productsExecutorClient;
    }
}
