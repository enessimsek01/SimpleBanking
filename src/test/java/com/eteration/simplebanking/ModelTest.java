package com.eteration.simplebanking;



import static org.junit.jupiter.api.Assertions.assertTrue;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.util.InsufficientBalanceException;
import com.eteration.simplebanking.model.WithdrawalTransaction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ModelTest {

	@Test
	public void testCreateAccountAndSetBalance0() {
		Account account = new Account("Kerem Karaca", "17892");
		assertTrue(account.getOwner().equals("Kerem Karaca"));
		assertTrue(account.getAccountNumber().equals("17892"));
		assertTrue(account.getBalance() == 0);
	}

	@Test
	public void testDepositIntoBankAccount() {
		Account account = new Account("Demet Demircan", "9834");
		DepositTransaction depositTransaction = new DepositTransaction();
		depositTransaction.setAmount(100.0);
		List<DepositTransaction> depositTransactionList = new ArrayList<>();
		depositTransactionList.add(depositTransaction);
		account.setDepositTransactions(depositTransactionList);
		assertTrue(account.getBalance() == 100);
	}

	@Test
	public void testWithdrawFromBankAccount() throws InsufficientBalanceException {
		Account account = new Account("Demet Demircan", "9834");
		DepositTransaction depositTransaction = new DepositTransaction();
		depositTransaction.setAmount(100.0);
		List<DepositTransaction> depositTransactionList = new ArrayList<>();
		depositTransactionList.add(depositTransaction);
		account.setDepositTransactions(depositTransactionList);
		assertTrue(account.getBalance() == 100);
		WithdrawalTransaction withdrawalTransaction = new WithdrawalTransaction();
		withdrawalTransaction.setAmount(50.0);
		List<WithdrawalTransaction> withdrawalTransactionList = new ArrayList<>();
		withdrawalTransactionList.add(withdrawalTransaction);
		account.setWithdrawalTransactions(withdrawalTransactionList);
		assertTrue(account.getBalance() == 50);
	}

	@Test
	public void testWithdrawException() {
		Assertions.assertThrows( InsufficientBalanceException.class, () -> {
			Account account = new Account("Demet Demircan", "9834");
			DepositTransaction depositTransaction = new DepositTransaction();
			depositTransaction.setAmount(100.0);
			List<DepositTransaction> depositTransactionList = new ArrayList<>();
			depositTransactionList.add(depositTransaction);
			account.setDepositTransactions(depositTransactionList);
			WithdrawalTransaction withdrawalTransaction = new WithdrawalTransaction();
			withdrawalTransaction.setAmount(500.0);
			List<WithdrawalTransaction> withdrawalTransactionList = new ArrayList<>();
			withdrawalTransactionList.add(withdrawalTransaction);
			account.setWithdrawalTransactions(withdrawalTransactionList);
		  });

	}
	
	@Test
	public void testTransactions() throws InsufficientBalanceException {
//		 Create account
		Account account = new Account("Canan Kaya", "1234");
		assertTrue(account.getDepositTransactions().isEmpty());
		assertTrue(account.getWithdrawalTransactions().isEmpty());
		// Deposit Transaction

		DepositTransaction depositTrx = new DepositTransaction(100.0);
		assertTrue(depositTrx.getDate() != null);
		List<DepositTransaction> depositTransactions = new ArrayList<>();
		depositTransactions.add(depositTrx);
		account.setDepositTransactions(depositTransactions);
		assertTrue(account.getBalance() == 100);
		assertTrue(account.getDepositTransactions().size() == 1);

		// Withdrawal Transaction
		WithdrawalTransaction withdrawalTrx = new WithdrawalTransaction(60.0);
		assertTrue(withdrawalTrx.getDate() != null);
		List<WithdrawalTransaction> withdrawalTransactions = new ArrayList<>();
		withdrawalTransactions.add(withdrawalTrx);
		account.setWithdrawalTransactions(withdrawalTransactions);
		assertTrue(account.getBalance() == 40.0);
		assertTrue(account.getWithdrawalTransactions().size() == 1);
		assertTrue(account.getDepositTransactions().size() == 1);

	}


}
