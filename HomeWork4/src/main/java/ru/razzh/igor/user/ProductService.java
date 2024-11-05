package ru.razzh.igor.user;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void createpRroductTable() {
        productDao.createpRroductTable();
    }

    public void insertProduct(String accountNumber, BigDecimal balance, String productType, long userId) {
        productDao.insertProduct(accountNumber, balance, productType, userId);
    }

    public void delete(Long id) {
        productDao.delete(id);
    }

    public Product getById(long id) {
        return productDao.getById(id);
    }

    public List<Product> getAllProductByUserId(long userId) {
        return productDao.getAllproductByUserId(userId);
    }

    public void updateProductSumById(long id, BigDecimal balance) {
        productDao.updateProductSumById(id, balance);
    }
    public Product getProductByUserIdAndProductType(long userId,String productType) {
        return productDao.getAllproductByUserId(userId).stream().filter(p -> p.getProductType().equals(productType)).findFirst().orElseThrow(() -> new EmptyResultDataAccessException(1));
    }
}
