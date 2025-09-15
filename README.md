# 🚀 Spring Boot Demo Application

This is a **Spring Boot 3.5.5** demo project using:
- **Spring Data JPA** (with Microsoft SQL Server)
- **Spring Web**
- **Spring Boot Validation**
- **MapStruct** for DTO mapping
- **Lombok** for boilerplate reduction
- **(Optional)** Spring Security + OAuth2 Resource Server (JWT)

The project demonstrates:
- A base `BaseEntity` with audit fields (`createdAt`, `updatedAt`, `userId`)
- A sample `Employee` entity with CRUD capabilities
- Pre-configured Hibernate and logging
- A ready-to-enable `SecurityConfig` for OAuth2 JWT-based security

---

## 📂 Project Structure

```

src/main/java/com/example/demo/
├── core/
│   ├── config/
│   │   └── SecurityConfig.java   # 🔒 Security setup (commented until enabled)
│   └── contract/
│       └── BaseEntity.java       # Common audit fields
├── employee/
│   └── dao/
│       └── entity/
│           └── Employee.java     # Example entity
└── DemoApplication.java          # Main Spring Boot class

````

---

## ⚙️ Prerequisites

- **Java 17+**
- **Maven 3.8+**
- **Docker** (if running SQL Server in a container)

---

## 🐳 Run SQL Server in Docker

```bash
docker network create db

docker run -itd --rm --network db \
  -e "ACCEPT_EULA=Y" \
  -e "MSSQL_SA_PASSWORD=YourStrong!Passw0rd" \
  -p 1433:1433 \
  --name mssql \
  --mount type=bind,source=/your/local/path/mssql,target=/var/opt/mssql/data \
  mcr.microsoft.com/mssql/server:2022-latest
````

---

## 🔧 Configuration

The default configuration is in `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:sqlserver://localhost:1433;databaseName=demo;encrypt=false
    username: sa
    password: YourStrong!Passw0rd
    driver-class-name: com.microsoft.sqlserver.jdbc.SQLServerDriver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate.format_sql: true
      hibernate.transaction.jta.platform: org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform
      hibernate.dialect: org.hibernate.dialect.SQLServerDialect
```

---

## ▶️ Running the App

```bash
mvn spring-boot:run
```

Visit:

* `http://localhost:8080/actuator/health` → to verify app is up.
* (Add controllers to expose your REST APIs)

---

## 🔒 Enabling Security (Optional)

1. Go to `core/config/SecurityConfig.java`
2. Uncomment `@Configuration` and `@EnableWebSecurity`
3. Update `application.yml` with your JWT issuer or public key

```yaml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: https://your-auth-server/.well-known/openid-configuration
```

Restart the app and your endpoints will require JWT tokens.

---

## 🛠️ Build and Package

```bash
mvn clean package
```

The JAR will be available at `target/demo-0.0.1-SNAPSHOT.jar`:

```bash
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

---

## 🧰 Useful Commands

| Command               | Description                              |
| --------------------- | ---------------------------------------- |
| `mvn spring-boot:run` | Run the app in dev mode                  |
| `mvn clean package`   | Build executable JAR                     |
| `docker ps`           | Check if SQL Server container is running |
| `docker logs mssql`   | View SQL Server logs                     |

---

## 📌 Roadmap

* [x] Setup Spring Boot project with JPA + SQL Server
* [x] Add BaseEntity with audit fields
* [x] Configure Hibernate + logging
* [x] Prepare SecurityConfig (disabled by default)
* [ ] Add sample REST Controllers
* [ ] Enable OAuth2 security with JWT
* [ ] Add integration tests

---

## 📄 License

This project is licensed under the MIT License — you are free to use, modify, and distribute.

---

## 🙌 Contributing

Feel free to open issues or submit PRs to improve this demo project.

#springboot #jpa #sqlserver #mapstruct #lombok #oauth2 #jwt #demo #java #maven
