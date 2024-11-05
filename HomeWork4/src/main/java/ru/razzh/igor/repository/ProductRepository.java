package ru.razzh.igor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.razzh.igor.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public Product getById(long id);

    public List<Product> getAllByUserId(Long userId);

    @Modifying
    @Query(value = "update products p set p.balance = :balance where p.id = :id", nativeQuery = true)
    public void updateBalanceById(long id, BigDecimal balance);

    public Optional<Product> getByUserIdAndProductType(long userId, String productType);
}
