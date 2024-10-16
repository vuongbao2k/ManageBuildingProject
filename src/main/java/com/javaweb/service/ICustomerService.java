package com.javaweb.service;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {
    List<CustomerSearchResponse> searchCustomer(CustomerSearchRequest customerSearchRequest);
    Page<CustomerSearchResponse> searchCustomer(CustomerSearchRequest customerSearchRequest, Pageable pageable);
    void createOrUpdate(CustomerDTO customerDTO);
    CustomerDTO findById(Long customerId);
    void deleteCustomer(List<Long> customerIds);
    ResponseDTO listStaff(Long customerId);
    void addAssignmentCustomerEntity(AssignmentCustomerDTO assignmentCustomerDTO);
}
