package ru.irazzhivin.payment.dto;

import java.math.BigDecimal;

public class TaskRequest {

    private BigDecimal balance;

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "TaskRequest{" +
                "balance=" + balance +
                '}';
    }
}
