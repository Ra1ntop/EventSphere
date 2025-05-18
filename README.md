<h1 align="center">🎉 EventSphere</h1>
<p align="center">
  <strong>Modern Microservices Event Management Platform</strong>
</p>

<p align="center">
  <a href="https://github.com/your-username/eventsphere/actions">
    <img src="https://img.shields.io/github/actions/workflow/status/your-username/eventsphere/ci.yml?branch=main&style=flat-square&color=green" alt="CI Status"/>
  </a>
  <a href="https://img.shields.io/docker/pulls/your-username/eventsphere">
    <img src="https://img.shields.io/docker/pulls/your-username/eventsphere?style=flat-square&color=blue" alt="Docker Pulls"/>
  </a>
  <a href="https://img.shields.io/github/license/your-username/eventsphere">
    <img src="https://img.shields.io/github/license/your-username/eventsphere?style=flat-square&color=orange" alt="License"/>
  </a>
  <a href="https://img.shields.io/badge/java-17-orange">
    <img src="https://img.shields.io/badge/java-17-orange?style=flat-square" alt="Java 17"/>
  </a>
  <a href="https://img.shields.io/badge/spring-boot%203-green">
    <img src="https://img.shields.io/badge/spring-boot%203-green?style=flat-square" alt="Spring Boot 3"/>
  </a>
</p>

<p align="center">
  <strong>EventSphere</strong> is a cutting-edge, microservices-based platform for managing events, designed to showcase <strong>mid-to-senior-level</strong> development skills. Powered by <strong>Kafka</strong>, <strong>Redis</strong>, <strong>PostgreSQL</strong>, and <strong>Docker</strong>, it integrates with <strong>Google Calendar</strong> and <strong>Telegram Bot</strong> APIs to deliver a real-world, scalable solution. 🚀
</p>

---

## 🌟 Key Features

- 🧩 **Microservices Architecture**: Modular, scalable, and maintainable.
- ⚡ **Event-Driven Design**: Powered by Apache Kafka for asynchronous workflows.
- 📬 **Real-Time Notifications**: Instant updates via Telegram Bot.
- 📅 **Calendar Integration**: Sync events with Google Calendar using OAuth2.
- 🚀 **High Performance**: Redis caching for fast event retrieval.
- ✅ **Clean Code**: Adheres to SOLID principles with comprehensive tests.
- 🐳 **Containerized**: Fully deployable with Docker and Docker Compose.
- 📜 **Database Migrations**: Managed with Flyway for seamless schema updates.
- 🤖 **CI/CD Ready**: Automated builds and tests with GitHub Actions.

