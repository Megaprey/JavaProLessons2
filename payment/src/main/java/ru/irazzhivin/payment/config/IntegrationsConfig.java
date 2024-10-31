package ru.irazzhivin.payment.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.irazzhivin.payment.handler.RestTemplateResponseErrorHandler;

@Configuration
public class IntegrationsConfig {
    RestTemplateResponseErrorHandler restTemplateResponseErrorHandler;

    public IntegrationsConfig(RestTemplateResponseErrorHandler restTemplateResponseErrorHandler) {
        this.restTemplateResponseErrorHandler = restTemplateResponseErrorHandler;
    }

    @Bean("product-client")
    public RestTemplate productsRestTemplate(ExecutorsProperties executorsProperties) {
        RestTemplateProperties productsExecutorClient = executorsProperties.getProductsExecutorClient();
        return new RestTemplateBuilder()
                .rootUri(productsExecutorClient.getUrl())
                .setConnectTimeout(productsExecutorClient.getConnectTimeout())
                .setReadTimeout(productsExecutorClient.getReadTimeout())
//                .errorHandler(restTemplateResponseErrorHandler)
                .build();
    }
}
