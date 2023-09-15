package com.eteration.simplebanking.repository;


import com.eteration.simplebanking.model.DepositTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositTransactionRepository extends JpaRepository<DepositTransaction, Long> {
}
