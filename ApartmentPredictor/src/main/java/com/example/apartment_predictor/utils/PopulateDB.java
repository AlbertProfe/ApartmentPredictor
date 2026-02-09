package com.example.apartment_predictor.utils;

import com.example.apartment_predictor.model.Apartment;
import com.example.apartment_predictor.model.FurnishingStatus;
import com.example.apartment_predictor.service.ApartmentService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class PopulateDB {

    @Autowired
    ApartmentService apartmentService;

    Faker faker = new Faker();

    public int populateApartments(int qty){
        int qtyAdded = 0;

        Random random = new Random();

        for (int i = 0; i < qty; i++){

            Apartment apartment = new Apartment();
            apartment.setId(faker.numerify(faker.number().digits(5)));
            apartment.setName("Casa en " + faker.address().cityName());
            apartment.setArea(random.nextInt(190) + 30);
            apartment.setStories(random.nextInt(2) + 1);
            apartment.setBedrooms(random.nextInt(4) + 1);
            apartment.setBathrooms(random.nextInt(4) + 1);
            apartment.setGuestroom(random.nextBoolean());
            apartment.setBasement(random.nextBoolean());
            apartment.setHotwaterheating(random.nextBoolean());
            apartment.setAirconditioning(random.nextBoolean());
            apartment.setParking(random.nextBoolean());
            apartment.setFurnishingstatus(FurnishingStatus.values()[random.nextInt(FurnishingStatus.values().length - 1)]);
            // El precio depende de lo que tenga el apartamento, y NO es una propiedad real.

            FurnishingStatus status = FurnishingStatus.UNFURNISHED;


            Apartment created = apartmentService.createApartment(apartment);
            if (created != null){
                qtyAdded++;
            }
        }

        return qtyAdded;
    }
}
