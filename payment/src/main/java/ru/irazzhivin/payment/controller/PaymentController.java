package ru.irazzhivin.payment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.irazzhivin.payment.dto.PayRequest;
import ru.irazzhivin.payment.dto.ProductDto;
import ru.irazzhivin.payment.dto.error.ErrorResponse;
import ru.irazzhivin.payment.error.IntegrationNotFoundErrorDto;
import ru.irazzhivin.payment.exception.IntegrationNotFoundException;
import ru.irazzhivin.payment.exception.PayException;
import ru.irazzhivin.payment.service.ProductService;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "v1/payment")
public class PaymentController {
    private final ProductService productService;

    public PaymentController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("product/{id}")
    public ProductDto getProductByUserId(@PathVariable long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/pay")
    public BigDecimal pay(@RequestBody PayRequest payRequest) {
        return productService.pay(payRequest.userId(), payRequest.productType(), payRequest.sum());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleProductNotFoundException(IntegrationNotFoundException exception) {
        IntegrationNotFoundErrorDto errorDto = exception.getIntegrationNotFoundErrorDto();
        String textMessage = errorDto.getErrorDescription();
        textMessage += " : " + errorDto;
        return new ErrorResponse(HttpStatus.NOT_FOUND.name(), textMessage);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlePayException(PayException exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.name(), exception.getMessage());
    }

}
