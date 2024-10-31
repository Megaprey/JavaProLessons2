package ru.irazzhivin.payment.dto;

import java.math.BigDecimal;

public record PayRequest(long userId, String productType, BigDecimal sum) {
}
