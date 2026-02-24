package com.example.apartment_predictor.utils;

import com.example.apartment_predictor.model.*;
import com.example.apartment_predictor.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

@Component
public class DatabaseVerifier {

    @Autowired
    ApartmentRepository apartmentRepository;

    @Autowired
    SchoolRepository schoolRepository;

    @Autowired
    ReviewerRepository reviewerRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    PropertyContractRepository propertyContractRepository;

    /**
     * Generic method to verify that all objects in a list exist in their respective database tables
     * @param objects List of objects to verify
     * @param <T> Type of objects (must extend one of the model classes)
     * @return true if all objects exist in database, false otherwise
     */
    public <T> boolean verifyAllObjectsExist(List<T> objects) {
        if (objects == null || objects.isEmpty()) {
            return false;
        }

        int verifiedCount = 0;
        int totalCount = objects.size();

        for (T obj : objects) {
            if (verifyObjectExists(obj)) {
                verifiedCount++;
            }
        }

        boolean allExist = verifiedCount == totalCount;
        System.out.println("Database Verification: " + verifiedCount + "/" + totalCount + " objects verified. All exist: " + allExist);

        return allExist;
    }

    /**
     * Verify a single object exists in its database table
     * @param obj Object to verify
     * @param <T> Type of object
     * @return true if object exists, false otherwise
     */
    public <T> boolean verifyObjectExists(T obj) {
        try {
            String id = getId(obj);
            if (id == null) {
                System.out.println("Object has null ID: " + obj);
                return false;
            }

            boolean exists = checkExistence(obj, id);
            if (!exists) {
                System.out.println("Object NOT found in database: " + obj.getClass().getSimpleName() + " with ID: " + id);
            }

            return exists;

        } catch (Exception e) {
            System.out.println("Error verifying object: " + obj + " - " + e.getMessage());
            return false;
        }
    }

    /**
     * Get the ID of an object using reflection
     */
    private <T> String getId(T obj) throws Exception {
        Method getIdMethod = obj.getClass().getMethod("getId");
        return (String) getIdMethod.invoke(obj);
    }

    /**
     * Check if object exists in its respective repository
     */
    private <T> boolean checkExistence(T obj, String id) {
        if (obj instanceof Apartment) {
            return apartmentRepository.findById(id).isPresent();
        } else if (obj instanceof School) {
            return schoolRepository.findById(id).isPresent();
        } else if (obj instanceof Reviewer) {
            return reviewerRepository.findById(id).isPresent();
        } else if (obj instanceof Review) {
            return reviewRepository.findById(id).isPresent();
        } else if (obj instanceof Owner) {
            return ownerRepository.findById(id).isPresent();
        } else if (obj instanceof PropertyContract) {
            return propertyContractRepository.findById(id).isPresent();
        } else {
            throw new IllegalArgumentException("Unsupported object type: " + obj.getClass().getSimpleName());
        }
    }

    /**
     * Convenience method to verify all PopulateDB results at once
     */
    public boolean verifyPopulateDBResults(
            List<Apartment> apartments,
            List<School> schools,
            List<Reviewer> reviewers,
            List<Review> reviews,
            List<Owner> owners,
            List<PropertyContract> propertyContracts) {

        System.out.println("=== Starting Database Verification ===");

        boolean apartmentsOk = verifyAllObjectsExist(apartments);
        boolean schoolsOk = verifyAllObjectsExist(schools);
        boolean reviewersOk = verifyAllObjectsExist(reviewers);
        boolean reviewsOk = verifyAllObjectsExist(reviews);
        boolean ownersOk = verifyAllObjectsExist(owners);
        boolean propertyContractsOk = verifyAllObjectsExist(propertyContracts);

        boolean allOk = apartmentsOk && schoolsOk && reviewersOk && reviewsOk && ownersOk && propertyContractsOk;

        System.out.println("=== Database Verification Complete ===");
        System.out.println("Final Result: " + (allOk ? "SUCCESS - All objects verified" : "FAILURE - Some objects missing"));

        return allOk;
    }
}