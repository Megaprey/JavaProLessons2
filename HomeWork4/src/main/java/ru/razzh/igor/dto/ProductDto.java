package ru.razzh.igor.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.razzh.igor.entity.User;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
public class ProductDto {
    public ProductDto(){}
    private long id;
    private String accountNumber;
    private BigDecimal balance;
    private String productType;
    private User user;
}
