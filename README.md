# Scalable Ecommerce Platform

A Java and Spring Boot ecommerce backend organized as independently buildable services. The repository currently contains user management, product catalog, cart and order management, payment processing, an API gateway, and a shared microservice library.

## Services

| Component | Default port | Responsibility |
| --- | ---: | --- |
| `user-management-service` | 5000 | Registration, login, sessions, profiles, and addresses |
| `product-service` | 5001 | Product catalog and search; includes a Fake Store API client |
| `payment-service` | 5002 | Payment operations and Razorpay integration |
| `order-service` | 5003 | Cart, checkout, and order management |
| `cloud-api-gateway-service` | 8081 | Routes API requests and applies JWT filtering |
| `microsvc-lib` | — | Shared response models, exceptions, logging, and utilities |

The services use MySQL for relational data, Redis for sessions/cache-related operations, and MongoDB for cart details. Product and payment services also integrate with external APIs. Each component has its own Maven project; there is no root Maven aggregator at this time.

## Requirements

- Java 17
- Maven (or the included Maven Wrapper in each project)
- MySQL, Redis, and MongoDB running locally
- Razorpay credentials to use payment features

The current service configurations expect a MySQL database named `scaler_ecommerce`, Redis on `localhost:6379`, and MongoDB on `localhost:27017`. Database schema/table creation scripts are not included, so configure the database before starting the services.

## Build

Build and install the shared library first, since the services depend on it:

```bash
cd microsvc-lib
./mvnw clean install
```

Then build each service from its own directory:

```bash
cd ../user-management-service && ./mvnw clean package
cd ../product-service && ./mvnw clean package
cd ../order-service && ./mvnw clean package
cd ../payment-service && ./mvnw clean package
cd ../cloud-api-gateway-service && ./mvnw clean package
```

On Windows, use `mvnw.cmd` instead of `./mvnw`.

## Run locally

Start MySQL, Redis, and MongoDB first. Configure service settings and credentials for your environment, then run each application in a separate terminal:

```bash
cd microsvc-lib && ./mvnw install
```

```bash
cd user-management-service && ./mvnw spring-boot:run
cd product-service && ./mvnw spring-boot:run
cd order-service && ./mvnw spring-boot:run
cd payment-service && ./mvnw spring-boot:run
cd cloud-api-gateway-service && ./mvnw spring-boot:run
```

The gateway is configured to listen on port `8081` and forward to the services on ports `5000`–`5003`. Service URLs, database settings, JWT configuration, and Razorpay credentials are currently stored in application configuration files; use environment-specific external configuration and do not commit real secrets.

## API overview

The gateway routes user (`/user/**`), product/search (`/product/**`, `/search/**`), payment (`/payment/**`), and order (`/order/**`) requests. The controllers provide operations for:

- User registration, login/logout, profiles, password reset, and addresses
- Product listing, lookup, categories, CRUD operations, and search
- Cart updates, cart retrieval, and checkout
- Order lookup and status/payment updates
- Payment link generation, payment lookup, retry, and webhook handling

See the individual service source controllers and service READMEs for request/response details. Note that the product controller currently maps `/products/**`, while the gateway route is configured for `/product/**`; align these paths before relying on product requests through the gateway.

## Tests

Run a service's tests from its directory with `./mvnw test`. The current test suites primarily contain Spring application context-load tests; broader API and integration coverage is still needed.