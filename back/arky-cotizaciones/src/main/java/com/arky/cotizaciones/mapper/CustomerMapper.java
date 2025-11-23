package com.arky.cotizaciones.mapper;

import org.springframework.stereotype.Component;

import com.arky.cotizaciones.dto.CustomerRequestDTO;
import com.arky.cotizaciones.dto.CustomerResponseDTO;
import com.arky.cotizaciones.model.Customer;

@Component
public class CustomerMapper {


    public CustomerResponseDTO toDto(Customer customer){
        CustomerResponseDTO dto =  new CustomerResponseDTO();
        dto.setCustomerId(customer.getCustomerId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        dto.setAddress(customer.getAddress());
        dto.setRut(customer.getRut());
        dto.setRubro(customer.getRubro());

        return dto;
    }




    public Customer toEntity(CustomerRequestDTO dto){
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());
        customer.setRut(dto.getRut());
        customer.setRubro(dto.getRubro());

        return customer;
    }


    

    public void updateDtoFromCustomer(CustomerRequestDTO dto, Customer customer){
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());
        customer.setRut(dto.getRut());
        customer.setRubro(dto.getRubro());
    }
}
