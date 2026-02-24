# assignOwnerApartmentToContract

## Summary

- `ApartmentAssignRestController`

The `assignOwnerAndApartmentToContract` method includes the implementation:

**Key Features:**

- **Defensive programming**: Validates apartmentId, ownerId, and propertyContractData existence
- **UUID generation**: PropertyContract automatically gets UUID ID via constructor
- **Data mapping**: Copies all contract data from request body with fallback defaults
- **Relationship setup**: Properly sets owner and apartment relationships
- **Database persistence**: Saves and verifies contract creation
- **Consistent response pattern**: Follows same header structure as other methods

**Implementation Details:**

- **Validation checks**:
  - Apartment exists by ID
  - Owner exists by ID
  - Contract data is not null
- **Data handling**:
  - Uses provided contract date or defaults to today
  - Uses provided type or defaults to "APARTMENT"
  - Uses provided address or generates based on apartment ID
- **Response headers**:
  - Status messages for success/failure
  - Contract ID added to headers on success
  - Returns the updated apartment object

**API Endpoint:**

- **URL**: `PUT /api/v1/assign/apartment/contractOwner`
- **Parameters**: `apartmentId`, `ownerId`
- **Body**: PropertyContract JSON object
- **Response**: Updated `Apartment` object with contract relationships

Code skeleton:

```java
@PutMapping("/contractOwner")
    public ResponseEntity<Apartment> assignOwnerAndApartmentToContract(
            @RequestParam String apartmentId,
            @RequestParam String ownerId,
            @RequestBody PropertyContract propertyContractData
    ){
        // ... headers
        // Defensive programming: validate apartmentId exists
        // Defensive programming: validate ownerId exists
        // Defensive programming: validate propertyContractData is not null

        // Create a new PropertyContract with UUID id and data from propertyContractData
        PropertyContract newPropertyContract = new PropertyContract();
        // set all data for newPropertyContract with propertyContractData
        //newPropertyContract.setActive(propertyContractData.isActive());

        // Set relationships
        newPropertyContract.setOwner(owner);
        newPropertyContract.setApartment(apartment);

        // Save newPropertyContract
        propertyContractRepository.save(newPropertyContract);

        // Defensive programming: Verify the contract was saved
        // ... headers
        // Return apartment updated
        return ResponseEntity.ok().headers(headers).body(apartment);
    }
```

Code:

```java
@PutMapping("/contractOwner")
    public ResponseEntity<Apartment> assignOwnerAndApartmentToContract(
            @RequestParam String apartmentId,
            @RequestParam String ownerId,
            @RequestBody PropertyContract propertyContractData
    ){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "assignOwnerAndApartmentToContract executed");
        headers.add("version", "1.0 Api Rest Apartment Object");
        headers.add("active", "true");
        headers.add("author", "Albert");

        // Defensive programming: validate apartmentId exists
        Apartment apartment = apartmentService.findApartmentById(apartmentId);
        if (apartment == null) {
            headers.add("Status", "assignOwnerAndApartmentToContract failed: apartment not found");
            return ResponseEntity.badRequest().headers(headers).body(null);
        }

        // Defensive programming: validate ownerId exists
        Owner owner = ownerRepository.findById(ownerId).orElse(null);
        if (owner == null) {
            headers.add("Status", "assignOwnerAndApartmentToContract failed: owner not found");
            return ResponseEntity.badRequest().headers(headers).body(null);
        }

        // Defensive programming: validate propertyContractData is not null
        if (propertyContractData == null) {
            headers.add("Status", "assignOwnerAndApartmentToContract failed: propertyContractData is null");
            return ResponseEntity.badRequest().headers(headers).body(null);
        }

        // Create a new PropertyContract with UUID id and data from propertyContractData
        PropertyContract newPropertyContract = new PropertyContract();
        newPropertyContract.setPropertyContractCode(propertyContractData.getPropertyContractCode());
        newPropertyContract.setUrlContractPropertyDocument(propertyContractData.getUrlContractPropertyDocument());
        newPropertyContract.setContractDate(propertyContractData.getContractDate() != null ? 
            propertyContractData.getContractDate() : LocalDate.now());
        newPropertyContract.setValuePropertyContract(propertyContractData.getValuePropertyContract());
        newPropertyContract.setTypeProperty(propertyContractData.getTypeProperty() != null ? 
            propertyContractData.getTypeProperty() : "APARTMENT");
        newPropertyContract.setAddress(propertyContractData.getAddress() != null ? 
            propertyContractData.getAddress() : "Address for Apartment " + apartmentId);
        newPropertyContract.setActive(propertyContractData.isActive());

        // Set relationships
        newPropertyContract.setOwner(owner);
        newPropertyContract.setApartment(apartment);

        // Save newPropertyContract
        propertyContractRepository.save(newPropertyContract);

        // Verify the contract was saved
        PropertyContract savedContract = propertyContractRepository.findById(newPropertyContract.getId()).orElse(null);
        if (savedContract == null) {
            headers.add("Status", "assignOwnerAndApartmentToContract failed: contract not saved");
            return ResponseEntity.badRequest().headers(headers).body(null);
        }

        // Return apartment updated
        headers.add("Status", "assignOwnerAndApartmentToContract success");
        headers.add("contractId", savedContract.getId());

        return ResponseEntity.ok().headers(headers).body(apartment);
    }
```

