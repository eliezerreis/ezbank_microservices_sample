# Microservices Architecture Showcase

This project demonstrates the implementation of a modern **Microservices Architecture** using a variety of industry-standard technologies. The goal is to showcase scalable, secure, and fault-tolerant services built with **Java**, **Spring Boot**, and **Spring Cloud**, running on **Docker** and **Kubernetes**.

**This is still a ongoing project**

## Key Technologies & Features

- **Microservices Architecture**:  
  A set of independently deployable services, each handling specific business logic, built with **Spring Boot**.

- **Spring Cloud**:  
  Utilizes Spring Cloud components for service discovery (Eureka), centralized configuration (Spring Cloud Config), and resilient service-to-service communication (Feign, Ribbon, Hystrix).

- **Docker**:  
  All services are containerized using **Docker**, ensuring consistency across development, testing, and production environments.

- **Kubernetes & Helm**:  
  Orchestration of services with **Kubernetes** for automated scaling, self-healing, and load balancing. **Helm** charts simplify deployments and management.

- **Kafka**:  
  **Kafka** is used for asynchronous communication between services, enabling event-driven architecture and decoupled service interactions.

- **MongoDB**:  
  A NoSQL database used for storing unstructured or semi-structured data with high scalability and performance.

- **Microservices Security**:  
  **Spring Security**, **OAuth2**, and **JWT** are integrated for secure API communication, authentication, and authorization across services.

- **Event-Driven Architecture**:  
  Real-time, event-driven processing is handled with **Kafka** to ensure decoupled and reliable service interactions.

## Additional Features

- **CI/CD Pipeline**:  
  Automated build, test, and deployment pipelines ensuring smooth and consistent delivery to production.

- **Monitoring & Logging**:  
  Integration with **Prometheus** and **Grafana** for monitoring, and **ELK Stack** for centralized logging, enabling efficient troubleshooting and tracing.

## Use Cases

This project is ideal for demonstrating scalable and resilient applications like e-commerce platforms, order processing systems, and real-time data pipelines.

## Microservices

List of microservices **currently** available on our EzBank. Each microservice is built and containerized using different methods for showcasing various containerization techniques.


### 1. **Accounts Service**
- **Description:** This service simulates the creation of a new bank customer and the setup of their account. Each customer is identified by a unique mobile number, and only one account can be created per mobile number.

### 2. **Card Service**
- **Description:** Similar to the Accounts service, each mobile number can only have one associated card. This service is responsible for managing the creation of bank cards.

### 3. **Loans Service**
- **Description:** This service manages loan offerings and tracks the loans a customer can take within EzBank.
