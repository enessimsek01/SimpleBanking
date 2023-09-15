package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.dto.AccountDto;
import com.eteration.simplebanking.dto.AmountDto;
import com.eteration.simplebanking.dto.TransactionDto;
import com.eteration.simplebanking.util.InsufficientBalanceException;
import com.eteration.simplebanking.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// This class is a place holder you can change the complete implementation

@RestController
@RequestMapping("/account/v1")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable String accountNumber) {
        return new ResponseEntity<>(
                accountService.findAccountDto(accountNumber), HttpStatus.OK
        );
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<Object> credit(@PathVariable String accountNumber, @RequestBody AmountDto amountDto) {
        return new ResponseEntity<>(
                accountService.credit(accountNumber, amountDto), HttpStatus.OK
        );
    }
    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<Object> debit(@PathVariable String accountNumber, @RequestBody AmountDto amountDto)
            throws InsufficientBalanceException {
        return new ResponseEntity<>(
                accountService.debit(accountNumber, amountDto), HttpStatus.OK
        );
    }
}