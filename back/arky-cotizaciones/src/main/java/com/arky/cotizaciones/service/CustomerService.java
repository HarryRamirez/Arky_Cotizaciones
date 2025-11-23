package com.arky.cotizaciones.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.arky.cotizaciones.dto.CustomerRequestDTO;
import com.arky.cotizaciones.dto.CustomerResponseDTO;




@Service
public interface CustomerService {


    List<CustomerResponseDTO> getAll();

    CustomerResponseDTO getByCustomer(int customerId);

    CustomerResponseDTO saveCustomer(CustomerRequestDTO dto);

    CustomerResponseDTO updateCustomer(int customerId, CustomerRequestDTO dto);

    void deleteCustomer(int customerId);

    List<CustomerResponseDTO> searchCustomer(String name);

    ByteArrayInputStream exportCustomersToExcel();


}
