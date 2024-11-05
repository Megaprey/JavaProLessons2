package ru.irazzhivin.payment.config;

import java.time.Duration;

public class RestTemplateProperties {
    private String url;
    private Duration connectTimeout;
    private Duration readTimeout;

    public RestTemplateProperties(String url, Duration connectTimeout, Duration readTimeout) {
        this.url = url;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
    }

    public String getUrl() {
        return url;
    }

    public Duration getConnectTimeout() {
        return connectTimeout;
    }

    public Duration getReadTimeout() {
        return readTimeout;
    }
}
