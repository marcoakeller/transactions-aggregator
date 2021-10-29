package com.fundrecs.interview.transactions.api.repositories;

import com.fundrecs.interview.transactions.api.entity.Transaction;
import com.fundrecs.interview.transactions.api.entity.Transaction.CompositeKey;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, CompositeKey>, TransactionRepositoryCustom {
}
