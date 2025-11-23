package com.arky.cotizaciones.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arky.cotizaciones.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	boolean existsByNameContainingIgnoreCase(String name);
	List<Product> findByNameContainingIgnoreCase(String name);

}
