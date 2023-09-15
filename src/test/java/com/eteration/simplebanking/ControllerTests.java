package com.eteration.simplebanking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.eteration.simplebanking.controller.AccountController;
import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.dto.AccountDto;
import com.eteration.simplebanking.dto.AmountDto;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.util.InsufficientBalanceException;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.services.AccountService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class ControllerTests  {

    @Spy
    @InjectMocks
    private AccountController controller;
 
    @Mock
    private AccountService service;

    
    @Test
    public void givenId_Credit_thenReturnJson()
    throws Exception {
        
        Account account = new Account("Kerem Karaca", "17892");

        AmountDto amountDto = new AmountDto();
        amountDto.setAmount(1000.0);

        doReturn(account).when(service).findAccount( "17892");
        ResponseEntity<Object> result = controller.credit("17892", amountDto);
        verify(service, times(1)).findAccount("17892");
        assertEquals("OK", result.getBody());
    }

    @Test
    public void givenId_CreditAndThenDebit_thenReturnJson()
    throws Exception {
        
        Account account = new Account("Kerem Karaca", "17892");

        AmountDto amountDto = new AmountDto();
        amountDto.setAmount(1000.0);

        doReturn(account).when(service).findAccount( "17892");
        ResponseEntity<Object> creditResult = controller.credit("17892", amountDto);
        amountDto.setAmount(50.0);
        ResponseEntity<Object> debitResult = controller.debit("17892", amountDto);
        verify(service, times(2)).findAccount("17892");
        assertEquals(HttpStatus.OK, creditResult.getStatusCode());
        assertEquals(HttpStatus.OK, debitResult.getStatusCode());
        assertEquals(950.0, account.getBalance(),0.001);
    }

    @Test
    public void givenId_CreditAndThenDebitMoreGetException_thenReturnJson()
    throws Exception {
        Assertions.assertThrows( InsufficientBalanceException.class, () -> {
            Account account = new Account("Kerem Karaca", "17892");

            AmountDto amountDto = new AmountDto();
            amountDto.setAmount(1000.0);


            doReturn(account).when(service).findAccount( "17892");
            ResponseEntity<Object> creditResult = controller.credit("17892", amountDto);
            assertEquals(HttpStatus.OK, creditResult.getStatusCode());
            assertEquals(1000.0, account.getBalance(),0.001);
            verify(service, times(1)).findAccount("17892");

            amountDto.setAmount(5000.0);

            ResponseEntity<Object> debit = controller.debit("17892", amountDto);
        });
    }

    @Test
    public void givenId_GetAccount_thenReturnJson()
    throws Exception {
        
        Account account = new Account("Kerem Karaca", "17892");

        doReturn(account).when(service).findAccount( "17892");
        ResponseEntity<AccountDto> result = controller.getAccount( "17892");
        verify(service, times(1)).findAccount("17892");
        assertEquals(account, result.getBody());
    }



}
