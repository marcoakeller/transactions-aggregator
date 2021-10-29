package com.fundrecs.interview.transactions.api.utils;

import com.fundrecs.interview.transactions.api.entity.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class TransactionsUtils {

    /**
     * Method to aggregate all amounts based on {@link Transaction.CompositeKey}
     *
     * @param transactions - {@link List} of {@link Transaction}
     * @return summarized {@link List} of {@link Transaction}
     */
    public static List<Transaction> summarizeTransactions(final List<Transaction> transactions) {
        return new ArrayList<>(transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getKey,
                        Collectors.reducing(
                                new Transaction(null, "0"),
                                (a, b) -> new Transaction(b.getKey(), sum(a.getAmount(), b.getAmount())))))
                .values());
    }

    public static String sum(final String value1, final String value2) {
        return new BigDecimal(value1).add(new BigDecimal(value2)).toString();
    }

}
