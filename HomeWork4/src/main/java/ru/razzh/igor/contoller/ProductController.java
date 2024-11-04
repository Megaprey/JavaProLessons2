package ru.razzh.igor.contoller;

import jakarta.transaction.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.razzh.igor.dto.TaskRequest;
import ru.razzh.igor.dto.error.ErrorResponse;
import ru.razzh.igor.entity.Product;
import ru.razzh.igor.service.ProductService;
import ru.razzh.igor.dto.ProductDto;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("v1/product")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable long id) {
        return productService.getById(id);
    }

    @GetMapping("/all")
    public List<ProductDto> getAllProductByUserId(@RequestParam long userId) {
        return productService.getAllProductByUserId(userId);
    }

    @PutMapping("/{idProduct}/balance")
    public void updateProductSumById(@PathVariable long idProduct, @RequestBody TaskRequest taskRequest) {
        productService.updateProductSumById(idProduct, taskRequest.getBalance());
    }

    @GetMapping("/{userId}/product")
    public ProductDto getProductByUserIdAndProductType(@PathVariable long userId, @RequestParam String productType) {
        return productService.getProductByUserIdAndProductType(userId, productType);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleProductNotFoundException(EmptyResultDataAccessException exception) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.name(), exception.getMessage());
    }


}
