package com.example.apartment_predictor.controller;

import com.example.apartment_predictor.model.Reviewer;
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
@RequestMapping("api/v1/reviewers")
public class ReviewerRestController {

    @Autowired
    PopulateDB populateDB;

    @GetMapping("/populate")
    public ResponseEntity<String> populateReviewers(@RequestParam int qty) {
        List<Reviewer> reviewers = populateDB.populateReviewers(qty);
        if (reviewers.size() > 0)
            return ResponseEntity.ok("Populated reviewers: " + reviewers.size());
        else
            return ResponseEntity.badRequest().body("Failed to populate reviewers");
    }




}
