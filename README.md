# Microservices Architecture Showcase

This project demonstrates the implementation of a modern **Microservices Architecture** using a variety of industry-standard technologies. The goal is to showcase scalable, secure, and fault-tolerant services built with **Java**, **Spring Boot**, and **Spring Cloud**, running on **Docker** and **Kubernetes**.

**This is still a ongoing project**

## Key Technologies & Features

- **Microservices Architecture**:  
  A set of independently deployable services, each handling specific business logic, built with **Spring Boot**.

- **Spring Cloud**:  
  Utilizes Spring Cloud components for service discovery (Eureka and Spring Load Balancer), centralized configuration (Spring Cloud Config), and resilient service-to-service communication (Feign and Resilient4J), and Spring Cloud BUS.

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

- **Microservices Resiliency**

  To ensure fault tolerance and system reliability, various resiliency patterns have been implemented across the microservices:

  - **Circuit Breaker**: Integrated **Spring Cloud Circuit Breaker** across all services to prevent cascading failures and allow the system to recover gracefully when a service is unavailable.

  - **API Gateway Circuit Breaker**: Added circuit breakers at the API Gateway level to ensure that if one service fails, requests to that service are gracefully handled without affecting other services.

  - **HTTP Timeout with Fallback**: Implemented configurable HTTP timeouts in all services to avoid prolonged waits in case of service failures. Each service has a fallback mechanism to handle timeouts and return default responses.

  - **Retry Pattern**: Applied the retry pattern in independent services like the **Accounts Service** to retry failed requests a specified number of times, allowing the system to recover from temporary glitches.

  - **Rate Limiting with Redis**: In the **Loans Service**, **Redis** is used to implement rate limiting, ensuring that the system can handle high request volumes without overwhelming any particular service.

These resiliency measures ensure that the system can continue to function even in the event of temporary failures, providing a seamless experience to users.


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
