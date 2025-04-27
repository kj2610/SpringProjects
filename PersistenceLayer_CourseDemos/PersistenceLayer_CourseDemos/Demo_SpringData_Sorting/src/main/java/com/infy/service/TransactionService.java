package com.infy.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.infy.dto.TransactionDTO;
import com.infy.exception.InfyBankException;

public interface TransactionService {

	public List<TransactionDTO> getAllTransaction(Sort sort) throws InfyBankException;

	public List<TransactionDTO> getAllTransactionByTransactionDateAfter(LocalDate transactionDate, Pageable pageable) throws InfyBankException;

}
