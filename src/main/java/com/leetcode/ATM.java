package com.leetcode;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Банкомат.
 * Инициализируется набором купюр и умеет выдавать купюры для заданной суммы, либо отвечать отказом.
 * При выдаче купюры списываются с баланса банкомата.
 * Допустимые номиналы: 50₽, 100₽, 500₽, 1000₽, 5000₽.
 *
 * Другие валюты и номиналы должны легко добавляться разработчиками в будущем.
 * Многопоточные сценарии могут быть добавлены позже (например резервирование).
 */
public class ATM {
    Map<Currency, Map<Banknote, Integer>> banknotes;

    public ATM(final Map<Currency, Map<Banknote, Integer>> banknotes) {
        this.banknotes = new HashMap<>();

        banknotes.forEach((ck, cv) -> {
            var currencyBanknotes = new TreeMap<Banknote, Integer>(
                (o1, o2) -> Integer.compare(o2.getValue(),o1.getValue())
            );

            cv.forEach((b, v) -> {
                if (b.currency != ck) {
                    throw new RuntimeException("Invalid banknote");
                }
                if (v <= 0) {
                    throw new RuntimeException("Invalid param");
                }
                currencyBanknotes.put(b, v);
            });
            this.banknotes.put(ck, currencyBanknotes);
        });
    }

    public Map<Banknote, Integer> getMoney(Currency currency, int amount) {
        Map<Banknote, Integer> cash = new HashMap<>();
        var currencyBanknotes = banknotes.get(currency);
        if (currencyBanknotes == null || currencyBanknotes.isEmpty()) {
            throw new RuntimeException("Currency not found");
        }
        for (var banknote : currencyBanknotes.keySet()) {
            if (amount <= 0) {
                break;
            }
            if (banknote.value <= amount) {
                var count = Math.min(amount / banknote.value, currencyBanknotes.get(banknote));
                cash.put(banknote, count);
                amount -= count * banknote.value;
            }
        }
        if (amount > 0) {
            throw new RuntimeException("Нет доступных банкнот");
        }

        cash.forEach(
            (k, v) -> currencyBanknotes.put(k, currencyBanknotes.get(k) - v)
        );
        return cash;
    }

    public enum Banknote {
        FIVE_THOUSANDS_RUB(5000, Currency.RUB),
        ONE_THOUSANDS_RUB(1000, Currency.RUB),
        FIVE_HUNDRED_RUB(500, Currency.RUB),
        HUNDRED_RUB(100, Currency.RUB),
        FIFTY_RUB(50, Currency.RUB);

        private final int value;
        private final Currency currency;

        Banknote(final int i, Currency currency) {
            this.value = i;
            this.currency = currency;
        }

        public int getValue() {
            return value;
        }
    }

    public enum Currency {
        USD, RUB
    }
}