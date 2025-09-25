package com.leetcode.design.reconciliation;

import com.leetcode.design.payment.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class ReconciliationService {
    private final Map<String, Transaction> systemATransactions;
    private final Map<String, Transaction> systemBTransactions;
    private final Map<String, Mismatch> mismatches;

    public ReconciliationService() {
        this.systemATransactions = new ConcurrentHashMap<>();
        this.systemBTransactions = new ConcurrentHashMap<>();
        this.mismatches = new ConcurrentHashMap<>();
    }

    public void addFromSystemA(Transaction tx) {
        systemATransactions.putIfAbsent(tx.id(), tx);
        Transaction bTx = systemBTransactions.get(tx.id());
        reconcile(tx, bTx);
    }

    public void addFromSystemB(Transaction tx) {
        systemBTransactions.putIfAbsent(tx.id(), tx);
        Transaction aTx = systemATransactions.get(tx.id());
        reconcile(tx, aTx);
    }

    private void reconcile(Transaction t1, Transaction t2) {
        if (t2 == null) {
            mismatches.put(t1.id(), new Mismatch(t1.id()));
        } else if (Objects.equals(t1.currency(), t2.currency()) && Objects.equals(t1.amount(), t2.amount())) {
            mismatches.remove(t1.id());
        } else {
            mismatches.put(t1.id(), new Mismatch(t1.id())); // mismatch due to value difference
        }
    }

    public List<Mismatch> getMismatches() {
        return new ArrayList<>(mismatches.values());
    }
}