> **Progress**:  
> ![Progress](https://progress-bar.dev/85/?title=Project%20Completion&width=200)

---

## 🧱 Architecture

EventSphere leverages a **microservices architecture** with services communicating via **REST** (through an API Gateway) and **Kafka** for event-driven interactions. Below is the architecture diagram:

```plaintext
+-----------------------+
|      API Gateway      |
| (Spring Cloud, Swagger|
+-----------------------+
            |
            | REST
+-----------------------+
|                       |
|   +---------------+   |   +---------------+
|   | User Service  |   |   | Event Service |
|   | PostgreSQL    |   |   | PostgreSQL    |
|   | Flyway        |   |   | Redis         |
|   |               |   |   | Flyway        |
|   +---------------+   |   +---------------+
|                       |         |
|   +---------------+   |         | Kafka
|   | Auth Service  |   |   +---------------+
|   | PostgreSQL    |   |   |Registration   |
|   | Flyway        |   |   | Service       |
|   | Google OAuth  |   |   | PostgreSQL    |
|   +---------------+   |   | Flyway        |
|                       |   +---------------+
|                       |         |
|                       |         | Kafka
|                       |   +---------------+
|                       |   | Notification   |
|                       |   | Service       |
|                       |   | Telegram Bot  |
|                       |   | Google Calendar|
|                       |   | Redis         |
|                       |   +---------------+
+-----------------------+
```

### 🛠️ Microservices
| Service              | Description                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| **API Gateway**      | Routes requests, handles JWT authentication, and exposes Swagger UI.         |
| **User Service**     | Manages user registration, profiles, and authentication.                    |
| **Auth Service**     | Handles Google OAuth2 and issues JWT tokens.                                |
| **Event Service**    | CRUD operations for events, with Redis caching for performance.             |
| **Registration Service** | Manages event registrations and publishes events to Kafka.                |
| **Notification Service** | Consumes Kafka events, sends Telegram notifications, and syncs with Google Calendar. |

---

## 🚀 Getting Started

### Prerequisites
- 🐳 **Docker** and **Docker Compose**
- ☕ **Java 17** (for local development)
- 🛠️ **Gradle** (for building services)
- 🔑 **Google Cloud Project** with Google Calendar API and OAuth2 credentials
- 🤖 **Telegram Bot Token** from BotFather

### Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/eventsphere.git
   cd eventsphere
   ```

2. **Set Environment Variables**:
   Create a `.env` file in the root directory:
   ```env
   GOOGLE_CLIENT_ID=your-google-client-id
   GOOGLE_CLIENT_SECRET=your-google-client-secret
   TELEGRAM_BOT_TOKEN=your-telegram-bot-token
   POSTGRES_USER=postgres
   POSTGRES_PASSWORD=postgres
   ```

3. **Run with Docker Compose**:
   ```bash
   docker-compose up --build
   ```

4. **Access the API**:
    - 📚 **Swagger UI**: `http://localhost:8080/swagger-ui.html`
    - 🌐 **API Gateway**: `http://localhost:8080`

### Database Migrations
Schemas are managed with **Flyway**, automatically applied on service startup. Migration scripts are located in `src/main/resources/db/migration`.

---

## 📡 API Examples

<details>
<summary>📌 <strong>Register for an Event</strong></summary>

```http
POST /register-event/{eventId}
Authorization: Bearer <JWT_TOKEN>
Content-Type: application/json

{
  "userId": "12345",
  "eventId": "67890"
}
```

**Response**:
```json
{
  "status": "success",
  "registrationId": "98765",
  "message": "Successfully registered for event"
}
```
</details>

<details>
<summary>📌 <strong>Create an Event</strong></summary>

```http
POST /events
Authorization: Bearer <JWT_TOKEN>
Content-Type: application/json

{
  "title": "Tech Meetup",
  "description": "A meetup for tech enthusiasts",
  "eventDate": "2025-06-01T18:00:00Z"
}
```

**Response**:
```json
{
  "eventId": "67890",
  "title": "Tech Meetup",
  "createdAt": "2025-05-18T16:33:00Z"
}
```
</details>

> 📚 Explore all endpoints via **Swagger UI** at `http://localhost:8080/swagger-ui.html`.

---

## 🛠️ Tech Stack

| Category            | Technology                     |
|---------------------|--------------------------------|
| **Backend**         | Java 17, Spring Boot 3, Spring Cloud, Spring Security |
| **Databases**       | PostgreSQL (Flyway), Redis     |
| **Messaging**       | Apache Kafka                   |
| **Containerization**| Docker, Docker Compose         |
| **API Docs**        | OpenAPI/Swagger                |
| **Authentication**  | Google OAuth2, JWT             |
| **Notifications**   | Telegram Bot API, Google Calendar API |
| **Testing**         | Testcontainers, JUnit, Mockito |
| **CI/CD**          | GitHub Actions                 |

---

## 🔄 Workflow Example

1. **User Authentication** 🔑:
    - User logs in via Google OAuth2 through **Auth Service**.
    - Receives a JWT token.

2. **Event Registration** 📝:
    - User registers for an event via **API Gateway**.
    - **Registration Service** validates with **Event Service** and publishes `EVENT_REGISTRATION_CREATED` to Kafka.

3. **Notifications** 📬:
    - **Notification Service** consumes Kafka events.
    - Sends Telegram messages and syncs with Google Calendar.

4. **Caching** ⚡:
    - **Event Service** caches popular events in **Redis**.

---

## 🧪 Running Tests

Each service includes unit and integration tests using **Testcontainers** for Kafka, PostgreSQL, and Redis.

```bash
cd event-service
./gradlew test
```

> **Test Coverage**:  
> ![Test Coverage](https://progress-bar.dev/90/?title=Test%20Coverage&width=200)

---

## 📊 Monitoring (Optional)

Add **Prometheus** and **Grafana** for metrics:
1. Uncomment `prometheus` and `grafana` in `docker-compose.yml`.
2. Access Grafana at `http://localhost:3000`.

---

## 📂 Project Structure

```plaintext
EventSphere/
├── api-gateway/
│   ├── Dockerfile
│   ├── src/
│   └── build.gradle
├── user-service/
├── auth-service/
├── event-service/
├── registration-service/
├── notification-service/
└── docker-compose.yml
```

---

## 🤝 Contributing

We welcome contributions! Follow these steps:
1. Fork the repository.
2. Create a feature branch: `git checkout -b feature/awesome-feature`.
3. Commit your changes: `git commit -m 'Add awesome feature'`.
4. Push to the branch: `git push origin feature/awesome-feature`.
5. Open a Pull Request.

---

## 📜 License

This project is licensed under the [MIT License](LICENSE).

---

## 🌟 Why This Project Stands Out

EventSphere showcases:
- 🧩 **Microservices Expertise**: Scalable, modular design.
- ⚡ **Event-Driven Systems**: Mastery of Kafka and asynchronous workflows.
- 🌐 **API Integrations**: Google Calendar and Telegram Bot.
- 🛠️ **Production-Ready**: Clean code, tests, logging, and CI/CD.

<p align="center">
  <strong>Built with 💻 and ☕ by [Your Name]</strong>
</p>