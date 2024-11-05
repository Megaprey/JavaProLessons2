package ru.irazzhivin.payment.exception;

import ru.irazzhivin.payment.error.IntegrationNotFoundErrorDto;

public class IntegrationNotFoundException extends RuntimeException {
    @Override
    public String toString() {
        return "IntegrationNotFoundException{" +
                "integrationNotFoundErrorDto=" + integrationNotFoundErrorDto +
                '}';
    }

    private IntegrationNotFoundErrorDto integrationNotFoundErrorDto;

    public IntegrationNotFoundException(String message) {
        super(message);
    }

    public IntegrationNotFoundException(String message, IntegrationNotFoundErrorDto integrationNotFoundErrorDto) {
        super(message);
        this.integrationNotFoundErrorDto = integrationNotFoundErrorDto;
    }

    public IntegrationNotFoundErrorDto getIntegrationNotFoundErrorDto() {
        return integrationNotFoundErrorDto;
    }
}
