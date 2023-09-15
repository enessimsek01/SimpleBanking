package com.eteration.simplebanking.services;


import com.eteration.simplebanking.dto.AccountDto;
import com.eteration.simplebanking.dto.AmountDto;
import com.eteration.simplebanking.dto.DepositTransactionDto;
import com.eteration.simplebanking.dto.WithdrawalTransactionDto;
import com.eteration.simplebanking.mapper.MapperUtil;
import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.util.InsufficientBalanceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

// This class is a place holder you can change the complete implementation

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final MapperUtil mapperUtil;
    private final TransactionService transactionService;

    public Account findAccount(String accountNumber){
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public AccountDto findAccountDto(String accountNumber){
        return mapperUtil.accountToDto(accountRepository.findByAccountNumber(accountNumber));
    }

    public DepositTransactionDto credit(String accountNumber, AmountDto amountDto){
        if (accountNumber == null) {
            throw new RuntimeException("Account number is empty.");
        }

        Account account = findAccount(accountNumber);

        if(account == null){
            throw new RuntimeException("Account not found.");
        }

        account.setBalance(account.getBalance() + amountDto.getAmount());
        accountRepository.save(account);

        return transactionService.saveDepositTransaction(account, amountDto.getAmount());
    }

    public WithdrawalTransactionDto debit(String accountNumber, AmountDto amountDto) throws InsufficientBalanceException {
        Account account = findAccount(accountNumber);

        if(account == null){
            return null;
        }
        if (account.getBalance() < amountDto.getAmount()) {
            throw new InsufficientBalanceException("Insufficient Balance");

        }

        account.setBalance(account.getBalance() - amountDto.getAmount());
        accountRepository.save(account);

        return transactionService.saveWithdrawalTransactionDto(account, amountDto.getAmount());

    }
}
