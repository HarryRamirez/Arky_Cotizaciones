package com.arky.cotizaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arky.cotizaciones.model.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer>{

}
