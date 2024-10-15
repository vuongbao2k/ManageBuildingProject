package com.javaweb.service.impl;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.converter.CustomerConverter;
import com.javaweb.converter.CustomerSearchBuilderConverter;
import com.javaweb.converter.CustomerSearchResponseConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private CustomerSearchBuilderConverter customerSearchBuilderConverter;
    @Autowired
    private CustomerConverter customerConverter;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerSearchResponseConverter customerSearchResponseConverter;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<CustomerSearchResponse> searchCustomer(CustomerSearchRequest customerSearchRequest) {
        CustomerSearchBuilder customerSearchBuilder = customerSearchBuilderConverter.toCustomerSearchBuilder(customerSearchRequest);
        List<CustomerEntity> list = customerRepository.searchCustomer(customerSearchBuilder);
        List<CustomerSearchResponse> responseList = new ArrayList<>();
        for(CustomerEntity customerEntity : list) {
            CustomerSearchResponse customerSearchResponse = customerSearchResponseConverter.toCustomerSearchResponse(customerEntity);
            responseList.add(customerSearchResponse);
        }
        return responseList;
    }

    @Override
    public Page<CustomerSearchResponse> searchCustomer(CustomerSearchRequest customerSearchRequest, Pageable pageable) {
        CustomerSearchBuilder customerSearchBuilder = customerSearchBuilderConverter.toCustomerSearchBuilder(customerSearchRequest);
        Page<CustomerEntity> customers = customerRepository.searchCustomer(customerSearchBuilder, pageable);
        List<CustomerEntity> customerEntities = customers.getContent();
        List<CustomerSearchResponse> result = new ArrayList<>();
        for (CustomerEntity customerEntity : customerEntities) {
            CustomerSearchResponse customerSearchResponse = customerSearchResponseConverter.toCustomerSearchResponse(customerEntity);
            result.add(customerSearchResponse);
        }
        return new PageImpl<CustomerSearchResponse>(result, pageable, result.size());
    }

    @Override
    public void createOrUpdate(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerConverter.toCustomerEntity(customerDTO);
        if(customerEntity.getId()!=null) {
//            customerEntity.setCreatedBy(customerDTO.getModifiedBy());
//            customerEntity.setCreatedDate(customerDTO.getModifiedDate());
            CustomerEntity oldCustomerEntity = customerRepository.getOne(customerEntity.getId());
            customerEntity.setCreatedBy(oldCustomerEntity.getCreatedBy());
            customerEntity.setCreatedDate(oldCustomerEntity.getCreatedDate());
        }
        customerEntity.setActive(true);
        customerRepository.save(customerEntity);
    }

    @Override
    public CustomerDTO findById(Long customerId) {
        CustomerEntity customerEntity = customerRepository.getOne(customerId);
        return customerConverter.toCustomerDTO(customerEntity);
    }

    @Override
    public void deleteCustomer(List<Long> customerIds) {
        for(Long customerId : customerIds) {
            CustomerEntity customerEntity = customerRepository.getOne(customerId);
            customerEntity.setActive(false);
            customerRepository.save(customerEntity);
        }
    }

    @Override
    public ResponseDTO listStaff(Long customerId) {
        CustomerEntity customerEntity = customerRepository.findById(customerId).get();
        List<UserEntity> staff = userRepository.findByStatusAndRoles_Code(1,"STAFF");
        List<UserEntity> staffAssignment = customerEntity.getUserEntities();
        List<StaffResponseDTO> staffResponseDTOList = new ArrayList<StaffResponseDTO>();
        ResponseDTO responseDTO = new ResponseDTO();
        for(UserEntity it : staff){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(it.getId());
            staffResponseDTO.setFullName(it.getFullName());
            if(staffAssignment.contains(it)){
                staffResponseDTO.setChecked("checked");
            }
            else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOList.add(staffResponseDTO);
        }
        responseDTO.setData(staffResponseDTOList);
        responseDTO.setMessage("success");
        return responseDTO;
    }

    @Override
    public void addAssignmentCustomerEntity(AssignmentCustomerDTO assignmentCustomerDTO) {
        CustomerEntity customerEntity = customerRepository.findById(assignmentCustomerDTO.getCustomerId()).get();
        List<UserEntity> userEntities = userRepository.findByIdIn(assignmentCustomerDTO.getStaffs());
        customerEntity.setUserEntities(userEntities);
        customerRepository.save(customerEntity);
    }
}
