package com.eteration.simplebanking.mapper;

import com.eteration.simplebanking.dto.AccountDto;
import com.eteration.simplebanking.dto.DepositTransactionDto;
import com.eteration.simplebanking.dto.TransactionDto;
import com.eteration.simplebanking.dto.WithdrawalTransactionDto;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperUtil {

    public AccountDto accountToDto(Account account) {
        AccountDto accountDto = new AccountDto();

        accountDto.setDate(account.getDate());
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setOwner(account.getOwner());
        accountDto.setBalance(account.getBalance());

        List<TransactionDto> transactionDtoList = new ArrayList<>();
        transactionDtoList.addAll(withdrawalTransactionsToDtoList(account.getWithdrawalTransactions()));
        transactionDtoList.addAll(depositTransactionsToDtoList(account.getDepositTransactions()));
        accountDto.setTransactions(transactionDtoList);

        return accountDto;
    }

    public AccountDto accountToDtoWithoutTransactions(Account account) {
        AccountDto accountDto = new AccountDto();

        accountDto.setDate(account.getDate());
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setOwner(account.getOwner());
        accountDto.setBalance(account.getBalance());

        return accountDto;
    }



    public List<TransactionDto> depositTransactionsToDtoList(List<DepositTransaction> transactions) {
        return transactions.stream().map(this::depositTransactionToDto).collect(Collectors.toList());
    }

    public List<TransactionDto> withdrawalTransactionsToDtoList(List<WithdrawalTransaction> transactions) {
        return transactions.stream().map(this::withdrawalTransactionToDto).collect(Collectors.toList());
    }

    public DepositTransactionDto depositTransactionToDto(DepositTransaction transaction) {
        DepositTransactionDto transactionDto = new DepositTransactionDto();

        transactionDto.setApprovalCode(transaction.getApprovalCode());
        transactionDto.setDate(transaction.getDate());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setTransactionType(transaction.getTransactionType());

        return transactionDto;
    }

    public WithdrawalTransactionDto withdrawalTransactionToDto(WithdrawalTransaction transaction) {
        WithdrawalTransactionDto transactionDto = new WithdrawalTransactionDto();

        transactionDto.setApprovalCode(transaction.getApprovalCode());
        transactionDto.setDate(transaction.getDate());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setTransactionType(transaction.getTransactionType());

        return transactionDto;
    }

    public DepositTransactionDto depositTransactionToDtoWithAccountDto(DepositTransaction transaction) {
        DepositTransactionDto transactionDto = new DepositTransactionDto();

        transactionDto.setApprovalCode(transaction.getApprovalCode());
        transactionDto.setDate(transaction.getDate());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setTransactionType(transaction.getTransactionType());
        transactionDto.setAccountDto(accountToDtoWithoutTransactions(transaction.getAccount()));

        return transactionDto;
    }

    public WithdrawalTransactionDto withdrawalTransactionToDtoWithAccountDto(WithdrawalTransaction transaction) {
        WithdrawalTransactionDto transactionDto = new WithdrawalTransactionDto();

        transactionDto.setApprovalCode(transaction.getApprovalCode());
        transactionDto.setDate(transaction.getDate());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setTransactionType(transaction.getTransactionType());
        transactionDto.setAccountDto(accountToDtoWithoutTransactions(transaction.getAccount()));

        return transactionDto;
    }
}
