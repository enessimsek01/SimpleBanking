package com.eteration.simplebanking.model;


import com.eteration.simplebanking.util.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;


// This class is a place holder you can change the complete implementation
@MappedSuperclass
@SuperBuilder
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public abstract class Transaction extends BaseEntity implements Serializable {

    @Id
    @Column(name = "approval_code", nullable = false, length = 36)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String approvalCode;

    @Column(name = "amount")
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;

    @ManyToOne(cascade ={CascadeType.PERSIST,CascadeType.MERGE} ,fetch = FetchType.LAZY)
    @JoinColumn(name = "account",referencedColumnName = "account_number",nullable = false)
    private Account account;

    public abstract void transaction(Double amount);

    @Override
    public String toString() {
        return "Transaction{" +
                    "approvalCode=" + approvalCode +
                    ", date=" + getDate() +
                    ", amount=" + amount +
                    ", account=" + account +
                '}';
    }
}
