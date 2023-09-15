package com.eteration.simplebanking.services;

import com.eteration.simplebanking.dto.DepositTransactionDto;
import com.eteration.simplebanking.dto.TransactionDto;
import com.eteration.simplebanking.dto.WithdrawalTransactionDto;
import com.eteration.simplebanking.mapper.MapperUtil;
import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.repository.DepositTransactionRepository;
import com.eteration.simplebanking.repository.WithdrawalTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
@RequiredArgsConstructor
public class TransactionService {

    private final DepositTransactionRepository depositTransactionRepository;
    private final WithdrawalTransactionRepository withdrawalTransactionRepository;
    private final MapperUtil mapperUtil;

    public DepositTransactionDto saveDepositTransaction(Account account, Double amount) {
        DepositTransaction depositTransaction = new DepositTransaction();

        depositTransaction.setTransactionType(TransactionType.DepositTransaction);
        depositTransaction.setAmount(amount);
        depositTransaction.setAccount(account);

        return mapperUtil.depositTransactionToDto(depositTransactionRepository.save(depositTransaction));
    }

    public WithdrawalTransactionDto saveWithdrawalTransactionDto(Account account, Double amount) {
        WithdrawalTransaction withdrawalTransaction = new WithdrawalTransaction();

        withdrawalTransaction.setTransactionType(TransactionType.WithdrawalTransaction);
        withdrawalTransaction.setAmount(amount);
        withdrawalTransaction.setAccount(account);

        return mapperUtil.withdrawalTransactionToDto(withdrawalTransactionRepository.save(withdrawalTransaction));
    }
}
