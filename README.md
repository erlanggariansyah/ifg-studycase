# IFG Studycase

## Arsitektur

Terdiri dari 2 service:

- Producer Service
- Order Service
- Kafka

Flow:

Client → Producer API → Kafka Topic `orders` → Consumer → Topic `processed` → Jika gagal setelah retry → Topic `orders-dlq`

---

# Teknologi

- Java 17
- Quarkus
- Apache Kafka
- Docker
- Docker Compose
- SmallRye Reactive Messaging

---

# How to run

1. Clone project
git clone https://github.com/erlanggariansyah/ifg-studycase.git
cd ifg-studycase

2. Build service
cd producer
mvn clean package
cd ..
cd consumer
mvn clean package
cd ..

3. Build project
docker compose up --build

# API
- Endpoint POST /api/v1/orders
- Example Request:
curl --location 'http://localhost:8080/api/v1/orders' \
--header 'Content-Type: application/json' \
--data '{
  "orderId":"ORD-001",
  "customerName":"Erlangga",
  "amount":2000
}'
