package com.example.apartment_predictor.controller;

import com.example.apartment_predictor.model.Review;
import com.example.apartment_predictor.model.School;
import com.example.apartment_predictor.utils.PopulateDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/reviews")
public class ReviewRestController {

    @Autowired
    PopulateDB populateDB;

    @GetMapping("/populate")
    public ResponseEntity<String> populateSchools(@RequestParam int qty) {
        List<Review> reviews = populateDB.populatePlainReviews(qty);
        if (reviews.size() > 0)
            return ResponseEntity.ok("Populated reviews: " + reviews.size());
        else
            return ResponseEntity.badRequest().body("Failed to populate reviews");
    }
}
