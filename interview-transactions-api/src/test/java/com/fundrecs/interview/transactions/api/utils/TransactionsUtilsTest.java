package com.fundrecs.interview.transactions.api.utils;

import com.fundrecs.interview.transactions.api.entity.Transaction;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TransactionsUtilsTest {

    @Test
    public void whenTransactionMatch_thenSumAmounts() {
        final Transaction t1 = new Transaction("11-12-2021", "credit", "1000.30");
        final Transaction t2 = new Transaction("11-12-2021", "credit", "2000.20");

        final List<Transaction> transactionList = new ArrayList<>(2);
        transactionList.add(t1);
        transactionList.add(t2);

        final List<Transaction> result = TransactionsUtils.summarizeTransactions(transactionList);

        assertEquals(1, result.size());
        assertEquals("3000.50", result.get(0).getAmount());
    }

    @Test
    public void whenTransactionsDifferentType_thenSumAmountsAreCorrect() {
        final Transaction t1 = new Transaction("11-12-2021", "credit", "1000.30");
        final Transaction t2 = new Transaction("11-12-2021", "credit", "2000.20");

        final Transaction t3 = new Transaction("10-12-2021", "debit", "10.30");
        final Transaction t4 = new Transaction("10-12-2021", "debit", "98.36");

        final Transaction t5 = new Transaction("09-12-2021", "debit", "100.00");

        final Transaction t6 = new Transaction("11-12-2021", "debit", "200.36");

        final List<Transaction> transactionList = new ArrayList<>(4);
        transactionList.add(t1);
        transactionList.add(t2);
        transactionList.add(t3);
        transactionList.add(t4);
        transactionList.add(t5);
        transactionList.add(t6);

        final List<Transaction> result = TransactionsUtils.summarizeTransactions(transactionList);

        Assertions.assertThat(result)
                .hasSize(4)
                .extracting(Transaction::getAmount)
                .containsExactlyInAnyOrder("3000.50", "108.66", "100.00", "200.36");
    }
}
