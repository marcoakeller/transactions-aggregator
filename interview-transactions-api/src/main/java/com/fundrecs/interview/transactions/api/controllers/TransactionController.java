package com.fundrecs.interview.transactions.api.controllers;

import com.fundrecs.interview.transactions.api.entity.Transaction;
import com.fundrecs.interview.transactions.api.entity.Transaction.CompositeKey;
import com.fundrecs.interview.transactions.api.entity.TransactionDTO;
import com.fundrecs.interview.transactions.api.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.fundrecs.interview.transactions.api.utils.TransactionsUtils.summarizeTransactions;
import static org.springframework.http.ResponseEntity.ok;

/**
 * RestController for REST calls.
 */
@RestController
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    /**
     * Retrieve a {@link Transaction} by type and date.
     *
     * @param type - the transaction type
     * @param date - the transaction date
     * @return {@link Transaction}
     */
    @GetMapping("/transaction/{type}/{date}")
    public ResponseEntity<TransactionDTO> findByTypeAndDate(@PathVariable("type") final String type,
                                                            @PathVariable("date") final String date) {
        final Optional<Transaction> transactionOpt = this.transactionRepository
                .findById(new CompositeKey(type, date));

        return transactionOpt.map(transaction -> ok().body(transaction.toTransactionDto()))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Save list of transactions and aggregates the amount values by type and date.
     *
     * @param transactions - the transactions to be stored
     */
    @PostMapping("/transaction")
    public void saveTransactions(@RequestBody final List<Transaction> transactions) {
        final List<Transaction> result = summarizeTransactions(transactions);
        this.transactionRepository.saveAll(result);
    }
}