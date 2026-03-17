# Plain Apartment specification

## Summary

> We’ll make a<mark> flexible filter that lets filter search by</mark> most of the important fields (price range, size, bedrooms, bathrooms, parking, furnishing, preferred area, main road, guestroom, basement, airconditioning, hot water heating…).

## Specification

```java
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
            addYesNoFilter(p, cb, root, "mainroad", mainroad);
            addYesNoFilter(p, cb, root, "guestroom", guestroom);
            addYesNoFilter(p, cb, root, "basement", basement);
            addYesNoFilter(p, cb, root, "hotwaterheating", hotwaterheating);
            addYesNoFilter(p, cb, root, "airconditioning", airconditioning);
            addYesNoFilter(p, cb, root, "prefarea", prefarea);

            return p;
        };
    }

    // Small helper – makes yes/no filters cleaner
    private static void addYesNoFilter(
            Predicate current,
            CriteriaBuilder cb,
            Root<Apartment> root,
            String fieldName,
            Boolean value) {

        if (value != null) {
            String expected = value ? "yes" : "no";
            current = cb.and(current,
                cb.equal(
                    cb.lower(root.get(fieldName)),
                    expected
                )
            );
        }
    }

    // Small utility – checks string is not null/empty/whitespace
    private static boolean isNotBlank(String s) {
        return s != null && !s.trim().isEmpty();
    }
}
```

### Example usage in Controller

Pseudocode `filterApartments`:

```java
@GetMapping("/filter")
public ResponseEntity<List<Apartment>> filterApartments(
        Long maxPrice, ....) {

    // create the specification for apartment
    Specification<Apartment> apartmentSpecification = 
           ApartmentSpecification.filterBy(maxPrice,...);

    // use the apartmentSpecification at apartmentRepository
    // to findAll by filtering it with the specification
    List<Apartment> apartments = 
        apartmentRepository.findAll(apartmentSpecification);

    return ResponseEntity.ok(apartments);
}
```

Full code:

```java
@GetMapping("/filter")
public ResponseEntity<List<Apartment>> filterApartments(
        @RequestParam(required = false) Long maxPrice,
        @RequestParam(required = false) Integer minArea,
        @RequestParam(required = false) Integer minBedrooms,
        @RequestParam(required = false) Integer minBathrooms,
        @RequestParam(required = false) Integer minParking,
        @RequestParam(required = false) String furnishingStatus,
        @RequestParam(required = false) Boolean mainroad,
        @RequestParam(required = false) Boolean guestroom,
        @RequestParam(required = false) Boolean basement,
        @RequestParam(required = false) Boolean hotwaterheating,
        @RequestParam(required = false) Boolean airconditioning,
        @RequestParam(required = false) Boolean prefarea
) {
    Specification<Apartment> spec = ApartmentSpecification.filterBy(
            maxPrice, minArea, minBedrooms, minBathrooms, minParking,
            furnishingStatus, mainroad, guestroom, basement,
            hotwaterheating, airconditioning, prefarea
    );

    List<Apartment> apartments = apartmentRepository.findAll(spec);

    return ResponseEntity.ok(apartments);
}
```

### Example URLs

- `/search?maxPrice=8500000&minBedrooms=3&minBathrooms=2`  
- `/search?furnishingStatus=furnished&airconditioning=true`  
- `/search?minArea=1800&mainroad=true&prefarea=true`  
- `/search?minParking=2&basement=yes&hotwaterheating=yes`

## Quick memory

![](https://raw.githubusercontent.com/AlbertProfe/ApartmentPredictor/refs/heads/master/docs/screenshots/code-specification-apartment.png)

How to create the `filterApartments` method:

1. <mark>Write the **method** signature  </mark>
   
   - Use `@GetMapping("/filter")`  
   - Return type: `ResponseEntity<List<Apartment>>`  
   - Method name: `filterApartments`  
   - Add all needed `@RequestParam` parameters (like `maxPrice`, `minArea`, `minBedrooms`, etc.) with `required = false`

2. <mark>Create the **Specification** object  </mark>
   
   - Call `ApartmentSpecification.searchBy(...)` (or `filterBy(...)`)  
   - Pass all the request parameters you received in the same order  
   - Store the result in a variable (e.g. `Specification<Apartment> spec`)

3. <mark>Call the **repository** with the specification  </mark>
   
   - Use `apartmentRepository.findAll(...)`  
   - Give it the specification object you just created  
   - Save the returned list in a variable (e.g. `List<Apartment> apartments`)

4. <mark>Return the result wrapped in **ResponseEntity**  </mark>
   
   - Use `ResponseEntity.ok(...)`  
   - Pass the list of apartments inside it  
   - This sends HTTP 200 + the filtered list as JSON