## Postman

### Owner #1 David Miller

<mark>ownerid</mark> : `"00567d6b-d4d4-486b-b12b-73e50dcd524d",`

```json
"owner": {
    "id": "00567d6b-d4d4-486b-b12b-73e50dcd524d",
    "fullName": "David Miller",
    "birthDate": "1982-02-24",
    "email": "david.miller@hotmail.com",
    "password": "password123",
    "idLegalOwner": "RLT-154743",
    "registrationDate": "2016-03-09",
    "qtyDaysAsOwner": 3639,
    "business": false,
    "active": false
},
```

**API REQUEST**

`http://localhost:8080/api/v1/assign/apartment/contractOwner?apartmentId=5ca600bb-6071-4974-aaec-d854aa70aafc&ownerId=00567d6b-d4d4-486b-b12b-73e50dcd524d`

```json
{
    "propertyContractCode": "PROP-001-123456",
    "urlContractPropertyDocument": "https://docs.example.com/contracts/PROP-001-123456.pdf",
    "contractDate": "2023-06-15",
    "valuePropertyContract": 250000,
    "typeProperty": "APARTMENT",
    "address": "123 Main Street, Apt 4B, New York, NY 10001",
    "isActive": true
}
```

<mark>ownerId</mark> `:00567d6b-d4d4-486b-b12b-73e50dcd524d`
<mark>apartmentId</mark>:` 5ca600bb-6071-4974-aaec-d854aa70aafc`

**Response**

```json
{
    "id": "5ca600bb-6071-4974-aaec-d854aa70aafc",
    "price": null,
    "area": 5,
    "bedrooms": 1,
    "bathrooms": 1,
    "stories": 1,
    "mainroad": "yes",
    "guestroom": "yes",
    "basement": "yes",
    "hotwaterheating": "yes",
    "airconditioning": "yes",
    "parking": 1,
    "prefarea": "yes",
    "furnishingstatus": "none",
    "reviews": [
        {
            "id": "a31a9482-3e47-4ec9-b8c9-9f3d3884c487",
            "title": "Nice Apartment in Fifth Avenue",
            "content": "This apartment exceeded my expectations. The location is perfect and the amenities are top-notch. Highly recommended for anyone looking for a comfortable stay.",
            "rating": 5,
            "reviewDate": "2025-12-12",
            "reviewer": null
        }
    ],
    "schools": [],
    "contracts": [
        {
            "id": "51ac3c07-4dc9-4d30-81dc-11973aaa4191",
            "propertyContractCode": "PROP-001-123456",
            "urlContractPropertyDocument": "https://docs.example.com/contracts/PROP-001-123456.pdf",
            "contractDate": "2023-06-15",
            "valuePropertyContract": 250000,
            "typeProperty": "APARTMENT",
            "address": "123 Main Street, Apt 4B, New York, NY 10001",
            "owner": {
                "id": "00567d6b-d4d4-486b-b12b-73e50dcd524d",
                "fullName": "David Miller",
                "birthDate": "1982-02-24",
                "email": "david.miller@hotmail.com",
                "password": "password123",
                "idLegalOwner": "RLT-154743",
                "registrationDate": "2016-03-09",
                "qtyDaysAsOwner": 3639,
                "business": false,
                "active": false
            },
            "active": false
        }
    ]
}
```

### Owner #2 David Smith

<mark>ownerId</mark> : `098e4821-e523-4f65-98c3-098143f6539e`

```json
 {
        "id": "098e4821-e523-4f65-98c3-098143f6539e",
        "fullName": "David Smith",
        "birthDate": "1992-02-24",
        "email": "david.smith@gmail.com",
        "password": "password123",
        "idLegalOwner": "RLT-856656",
        "registrationDate": "2021-04-13",
        "qtyDaysAsOwner": 1778,
        "business": true,
        "active": true
    },
```

`http://localhost:8080/api/v1/assign/apartment/contractOwner?apartmentId=5ca600bb-6071-4974-aaec-d854aa70aafc&ownerId=098e4821-e523-4f65-98c3-098143f6539e`

```json
{
    "propertyContractCode": "PROP-001-123456",
    "urlContractPropertyDocument": "https://docs.example.com/contracts/PROP-001-123456.pdf",
    "contractDate": "2023-06-15",
    "valuePropertyContract": 250000,
    "typeProperty": "APARTMENT",
    "address": "123 Main Street, Apt 4B, New York, NY 10001",
    "isActive": true
}
```

