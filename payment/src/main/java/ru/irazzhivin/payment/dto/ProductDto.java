package ru.irazzhivin.payment.dto;

import java.math.BigDecimal;

public record ProductDto(long id,
        String accountNumber,
        BigDecimal balance,
        String productType,
        long userId) {
    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", productType='" + productType + '\'' +
                ", userId=" + userId +
                '}';
    }
}
