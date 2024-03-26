# BC Stock Finnhub

BC Stock Finnhub is a demo project developed using Spring Boot for retrieving stock information using the Finnhub API.

## Features

- Retrieve stock information using the Finnhub API.
- Cache stock symbols using Redis.

## Technology Stack

- Spring Boot 3.2.2
- Spring Boot Starter Web
- Spring Boot DevTools
- Lombok
- Spring Boot Starter Test
- Spring Boot Starter Data Redis
- Swagger Annotations
- Spring Data Commons
- ModelMapper
- Spring Boot Starter Validation
- Spring Boot Starter Actuator

## How to Run

1. Clone the project locally.
2. Run `mvn spring-boot:run` in the command line.
3. Access `http://localhost:8083` in your browser.

## Configuration

1. Regisrer an account on [www.finnhub.io](https://finnhub.io)
2. Create your own token
3. Replace your token in applocation.yml
