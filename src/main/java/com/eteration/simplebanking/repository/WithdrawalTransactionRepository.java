package com.eteration.simplebanking.repository;

import com.eteration.simplebanking.model.WithdrawalTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalTransactionRepository extends JpaRepository<WithdrawalTransaction, Long> {
}
