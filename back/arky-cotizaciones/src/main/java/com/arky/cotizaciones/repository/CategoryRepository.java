package com.arky.cotizaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arky.cotizaciones.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
