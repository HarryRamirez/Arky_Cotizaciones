package com.arky.cotizaciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arky.cotizaciones.model.Quotation;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation, Integer>{

	List<Quotation> findByCustomer_NameIgnoreCaseContaining(String name);

	Integer countByStateStateId(Integer stateId);


}
