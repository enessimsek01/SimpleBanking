package com.eteration.simplebanking.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

// This class is a place holder you can change the complete implementation
@Entity
@Table(name = "deposit_transaction")
@Getter @Setter @NoArgsConstructor
public class DepositTransaction extends Transaction {

    @Override
    public void transaction(Double amount) {

    }

    @PrePersist
    protected void onCreate() {
        setDate(new Date());
        setTransactionType(TransactionType.DepositTransaction);
    }
}
