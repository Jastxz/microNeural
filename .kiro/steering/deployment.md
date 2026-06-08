---
inclusion: fileMatch
fileMatchPattern: ".woodpecker*,Dockerfile*,docker-compose*,application.yml"
---

# Deployment — microNeural

## CI/CD
- Woodpecker CI pipeline (`.woodpecker.yaml`)

## Local
- `./mvnw spring-boot:run`
- Requires eurekaServer on port 8761
- Depends on neural-lib being installed in local Maven repo (`mvn install` in neural-lib first)

## Startup Order
1. eurekaServer
2. microGateway
3. This service
