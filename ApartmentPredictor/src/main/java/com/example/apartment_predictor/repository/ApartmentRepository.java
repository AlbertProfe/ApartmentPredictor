package com.example.apartment_predictor.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.example.apartment_predictor.model.Apartment;

public interface ApartmentRepository extends CrudRepository<Apartment, String>, PagingAndSortingRepository<Apartment, String>, JpaSpecificationExecutor<Apartment> {
}
