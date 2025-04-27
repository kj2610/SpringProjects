package com.infy.service;

import java.time.LocalDate;
import java.util.List;

import com.infy.dto.TransactionDTO;
import com.infy.exception.InfyBankException;

public interface TransactionService {
	public List<TransactionDTO> getAllTransaction(Integer pageNo, Integer pageSize) throws InfyBankException;

	public List<TransactionDTO> getAllTransactionByTransactionDateAfter(LocalDate transactionDate, Integer pageNo,
			Integer pageSize) throws InfyBankException;
}