package com.example.apartment_predictor.repository;

import com.example.apartment_predictor.model.Apartment;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.*;

import java.util.Objects;

public class ApartmentSpecification {

    public static Specification<Apartment> filterBy(
            Long maxPrice,              // ≤ this price
            Integer minArea,            // ≥ this area (sq ft / m²)
            Integer minBedrooms,
            Integer minBathrooms,
            Integer minParking,
            String furnishingStatus,    // "furnished", "semi-furnished", "unfurnished" (partial match)
            Boolean mainroad,           // yes/no
            Boolean guestroom,
            Boolean basement,
            Boolean hotwaterheating,
            Boolean airconditioning,
            Boolean prefarea            // preferred area yes/no
    ) {
        return (Root<Apartment> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            Predicate p = cb.conjunction();   // starts as "true"

            // ─────────────────────────────────────────────
            // Price – most important filter
            // ─────────────────────────────────────────────
            if (maxPrice != null && maxPrice > 0) {
                p = cb.and(p, cb.lessThanOrEqualTo(root.get("price"), maxPrice));
            }

            // ─────────────────────────────────────────────
            // Area (size)
            // ─────────────────────────────────────────────
            if (minArea != null && minArea > 0) {
                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("area"), minArea));
            }

            // ─────────────────────────────────────────────
            // Bedrooms & Bathrooms
            // ─────────────────────────────────────────────
            if (minBedrooms != null && minBedrooms > 0) {
                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("bedrooms"), minBedrooms));
            }

            if (minBathrooms != null && minBathrooms > 0) {
                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("bathrooms"), minBathrooms));
            }

            // ─────────────────────────────────────────────
            // Parking spaces
            // ─────────────────────────────────────────────
            if (minParking != null && minParking > 0) {
                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("parking"), minParking));
            }

            // ─────────────────────────────────────────────
            // Furnishing status (partial / case-insensitive)
            // ─────────────────────────────────────────────
            if (isNotBlank(furnishingStatus)) {
                p = cb.and(p,
                    cb.like(
                        cb.lower(root.get("furnishingstatus")),
                        "%" + furnishingStatus.trim().toLowerCase() + "%"
                    )
                );
            }

            // ─────────────────────────────────────────────
            // Yes/No fields (Boolean-like strings: "yes"/"no")
            // ─────────────────────────────────────────────
            p = addYesNoFilter(p, cb, root, "mainroad", mainroad);
            p = addYesNoFilter(p, cb, root, "guestroom", guestroom);
            p = addYesNoFilter(p, cb, root, "basement", basement);
            p = addYesNoFilter(p, cb, root, "hotwaterheating", hotwaterheating);
            p = addYesNoFilter(p, cb, root, "airconditioning", airconditioning);
            p = addYesNoFilter(p, cb, root, "prefarea", prefarea);

            return p;
        };
    }

    // Small helper – makes yes/no filters cleaner
    private static Predicate addYesNoFilter(
            Predicate current,
            CriteriaBuilder cb,
            Root<Apartment> root,
            String fieldName,
            Boolean value) {

        if (value != null) {
            String expected = value ? "yes" : "no";
            return cb.and(current,
                cb.equal(
                    cb.lower(root.get(fieldName)),
                    expected
                )
            );
        }
        return current;
    }

    // Small utility – checks string is not null/empty/whitespace
    private static boolean isNotBlank(String s) {
        return s != null && !s.trim().isEmpty();
    }
}
