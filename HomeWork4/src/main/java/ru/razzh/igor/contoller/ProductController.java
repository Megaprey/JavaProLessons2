package ru.razzh.igor.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.razzh.igor.user.Product;
import ru.razzh.igor.user.ProductDao;
import ru.razzh.igor.user.ProductService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/createTable")
    public String createProductTable() {
        productService.createpRroductTable();
        return "create Product table";
    }

    @GetMapping("/addProduct")
    public String addProduct(@RequestParam String accountNumber,
                             @RequestParam BigDecimal balance,
                             @RequestParam  String productType,
                             @RequestParam long userId) {
        productService.insertProduct(accountNumber, balance, productType, userId);
        return "add in table";
    }

    @GetMapping("/getById")
    public Product getById(@RequestParam long id) {
        return productService.getById(id);
    }

    @GetMapping("/getAllByUserId")
    public List<Product> getAllProductByUserId(@RequestParam long userId) {
        return productService.getAllProductByUserId(userId);
    }

}
