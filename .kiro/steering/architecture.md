---
inclusion: always
---

# Architecture — microNeural

## Stack
- **Framework:** Spring Boot + Spring Cloud (Eureka client)
- **Language:** Java
- **Build:** Maven
- **Domain:** Neural network operations as a microservice

## Module Boundaries (from graphify: 63 nodes, 89 edges, 15 communities)
- God nodes: `RequestsLimitFilter` (9 edges), `NeuralController` (6), `MundoRequest` (6)
- REST API exposing neural network inference/training operations
- `RequestsLimitFilter` — Rate limiting filter (shared pattern across microservices)
- `NeuralController` — HTTP endpoints for neural computations
- `MundoRequest` — DTO representing world state for neural evaluation
- Registered with Eureka for discovery by other services

## Dependency Rules
- Controller (`NeuralController`) → Service → neural logic (layered)
- `RequestsLimitFilter` protects all endpoints globally
- Uses neural-lib as library dependency for core algorithms
- `MundoRequest` is the API contract DTO
- No direct imports from other microservices

## Ecosystem
Part of cluster: eurekaServer, microGateway, microPrimeNumbers, microTracking, micro-adversarial-search
