# assignReviewToApartment

The method implement the next features:

1. Accept `apartmentId` and `reviewerId` as request parameters
2. Accept the review data in the request body
3. Validate that both the apartment and reviewer exist
4. Create a new review with the provided data
5. Save the new review created and check it exists at db.
6. Associate the review with both the apartment and reviewer
7. Save and return the updated apartment

## Implementation

```java
 @PutMapping("/reviews")
    public ResponseEntity<Apartment> assignReviewToApartment(
            @RequestParam String apartmentId,
            @RequestParam String reviewerId,
            @RequestBody Review reviewData
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "assignReviewToApartment executed");
        headers.add("version", "1.0 Api Rest Apartment Object");
        headers.add("active", "true");
        headers.add("author", "Albert");

        // Validate apartment exists
        Apartment apartment = apartmentService.findApartmentById(apartmentId);
        if (apartment == null) {
            headers.add("Status", "assignReviewToApartment failed: apartment not found");
            return ResponseEntity.badRequest().headers(headers).body(null);
        }

        // Validate reviewer exists
        Reviewer reviewer = reviewerRepository.findById(reviewerId).orElse(null);
        if (reviewer == null) {
            headers.add("Status", "assignReviewToApartment failed: reviewer not found");
            return ResponseEntity.badRequest().headers(headers).body(null);
        }

        // Validate review data
        if (reviewData == null) {
            headers.add("Status", "assignReviewToApartment failed: review data is null");
            return ResponseEntity.badRequest().headers(headers).body(null);
        }

        // Create and configure the review
        Review newReview = new Review();
        newReview.setTitle(reviewData.getTitle());
        newReview.setContent(reviewData.getContent());
        newReview.setRating(reviewData.getRating());
        newReview.setReviewDate(reviewData.getReviewDate() != null ? reviewData.getReviewDate() : LocalDate.now());
        newReview.setReviewer(reviewer);

        // Save the review
        reviewRepository.save(newReview);

        // Get the review from the database
        Review review = reviewRepository.findById(newReview.getId()).orElse(null);

        if (review == null) {
            headers.add("Status", "assignReviewToApartment failed: review not found");
            return ResponseEntity.badRequest().headers(headers).body(null);
        }

        // Add review to apartment (this also sets the apartment reference on the review)
        apartment.addReview(review);

        // Save the updated apartment
        Apartment apartmentSaved = apartmentService.updateApartment(apartment);
        headers.add("Status", "assignReviewToApartment success");

        return ResponseEntity.ok().headers(headers).body(apartmentSaved);
    }
```

## Required Dependencies

You'll need to add these imports and autowire the ReviewerRepository:

```java
import com.example.apartment_predictor.model.Review;
import com.example.apartment_predictor.model.Reviewer;
import com.example.apartment_predictor.repository.ReviewerRepository;
import org.springframework.web.bind.annotation.RequestBody;
import java.time.LocalDate;
import java.util.Optional;

@Autowired
ReviewerRepository reviewerRepository;
```

## Postman Usage

For Postman testing:

- **Method**: PUT

- **URL**: `http://localhost:8080/api/v1/assign/apartment/reviews?apartmentId={apartmentId}&reviewerId={reviewerId}`

- **Headers**: `Content-Type: application/json`

- **Body** (raw JSON):
  
  ```json
  {
    "title": "Great apartment!",
    "content": "Very clean and well-maintained property.",
    "rating": 5,
    "reviewDate": "2024-02-23"
  }
  ```

## Screeshoots

![](https://raw.githubusercontent.com/AlbertProfe/ApartmentPredictor/refs/heads/master/docs/diagrams/assignReviewToApartment-flow/postaman-assignReviewToApartment-1.png)

![](https://raw.githubusercontent.com/AlbertProfe/ApartmentPredictor/refs/heads/master/docs/diagrams/assignReviewToApartment-flow/postaman-assignReviewToApartment-2.png)

![](https://raw.githubusercontent.com/AlbertProfe/ApartmentPredictor/refs/heads/master/docs/diagrams/assignReviewToApartment-flow/postaman-assignReviewToApartment-3.png)

![](https://raw.githubusercontent.com/AlbertProfe/ApartmentPredictor/refs/heads/master/docs/diagrams/assignReviewToApartment-flow/postaman-assignReviewToApartment-4.png)
