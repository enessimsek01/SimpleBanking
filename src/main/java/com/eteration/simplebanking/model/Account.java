package com.eteration.simplebanking.model;


import com.eteration.simplebanking.util.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

// This class is a place holder you can change the complete implementation
@Entity
@Table(name = "account")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Account extends BaseEntity implements Serializable {

    @Id
    @Column(name = "account_number", nullable = false, length = 100)
    private String accountNumber;

    @Column(name = "owner", nullable = false, length = 50)
    private String owner;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<DepositTransaction> depositTransactions;

    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<WithdrawalTransaction> withdrawalTransactions;

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
    }
}
