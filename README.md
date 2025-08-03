# 🚀 Scalable Todo Web App

A full-stack, event-driven, scalable Todo Web Application built using:

- **Frontend:** Next.js (TypeScript)
- **Backend:** Spring Boot (Java)
- **Message Queue:** Apache Kafka
- **Database:** PostgreSQL
- **Containerization:** Docker and Docker Compose

---

## 🔧 Architecture

```
[ Next.js Client ] ↔ [ Spring Boot API ] → [ Kafka Topic ] → [ Kafka Consumer ] → [ PostgreSQL DB ]
```

---

## 📦 Project Structure

```
.
├── frontend/        # Next.js App (TypeScript)
├── backend/         # Spring Boot REST API and Kafka Producer
├── consumer/        # Kafka Consumer Service
├── db/              # PostgreSQL Data
├── docker-compose.yml
├── k6-tests/        # k6 performance test scripts
└── README.md
```

---

## 🛠️ Setup Instructions

### 🐳 Start All Services

```bash
docker-compose up --build
```

Access:

- Frontend: [http://localhost:3000](http://localhost:3000)
- Backend: [http://localhost:8080/api/todos](http://localhost:8080/api/todos)
- PostgreSQL: `localhost:5432`, user: `postgres`, password: `secret`
- Kafka: `localhost:9092`

---

## ✅ Features

- CRUD operations on todos
- Kafka event-driven architecture
- Async database persistence
- Modular, containerized services
- Easily scalable horizontally

---

# 🧪 Scalability Testing

## 📋 Test Plan Overview

| Test ID | Test Case Description                           | Tool   |
| ------- | ----------------------------------------------- | ------ |
| TC001   | Create Todos under 1000 concurrent requests     | k6     |
| TC002   | Kafka message throughput and consumer lag       | Custom |
| TC003   | Simulate sudden traffic spike                   | k6     |
| TC004   | Read latency with 1 million todos               | Custom |
| TC005   | Scale backend and test load balancing           | Docker |
| TC006   | Kafka partition scaling with multiple consumers | Docker |
| TC007   | SSR performance on high load (Next.js)          | k6     |
| TC008   | Kill Kafka broker and recover                   | Chaos  |

---

## 🧪 Run k6 Load Tests

### 🔹 Install k6

```bash
brew install k6  # MacOS
sudo apt install k6  # Ubuntu
```

### 🔹 Sample Test (k6-tests/load-post-todos.js)

```js
import http from "k6/http";
import { check, sleep } from "k6";

export const options = {
  vus: 100, // virtual users
  duration: "30s",
};

export default function () {
  const payload = JSON.stringify({
    title: `Test Todo ${__VU}-${__ITER}`,
  });

  const headers = { "Content-Type": "application/json" };

  const res = http.post("http://localhost:8080/api/todos", payload, {
    headers,
  });

  check(res, {
    "status is 200": (r) => r.status === 200,
  });

  sleep(1);
}
```

### 🔹 Run the Test

```bash
k6 run k6-tests/load-post-todos.js
```

---

## 📈 Monitoring & Scaling

### 🔹 Docker Stats

```bash
docker stats
```

### 🔹 Scale Services (Example)

```bash
docker-compose up --scale backend=3 --scale consumer=3
```

### 🔹 Kafka Monitoring (Optional Tools)

- [Kafka Lag Exporter](https://github.com/lightbend/kafka-lag-exporter)
- [Confluent Control Center](https://www.confluent.io/product/control-center/)

---

## 🗃️ PostgreSQL Schema

```sql
CREATE TABLE todos (
    id UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    completed BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);
```

---

## 💡 Future Enhancements

- JWT-based Authentication
- Role-based authorization
- Kafka dead letter queues
- Kubernetes deployment with Helm
- Redis caching for reads

---

## 👨‍💻 Authors

- Obed Otto – Full Stack Architecture & Design
- Contributors welcome!

---

## 📄 License

This project is licensed under the MIT License.
