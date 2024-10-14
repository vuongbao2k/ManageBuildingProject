package com.javaweb.converter;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {
    private ModelMapper modelMapper;
    public TransactionConverter(ModelMapper modelMapper) {this.modelMapper = modelMapper;}
    public TransactionDTO toTransactionDTO(TransactionEntity transactionEntity){
        return modelMapper.map(transactionEntity, TransactionDTO.class);
    }
    public TransactionEntity toTransactionEntity(TransactionDTO transactionDTO){
        return modelMapper.map(transactionDTO, TransactionEntity.class);
    }
}
