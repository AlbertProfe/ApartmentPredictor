package com.example.apartment_predictor.repository;

import com.example.apartment_predictor.model.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, String> {
}
