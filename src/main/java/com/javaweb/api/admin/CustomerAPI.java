package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.TransactionResponseDTO;
import com.javaweb.service.impl.CustomerService;
import com.javaweb.service.impl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerAPI {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public void createCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.createOrUpdate(customerDTO);
    }

    @PutMapping({"/{customerId}"})
    public void updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Long customerId){
        customerService.createOrUpdate(customerDTO);
    }

    @DeleteMapping
    public void deleteCustomer(@RequestBody List<Long> customerIds){
        if(customerIds != null && customerIds.size()>0){
            customerService.deleteCustomer(customerIds);
        }
    }

    @GetMapping("/{id}/staff")
    public ResponseDTO loafStaff(@PathVariable Long id){
        ResponseDTO result = customerService.listStaff(id);
        return result;
    }

    @PostMapping("/transaction")
    public void createTransaction(@RequestBody TransactionDTO transactionDTO){
        transactionService.addOrUpdateTransaction(transactionDTO);
    }

    @GetMapping("/{transactionId}/details")
    public TransactionResponseDTO loadTransactionDetail(@PathVariable Long transactionId){
        return transactionService.loadTransactionDetail(transactionId);
    }

    @PostMapping("/assignment")
    public void updateAssignmentCustomer(@RequestBody AssignmentCustomerDTO assignmentCustomerDTO) {
        customerService.addAssignmentCustomerEntity(assignmentCustomerDTO);
    }
}