<mark>ownerId</mark> :`098e4821-e523-4f65-98c3-098143f6539e`
<mark>apartmentId</mark>: `5ca600bb-6071-4974-aaec-d854aa70aafc`

```json
{
    "id": "5ca600bb-6071-4974-aaec-d854aa70aafc",
    "price": null,
    "area": 5,
    "bedrooms": 1,
    "bathrooms": 1,
    "stories": 1,
    "mainroad": "yes",
    "guestroom": "yes",
    "basement": "yes",
    "hotwaterheating": "yes",
    "airconditioning": "yes",
    "parking": 1,
    "prefarea": "yes",
    "furnishingstatus": "none",
    "reviews": [
        {
            "id": "a31a9482-3e47-4ec9-b8c9-9f3d3884c487",
            "title": "Nice Apartment in Fifth Avenue",
            "content": "This apartment exceeded my expectations. The location is perfect and the amenities are top-notch. Highly recommended for anyone looking for a comfortable stay.",
            "rating": 5,
            "reviewDate": "2025-12-12",
            "reviewer": null
        }
    ],
    "schools": [],
    "contracts": [
        {
            "id": "51ac3c07-4dc9-4d30-81dc-11973aaa4191",
            "propertyContractCode": "PROP-001-123456",
            "urlContractPropertyDocument": "https://docs.example.com/contracts/PROP-001-123456.pdf",
            "contractDate": "2023-06-15",
            "valuePropertyContract": 250000,
            "typeProperty": "APARTMENT",
            "address": "123 Main Street, Apt 4B, New York, NY 10001",
            "owner": {
                "id": "00567d6b-d4d4-486b-b12b-73e50dcd524d",
                "fullName": "David Miller",
                "birthDate": "1982-02-24",
                "email": "david.miller@hotmail.com",
                "password": "password123",
                "idLegalOwner": "RLT-154743",
                "registrationDate": "2016-03-09",
                "qtyDaysAsOwner": 3639,
                "business": false,
                "active": false
            },
            "active": false
        },
        {
            "id": "a8442953-58f0-4546-81e4-373cd2d33ead",
            "propertyContractCode": "PROP-001-123456",
            "urlContractPropertyDocument": "https://docs.example.com/contracts/PROP-001-123456.pdf",
            "contractDate": "2023-06-15",
            "valuePropertyContract": 250000,
            "typeProperty": "APARTMENT",
            "address": "123 Main Street, Apt 4B, New York, NY 10001",
            "owner": {
                "id": "098e4821-e523-4f65-98c3-098143f6539e",
                "fullName": "David Smith",
                "birthDate": "1992-02-24",
                "email": "david.smith@gmail.com",
                "password": "password123",
                "idLegalOwner": "RLT-856656",
                "registrationDate": "2021-04-13",
                "qtyDaysAsOwner": 1778,
                "business": true,
                "active": true
            },
            "active": false
        }
    ]
}
```

**Apartment Response Summary**

Apartment Details:

- **ID**: `5ca600bb-6071-4974-aaec-d854aa70aafc`
- **Price**: `null` (not set)
- **Area**: 5 sq units
- **Bedrooms**: 1
- **Bathrooms**: 1
- **Stories**: 1
- **Features**: Main road, guest room, basement, hot water heating, AC, parking (1), preferred area
- **Furnishing**: None

**Reviews:** 1 review

- **Title**: "Nice Apartment in Fifth Avenue"
- **Rating**: 5/5
- **Reviewer**: Not assigned (null)

**Schools:** None assigned

**Property Contracts:** 2 contracts (shared ownership)

### Contract 1:

- **ID**: `51ac3c07-4dc9-4d30-81dc-11973aaa4191`
- **Code**: `PROP-001-123456`
- **Value**: $250,000
- **Date**: June 15, 2023
- **Owner**: <mark>David Miller</mark> (inactive, non-business)
- **Status**: Inactive

### Contract 2:

- **ID**: `a8442953-58f0-4546-81e4-373cd2d33ead`
- **Code**: `PROP-001-123456` (same contract code)
- **Value**: $250,000 (same value)
- **Date**: June 15, 2023 (same date)
- **Owner**: <mark>David Smith</mark> (active, business owner)
- **Status**: Inactive

**Key Observations:**

- Both contracts share the same contract details (code, value, date) - indicating co-ownership
- Two different owners share the same apartment
- Both contracts are currently inactive
- The apartment has no assigned schools but has one positive review

## Screenshots

* [assignOwnerApartmentToContract-flow](https://github.com/AlbertProfe/ApartmentPredictor/tree/master/docs/diagrams/assignOwnerApartmentToContract-flow)
