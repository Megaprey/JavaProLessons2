package ru.razzh.igor.contoller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.razzh.igor.dto.TaskRequest;
import ru.razzh.igor.dto.error.ErrorResponse;
import ru.razzh.igor.user.Product;
import ru.razzh.igor.user.ProductService;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("v1/product")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/create")
    public String createProductTable() {
        productService.createpRroductTable();
        return "create Product table";
    }

    @GetMapping("/add")
    public String addProduct(@RequestParam String accountNumber,
                             @RequestParam BigDecimal balance,
                             @RequestParam  String productType,
                             @RequestParam long userId) {
        productService.insertProduct(accountNumber, balance, productType, userId);
        return "add in table";
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable long id) {
        return productService.getById(id);
    }

    @GetMapping("/all")
    public List<Product> getAllProductByUserId(@RequestParam long userId) {
        return productService.getAllProductByUserId(userId);
    }

    @PutMapping("/{idProduct}/balance")
    public void updateProductSumById(@PathVariable long idProduct, @RequestBody TaskRequest taskRequest) {
        productService.updateProductSumById(idProduct, taskRequest.getBalance());
    }

    @GetMapping("/{userId}/product")
    public Product getProductByUserIdAndProductType(@PathVariable long userId, @RequestParam String productType) {
        return productService.getProductByUserIdAndProductType(userId, productType);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleProductNotFoundException(EmptyResultDataAccessException exception) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.name(), exception.getMessage());
    }


}
