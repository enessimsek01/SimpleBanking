package com.eteration.simplebanking.dto;

import com.eteration.simplebanking.model.TransactionType;
import com.eteration.simplebanking.util.base.BaseDto;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionDto extends BaseDto {
    private String approvalCode;
    private Double amount;
    private AccountDto accountDto;
    private TransactionType transactionType;
}
