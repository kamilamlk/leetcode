package com.leetcode;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ATMTest {

    @Test
    void shouldReturnBanknotes() {

        var currencyBanknotes = new HashMap<ATM.Banknote, Integer>();
        currencyBanknotes.put(ATM.Banknote.FIVE_THOUSANDS_RUB, 1);
        currencyBanknotes.put(ATM.Banknote.ONE_THOUSANDS_RUB, 2); // 2
        currencyBanknotes.put(ATM.Banknote.FIVE_HUNDRED_RUB, 3); // 1
        currencyBanknotes.put(ATM.Banknote.HUNDRED_RUB, 4); // 1
        currencyBanknotes.put(ATM.Banknote.FIFTY_RUB, 5); // 1

        var banknotes = new HashMap<ATM.Currency, Map<ATM.Banknote, Integer>>();
        banknotes.put(ATM.Currency.RUB, currencyBanknotes);
        var amount = 2650;
        ATM atm = new ATM(banknotes);

        var result = atm.getMoney(ATM.Currency.RUB, amount);

        assertEquals(4, result.size());
        assertTrue(result.containsKey(ATM.Banknote.ONE_THOUSANDS_RUB));
        assertEquals(2, result.get(ATM.Banknote.ONE_THOUSANDS_RUB));

        assertTrue(result.containsKey(ATM.Banknote.FIVE_HUNDRED_RUB));
        assertEquals(1, result.get(ATM.Banknote.FIVE_HUNDRED_RUB));

        assertTrue(result.containsKey(ATM.Banknote.HUNDRED_RUB));
        assertEquals(1, result.get(ATM.Banknote.HUNDRED_RUB));

        assertTrue(result.containsKey(ATM.Banknote.FIFTY_RUB));
        assertEquals(1, result.get(ATM.Banknote.FIFTY_RUB));
    }

    // shouldThrowExceptionOnNotFoundBanknotes
    // shouldThrowExceptionOnNoBanknotes
    // shouldThrowExceptionOnEnoughBanknotes

}