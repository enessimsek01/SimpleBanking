package com.eteration.simplebanking.dto;

import com.eteration.simplebanking.util.base.BaseDto;
import lombok.Data;

import java.util.List;

@Data
public class AccountDto extends BaseDto {
    private String owner;
    private String accountNumber;
    private Double balance;
    private List<TransactionDto> transactions;
}
