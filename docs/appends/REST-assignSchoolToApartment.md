# assignSchoolToApartment

## New API /api/v1/assign/apartment/schools

File: `src/main/java/com/example/apartment_predictor/controller/ApartmentAssignRestController.java`

- [ApartmentAssignRestController.java](https://github.com/AlbertProfe/ApartmentPredictor/blob/b12efcb4e924d6198e732b1854faaba6c0cc2f37/ApartmentPredictor/src/main/java/com/example/apartment_predictor/controller/ApartmentAssignRestController.java#L40)

```java
@PutMapping("/schools")
    public ResponseEntity<Apartment> assignSchoolsToApartment(
            @RequestParam String apartmentId,
            @RequestParam List<String> schoolIds
    ) {

        // ... headers

        // ... defensive code for apartmentId, schoolIds

        // with the ids fetch the schools at db
        Iterable<School> found = schoolRepository.findAllById(schoolIds);
        List<School> schoolsFound = StreamSupport.stream(found.spliterator(), false)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        // add schools to apartment
        apartment.addSchools(schoolsFound);

        // .. headers
        // save apartment
        Apartment apartmentSaved = apartmentService.updateApartment(apartment);

        return ResponseEntity.ok().headers(headers).body(apartmentSaved);
    }

    }
```

## Old API /api/apartment/assignSchoolsToApartment

How it works in your REST controller

![](https://raw.githubusercontent.com/AlbertProfe/ApartmentPredictor/refs/heads/master/docs/diagrams/schoolsAssignToApartment-Flow/apartment-schools-controller.png)

File: `src/main/java/com/example/apartment_predictor/controller/ApartmentRestController.java`

- Endpoint: `PUT /api/apartment/assignSchoolsToApartment`
  - New Endpoint v1: `PUT /api/v1/assign/apartment/schools`
- Flow:
  - Load the Apartment by `apartmentId`
  - Load all `School`s by `schoolIds`
  - If some requested ids weren’t found, return `400 Bad Request`
  - Update the relationship by adding schools to the apartment (you currently use apartment.addSchools(schoolsFound))
  - Save the apartment → JPA writes rows into `APARTMENT_SCHOOL_JOIN_TABLE`
  - Return `200 OK` with response headers and the saved apartment

![](https://raw.githubusercontent.com/AlbertProfe/ApartmentPredictor/refs/heads/master/docs/diagrams/schoolsAssignToApartment-Flow/postman-apartment-schools.png)

```json
{
    "id": "fe9614a2-1a3f-403c-9822-4f5d027e13ed",
    "price": 130000,
    "area": 5,
    "bedrooms": 2,
    "bathrooms": 2,
    "stories": 3,
    "mainroad": "yes",
    "guestroom": "no",
    "basement": "no",
    "hotwaterheating": "yes",
    "airconditioning": "yes",
    "parking": 2,
    "prefarea": "yes",
    "furnishingstatus": "furnished",
    "reviews": [
        {
            "id": "7afe17a7-8fac-4847-a4b1-d934b2cc8a06",
            "title": "Nice Apartment in Fifth Avenue",
            "content": "This apartment exceeded my expectations. The location is perfect and the amenities are top-notch. Highly recommended for anyone looking for a comfortable stay.",
            "rating": 5,
            "reviewDate": "2025-12-11"
        }
    ],
    "schools": [
        {
            "id": "87ffb224-a053-4c3d-b593-cab8cf2f457e",
            "name": "Sunrise School",
            "type": "religious",
            "location": "East Side",
            "rating": 4,
            "public": false
        },
        {
            "id": "a2afa2f1-bab1-4fa6-816e-b77b8f3e31cd",
            "name": "Sunrise High School",
            "type": "religious",
            "location": "Downtown",
            "rating": 4,
            "public": false
        },
        {
            "id": "d217c2be-5079-43c8-9ffb-631ea8642bba",
            "name": "Hill Institute",
            "type": "private",
            "location": "East Side",
            "rating": 1,
            "public": false
        }
    ]
}
```
