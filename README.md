# Proposal Service

A Java/Spring Boot portfolio prototype for registering proposals and notifying downstream services through RabbitMQ.

## What it demonstrates

- REST endpoints to create and list proposals
- PostgreSQL persistence through Spring Data JPA
- DTO mapping with MapStruct
- RabbitMQ fan-out exchange for pending-proposal notifications
- Integration state is recorded when message publishing fails

## Technology

- Java 17
- Spring Boot 3.3
- Spring Web, Spring Data JPA and Spring AMQP
- PostgreSQL and RabbitMQ
- MapStruct and Maven Wrapper

## Run locally

Requires JDK 17, a PostgreSQL database named `propostadb`, and RabbitMQ running locally.

Set the database connection for your environment:

```bash
export DB_URL="jdbc:postgresql://localhost:5434/propostadb"
export DB_USERNAME="postgres"
export DB_PASSWORD="your-local-password"
```

The local RabbitMQ defaults are `localhost:5672` with the standard `guest` credentials. Override them with `RABBITMQ_HOST`, `RABBITMQ_PORT`, `RABBITMQ_USERNAME` and `RABBITMQ_PASSWORD` when needed. The exchange name can be set with `PROPOSAL_PENDING_EXCHANGE`.

```bash
./mvnw test
./mvnw spring-boot:run
```

## API

- `POST /proposta` — create a proposal and publish a pending-proposal notification
- `GET /proposta` — list stored proposals

This is a portfolio prototype. The default schema setting is intended for local development; production use should add a versioned database migration strategy and deployment-specific configuration.
