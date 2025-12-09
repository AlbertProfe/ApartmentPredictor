package com.example.apartment_predictor.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.apartment_predictor.model.Apartment;

public interface ApartmentRepository extends CrudRepository<Apartment, String> {
}
