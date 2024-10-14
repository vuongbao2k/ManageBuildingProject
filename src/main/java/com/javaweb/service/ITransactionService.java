package com.javaweb.service;

import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.TransactionResponseDTO;

import java.util.List;

public interface ITransactionService {
    List<TransactionEntity> findByCodeAndCustomerId(String code, Long customerId);
    void addOrUpdateTransaction(TransactionDTO transactionDTO);
    public TransactionResponseDTO loadTransactionDetail(Long id);
}
