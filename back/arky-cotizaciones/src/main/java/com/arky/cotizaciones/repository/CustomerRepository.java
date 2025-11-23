package com.arky.cotizaciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arky.cotizaciones.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	List<Customer> findByNameIgnoreCaseContaining(String name);

	boolean existsByEmail(String email);

}
