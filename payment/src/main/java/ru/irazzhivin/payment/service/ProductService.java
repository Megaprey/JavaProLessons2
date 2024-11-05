package ru.irazzhivin.payment.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.irazzhivin.payment.dto.ProductDto;
import ru.irazzhivin.payment.dto.TaskRequest;
import ru.irazzhivin.payment.error.IntegrationNotFoundErrorDto;
import ru.irazzhivin.payment.exception.IntegrationNotFoundException;
import ru.irazzhivin.payment.exception.PayException;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class ProductService {
    private final RestTemplate restTemplate;
    public  ProductService(@Qualifier(value = "product-client") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ProductDto getProductById(long id) {
        return restTemplate.getForObject("/v1/product/" + id, ProductDto.class);
    }

    public ProductDto getProductsByUserIdAndProductType(long id, String productType) {
        ProductDto[] products = restTemplate.getForObject("/v1/product/all?userId=" + id, ProductDto[].class);
        ProductDto productDto = Arrays.stream(products).filter(p -> p.productType().equals(productType)).findFirst()
                .orElseThrow(() -> {
                    IntegrationNotFoundErrorDto err = new IntegrationNotFoundErrorDto();
                    err.setErrorCode(HttpStatus.NOT_FOUND.name());
                    err.setErrorDescription("Не найден productType: " + productType);
                    IntegrationNotFoundException exception = new IntegrationNotFoundException("", err);
                    return exception;
                });
        return productDto;
    }

    public BigDecimal pay(long userId, String productType, BigDecimal sum) {
        ProductDto productDto = getProductsByUserIdAndProductType(userId, productType);
        if(productDto.balance().compareTo(sum) < 0) {
            String errMess = "У пользователя: " + userId + " на продукте: " + productType + " - недостаточно средств";
            System.out.println(errMess);
            throw new PayException(errMess);
        }
        BigDecimal currentBalance = productDto.balance();
        currentBalance = currentBalance.subtract(sum);
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setBalance(currentBalance);
        restTemplate.put("/v1/product/" + productDto.id() + "/balance", taskRequest);
        return currentBalance;
    }
}
