package com.example.apartment_predictor.utils;

import com.example.apartment_predictor.model.*;
import com.example.apartment_predictor.repository.SchoolRepository;
import com.example.apartment_predictor.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class PopulateDB {

    @Autowired
    ApartmentService apartmentService;

    @Autowired
    SchoolRepository schoolRepository;

    //todo: REFACTOR > all methods MUST return the objects created
    //todo: define our pattern, orchestrator
    //todo: define steps for our orchestrator > populateAll()

    // --------- ORCHESTRATOR ------------------------------------------------
    public int populateAll(int qty) {

        // 1 populate Apartments > List
        List<Apartment> plainApartments = populatePlainApartments(qty);
        // 2 populate Schools > List
        List<School> schools = populateSchools(qty);
        // 3 assignSchoolsToApartments
        boolean status = assignSchoolsToApartments(plainApartments, schools);


        // 4 populate Reviewers > List
        List<Reviewer> reviewers = populateReviewers(qty);
        // 5 create Reviews (very general description, valid for all apartments) and assign Reviewers
        // DO NOT SAVE to db!
        List<Review> plainReviews = populatePlainReviews(qty);
        // 6 assign Reviewers to Reviews
        List<Review> reviews = assignReviewersToReviews(reviewers, plainReviews);
        // 7 assign Reviews to Apartments
        List<Apartment> apartments = assignReviewsToApartments(reviews, plainApartments);


        // 8 populate Owners
        // 9 populate PropertyContracts assign Owners and Apartments
        // 10 check and return qty of created objects


        return 0;
    }

    // --------- POPULATE apartments and schools ------------------------------

    public List<School> populateSchools(int qty) {
        int qtySchoolsCreated = 0;
        List<School> schools = new ArrayList<>();
        if (qty <= 0) return null;

        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        String[] schoolTypes = {"public", "private", "religious"};
        String[] locations = {"Downtown", "Uptown", "Suburbs", "East Side", "West Side"};
        String[] namePrefixes = {"Green", "Oak", "River", "Hill", "Sunrise", "Cedar", "Lakeside"};
        String[] nameSuffixes = {"Academy", "School", "Institute", "High School", "College"};

        for (int i = 0; i < qty; i++) {
            String type = schoolTypes[rnd.nextInt(schoolTypes.length)];
            String location = locations[rnd.nextInt(locations.length)];
            int rating = rnd.nextInt(1, 6);
            boolean isPublic = "public".equals(type);

            String name = namePrefixes[rnd.nextInt(namePrefixes.length)] + " " + nameSuffixes[rnd.nextInt(nameSuffixes.length)];

            School school = new School(name, type, location, rating, isPublic);
            schoolRepository.save(school);

            School schoolById = schoolRepository.findById(school.getId()).orElse(null);
            if (schoolById != null) {
                qtySchoolsCreated++;
                schools.add(schoolById);
                System.out.println(
                        "School #" + qtySchoolsCreated +
                                "/" + qty + " created populateDB: " + schoolById);
            }
        }

        return schools;
    }

    public List<Apartment> populatePlainApartments(int qty) {
        int qtyApartmetnsCreated = 0;
        List<Apartment> apartments = new ArrayList<>();
        if (qty <= 0) return null;

        //Faker faker = new Faker(new Locale("en-US"));
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        String[] furnishingOptions = {"furnished", "semi-furnished", "unfurnished"};

        for (int i = 0; i < qty; i++) {
            Long price = rnd.nextLong(30_000, 600_001);          // adjust range if you want
            Integer area = rnd.nextInt(300, 5001);              // adjust range if you want
            Integer bedrooms = rnd.nextInt(1, 7);
            Integer bathrooms = rnd.nextInt(1, 5);
            Integer stories = rnd.nextInt(1, 5);

            String mainroad = rnd.nextBoolean() ? "yes" : "no";
            String guestroom = rnd.nextBoolean() ? "yes" : "no";
            String basement = rnd.nextBoolean() ? "yes" : "no";
            String hotwaterheating = rnd.nextBoolean() ? "yes" : "no";
            String airconditioning = rnd.nextBoolean() ? "yes" : "no";
            Integer parking = rnd.nextInt(0, 4);
            String prefarea = rnd.nextBoolean() ? "yes" : "no";

            String furnishingstatus = furnishingOptions[rnd.nextInt(furnishingOptions.length)];

            Apartment apartment = new Apartment(
                    price,
                    area,
                    bedrooms,
                    bathrooms,
                    stories,
                    mainroad,
                    guestroom,
                    basement,
                    hotwaterheating,
                    airconditioning,
                    parking,
                    prefarea,
                    furnishingstatus
            );

            apartmentService.createApartment(apartment);

            Apartment apartmentById = apartmentService.findApartmentById(apartment.getId());
            if (apartmentById != null) {
                qtyApartmetnsCreated++;
                apartments.add(apartmentById);
                System.out.println(
                        "Apartment #" + qtyApartmetnsCreated +
                         "/" + qty + " created populateDB: " + apartmentById);
            }

        }
        return apartments;
    }

    public boolean assignSchoolsToApartments(List<Apartment> apartments, List<School> schools) {
        if (apartments == null || apartments.isEmpty() || schools == null || schools.isEmpty()) {
            return false;
        }

        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        for (Apartment apartment : apartments) {
            // Randomly assign 1 to 4 schools
            int numSchoolsToAssign = rnd.nextInt(1, 5); // 1, 2, 3, or 4

            // Don't assign more schools than available
            numSchoolsToAssign = Math.min(numSchoolsToAssign, schools.size());

            // Randomly select schools
            List<School> selectedSchools = new ArrayList<>();
            List<School> availableSchools = new ArrayList<>(schools);

            for (int i = 0; i < numSchoolsToAssign && !availableSchools.isEmpty(); i++) {
                int randomIndex = rnd.nextInt(availableSchools.size());
                School selectedSchool = availableSchools.remove(randomIndex);
                selectedSchools.add(selectedSchool);
            }

            // Assign schools to apartment
            if (!selectedSchools.isEmpty()) {
                apartment.addSchools(selectedSchools);
                apartmentService.updateApartment(apartment);

                System.out.println("Assigned " + selectedSchools.size() + " schools to apartment " + apartment.getId());
            }
        }

        return true;
    }

    // ---------- POPULATE reviews, reviewers ------------------------------

    /*public List<Person> populatePeople(int qty){return null;}*/

    public List<Reviewer> populateReviewers(int qty) {


        return null;
    }

    public List<Review> populatePlainReviews(int qty) {
        return null;
    }

    public List<Review> assignReviewersToReviews(List<Reviewer> reviewers, List<Review> reviews){

        // 1 get all reviewers


        return null;
    }

    public List<Apartment> assignReviewsToApartments(List<Review> reviews, List<Apartment> apartments){
        return null;
    }

    // ---------- POPULATE owners, property contracts ------------------------------

    public int populateOwners(int qty) {
        return 0;
    }

    public int populatePropertyContracts(int qty) {
        return 0;
    }
}
