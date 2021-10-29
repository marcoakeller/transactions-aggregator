package com.fundrecs.interview.transactions.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Document(collection = "transactions")
@AllArgsConstructor
@NoArgsConstructor
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1250079262394601646L;

    @Indexed
    @Id
    private CompositeKey key;
    private String amount;

    public Transaction(@JsonProperty("date") final String date,
                       @JsonProperty("type") final String type, @JsonProperty("amount") final String amount) {
        this.key = new CompositeKey(type, date);
        this.amount = amount;
    }

    public TransactionDTO toTransactionDto() {
        return new TransactionDTO(this.key.date, this.key.type, this.amount);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Transaction that = (Transaction) o;
        return this.key.equals(that.key) && this.amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.key, this.amount);
    }

    @Getter
    @Setter
    public static class CompositeKey implements Serializable {

        private static final long serialVersionUID = -7387822619817220161L;
        private String date;
        private String type;

        public CompositeKey(final String type, final String date) {
            this.date = date;
            this.type = type;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final CompositeKey key = (CompositeKey) o;
            return this.date.equals(key.date) && this.type.equals(key.type);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.date, this.type);
        }
    }
}
