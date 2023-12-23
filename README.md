### This serves as a fictitious REST project intended for practice purposes. 😉

## Task: Banking System REST API with Java Spring Boot

### Entities

- Customer
    - Attributes
        - first name
        - last name
        - email
        - phone number

- Account
    - Attributes
        - account type
        - balance
        - opened date

### Endpoints

#### 1. `GET` Customer Details

- Endpoint: `/customers/{customerId}`
- Returns details of a specific customer and their accounts.

#### 2. `POST` Create Customer

- Endpoint: `/customers`
- Return the created customer details.
- JSON payload containing customer information

```JSON
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "123-456-7890"
}
```

#### 3. `PUT` Update Customer Details

- Endpoint: `customers/{customerId}`
- Return the updated customer details
- JSON payload container updated customer information

```JSON
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "updated.email@example.com",
  "phoneNumber": "987-654-3210"
}
```

#### 4. `POST` Create Account for Customer

- Endpoint: `/customers/{customerId}/accounts`
- Return the created account details.
- JSON payload containing account information

```JSON
{
  "accountType": "Savings",
  "balance": 1000.00
}
```

#### 5. `PUT` Update Account Details

- Endpoint: `/accounts/{accountId}`
- Return the updated account details.
- JSON payload containing updated account information

```JSON
{
  "accountType": "Checking",
  "balance": 1500.00
}
```

### Validations

- Use appropriate HTTP status codes in responses.
- Implement exception handling for error scenarios.
- Use a relational database (e.g., H2, MySQL) for persistence.
- Implement proper error handling and responses for validation failures.
