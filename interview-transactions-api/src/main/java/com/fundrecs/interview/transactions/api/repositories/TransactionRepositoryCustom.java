package com.fundrecs.interview.transactions.api.repositories;

import com.fundrecs.interview.transactions.api.entity.Transaction;

import java.util.List;

public interface TransactionRepositoryCustom {
    void upsert(final Transaction transaction);

    void saveAll(final List<Transaction> transaction);
}
