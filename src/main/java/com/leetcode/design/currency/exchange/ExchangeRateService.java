package com.leetcode.design.currency.exchange;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface ExchangeRateService {
    BigDecimal convertTo(BigDecimal amount,
                         Currency fromCurrency,
                         Currency toCurrency);
}
