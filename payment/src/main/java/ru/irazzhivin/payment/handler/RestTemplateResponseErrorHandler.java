package ru.irazzhivin.payment.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import ru.irazzhivin.payment.error.IntegrationNotFoundErrorDto;
import ru.irazzhivin.payment.exception.IntegrationNotFoundException;

import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is4xxClientError()) {
            ObjectMapper mapper = new ObjectMapper();
            IntegrationNotFoundErrorDto errorDto = mapper.readValue(response.getBody(), IntegrationNotFoundErrorDto.class);
            throw new IntegrationNotFoundException("Object not found ", errorDto);
        }
    }
}
