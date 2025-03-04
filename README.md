# 🛒 Order Management API

This is a **Spring Boot REST API** for managing orders. It provides CRUD operations for creating, retrieving, updating, and deleting orders. 

## 🚀 Getting Started

### 🔹 Prerequisites
- Java 17+
- Maven
- Postman (Optional)

### 🔹 Installation & Running the Project
1. Clone the repository:
   ```sh
   git clone https://github.com/Mohamedamr3737/Aspects_lab1
   cd Aspects_lab1
   ```
2. Build the project:
   ```sh
   mvn clean install
   ```
3. Run the application:
   ```sh
   mvn spring-boot:run
   ```
4. The API will be available at:
   ```
   http://localhost:8080
   ```

---

## 🔐 Authentication
All API requests **require a Bearer Token** in the headers.

```sh
Authorization: Bearer dummy-token-12345
```

---

## 📌 API Endpoints

### 🟢 1. Create a new Order
**Endpoint:**  
`POST /api/orders`

**Request Headers:**
```json
{
  "Content-Type": "application/json",
  "Authorization": "Bearer dummy-token-12345"
}
```

**Request Body:**
```json
{
  "userId": 105,
  "totalAmount": 250.0,
  "status": "Pending"
}
```

**Response:**
```json
{
  "message": "Order created successfully",
  "order": {
    "orderId": 5,
    "userId": 105,
    "totalAmount": 250.0,
    "status": "Pending"
  }
}
```

---

### 🟢 2. Get All Orders
**Endpoint:**  
`GET /api/orders`

**Response:**
```json
[
  { "orderId": 1, "userId": 101, "totalAmount": 150.0, "status": "Delivered" },
  { "orderId": 2, "userId": 102, "totalAmount": 200.0, "status": "Pending" }
]
```

---

### 🟢 3. Get Order by ID
**Endpoint:**  
`GET /api/orders/{orderId}`

**Example:**  
`GET /api/orders/1`

**Response:**
```json
{
  "orderId": 1,
  "userId": 101,
  "totalAmount": 150.0,
  "status": "Delivered"
}
```

---

### 🟢 4. Get Orders by User ID
**Endpoint:**  
`GET /api/orders/user/{userId}`

**Example:**  
`GET /api/orders/user/101`

**Response:**
```json
[
  { "orderId": 1, "userId": 101, "totalAmount": 150.0, "status": "Delivered" },
  { "orderId": 3, "userId": 101, "totalAmount": 50.0, "status": "Shipped" }
]
```

---

### 🟢 5. Update an Order
**Endpoint:**  
`PUT /api/orders/{orderId}`

**Example:**  
`PUT /api/orders/1`

**Request Body:**
```json
{
  "status": "Processing"
}
```

**Response:**
```json
{
  "message": "Order updated successfully",
  "order": {
    "orderId": 1,
    "userId": 101,
    "totalAmount": 150.0,
    "status": "Processing"
  }
}
```

---

### 🟢 6. Delete an Order
**Endpoint:**  
`DELETE /api/orders/{orderId}`

**Example:**  
`DELETE /api/orders/2`

**Response:**
```json
{
  "message": "Order deleted successfully"
}
```

---

## 📌 Error Handling
### ❌ Invalid Token
```json
{
  "message": "Unauthorized: Invalid or missing Bearer token"
}
```

### ❌ Order Not Found
```json
{
  "message": "Order not found"
}
```

---

## 🛠 Built With
- **Spring Boot** - Backend Framework
- **Maven** - Dependency Management
- **Java** - Programming Language
