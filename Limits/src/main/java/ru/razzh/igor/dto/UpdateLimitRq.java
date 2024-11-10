package ru.razzh.igor.dto;

import java.math.BigDecimal;

public record UpdateLimitRq(BigDecimal sum, long userId) {
}
