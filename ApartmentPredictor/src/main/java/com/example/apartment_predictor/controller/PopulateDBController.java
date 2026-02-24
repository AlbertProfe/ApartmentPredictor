package com.example.apartment_predictor.controller;

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
@RequestMapping("/api/v1/")
public class PopulateDBController {

    @Autowired
    PopulateDB populateDB;

    @GetMapping("/populateDB")
    public ResponseEntity<String> populateAll(@RequestParam int qty) {
        int createdQty = populateDB.populateAll(qty);
        if (createdQty >= (qty * 6))
            return ResponseEntity.ok("\nPopulated db: " + createdQty + " entities.");
        else
            return ResponseEntity.badRequest().body("Failed to populate db");
    }
}
