package com.fundrecs.interview.transactions.api.repositories;

import com.fundrecs.interview.transactions.api.entity.Transaction;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.fundrecs.interview.transactions.api.utils.TransactionsUtils.sum;

@Repository
public class TransactionRepositoryImpl implements TransactionRepositoryCustom {
    private static final Logger LOG = LoggerFactory.getLogger(TransactionRepositoryImpl.class);

    private final MongoTemplate mongoTemplate;

    @Autowired
    public TransactionRepositoryImpl(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * Save all transactions on MongoDB with upserts.
     *
     * @param transactions - transactions to be saved
     */
    @Override
    public void saveAll(final List<Transaction> transactions) {
        for (final Transaction transaction : transactions) {
            upsert(transaction);
        }
    }

    /**
     * Upsert for a {@link Transaction}.
     *
     * @param transaction - transaction to be updated
     */
    @Override
    public void upsert(final Transaction transaction) {
        final Transaction mdbTransaction = this.mongoTemplate.findById(transaction.getKey(), Transaction.class);
        if (mdbTransaction != null) {
            transaction.setAmount(sum(transaction.getAmount(), mdbTransaction.getAmount()));
            final Query query = new Query(Criteria.where("_id").is(transaction.getKey()));
            final Document doc = new Document();
            this.mongoTemplate.getConverter().write(transaction, doc);
            final Update update = Update.fromDocument(doc);
            this.mongoTemplate.upsert(query, update, "transactions");
            LOG.info("Aggregated transaction: '{}'", transaction.toTransactionDto().toString());
        } else {
            this.mongoTemplate.save(transaction);
        }
    }
}
