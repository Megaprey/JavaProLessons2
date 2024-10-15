package ru.razzh.igor.user;

import java.math.BigDecimal;

public class Product {
    private long id;
    private String accountNumber;
    private BigDecimal balance;
    private String productType;
    private long userId;

    public Product(long id, String accountNumber, BigDecimal balance, String productType, long userId) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.productType = productType;
        this.userId = userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getProductType() {
        return productType;
    }

    public long getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
