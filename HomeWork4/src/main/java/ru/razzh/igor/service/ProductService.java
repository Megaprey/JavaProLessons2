package ru.razzh.igor.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.razzh.igor.dto.ProductDto;
import ru.razzh.igor.repository.ProductRepository;
import ru.razzh.igor.entity.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService {
    ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ProductDto getById(long id) {
        return productToDto(repository.getById(id));
    }

    public List<ProductDto> getAllProductByUserId(long userId) {
        List<ProductDto> productDtoList = new ArrayList<>();
        repository.getAllByUserId(userId).stream().forEach(product -> productDtoList.add(productToDto(product)));
        return productDtoList;
    }

    @Transactional
    public void updateProductSumById(long id, BigDecimal balance) {
        repository.updateBalanceById(id, balance);
    }
    public ProductDto getProductByUserIdAndProductType(long userId, String productType) {
        return productToDto(repository.getByUserIdAndProductType(userId, productType).orElseThrow(() -> new EmptyResultDataAccessException(1)));
    }

    private ProductDto productToDto(Product product) {
        return new ProductDto(product.getId(), product.getAccountNumber(), product.getBalance(),
                product.getProductType(), product.getUser());
    }

}
