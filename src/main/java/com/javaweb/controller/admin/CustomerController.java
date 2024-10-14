package com.javaweb.controller.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.StatusCode;
import com.javaweb.enums.TransactionType;
import com.javaweb.enums.buildingType;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.IUserService;
import com.javaweb.service.impl.TransactionService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller(value = "customerControllerOfAdmin")
public class CustomerController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/admin/customer-list", method = RequestMethod.GET)
    public ModelAndView getNews(@ModelAttribute("customerSearch")CustomerSearchRequest customerSearchRequest, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/customer/list");
        mav.addObject("staffmaps", userService.getStaff());
        DisplayTagUtils.of(request,customerSearchRequest);
        customerSearchRequest.setMaxPageItems(3);
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            Long staffId = SecurityUtils.getPrincipal().getId();
            customerSearchRequest.setStaffId(staffId);
        }
        mav.addObject("customers", customerService.searchCustomer(customerSearchRequest));
//        Page<CustomerSearchResponse> customerSearchResponsePage = customerService.searchCustomer(customerSearchRequest, PageRequest.of(customerSearchRequest.getPage()-1,customerSearchRequest.getMaxPageItems()));
        return mav;
    }

    @GetMapping(value = "admin/customer-edit")
    public ModelAndView addCustomer(@ModelAttribute("customerEdit") CustomerDTO customerDTO){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("customerEdit", customerDTO);
        mav.addObject("statusCode", StatusCode.statusCode());
        return mav;
    }

    @GetMapping(value = "admin/customer-edit-{id}")
    public ModelAndView addCustomer(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("statusCode", StatusCode.statusCode());
        //find customer by id
        CustomerDTO customerDTO = customerService.findById(id);
        mav.addObject("customerEdit", customerDTO);
        mav.addObject("transactionType", TransactionType.transactionType());
        // 2 gd, findbycodeandcustomerid
        mav.addObject("CSKHList", transactionService.findByCodeAndCustomerId("CSKH",id));
        mav.addObject("DDXList", transactionService.findByCodeAndCustomerId("DDX",id));
        return mav;
    }
}
