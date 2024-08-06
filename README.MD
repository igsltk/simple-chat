# SimpleChat - Simple WebApp(Chat) on microservices

## What I used
* Java 17
* Spring Boot 3
* RabbitMQ
* Redis
* Eureka (Server & Client)
* WebSockets
* PostgreSQL
* Lombok
* Hibernate
* Docker (Docker Compose)
* Docker Registry
* Kubernetes (k8s)

## Overview
This project is a microservices-based architecture designed to facilitate efficient communication and message routing. The system is composed of four main services: app, chats, eureka, and gateway. Each service has a distinct role, contributing to the overall functionality and robustness of the application.

### Microservices Components

#### 1. App Service
* Description: The app service is the central component of the system. It handles the core business logic and is responsible for sending messages.
* Functionality: When a message needs to be sent, the app service publishes it to RabbitMQ, a messaging broker, which ensures reliable message delivery to the intended recipient service.

#### 2. Chats Service
* Description: The chats service is dedicated to managing and processing chat messages.
* Functionality: It consumes messages from RabbitMQ that are sent by the app service. The chats service handles these messages appropriately, such as storing them, updating chat history, or triggering further actions within the chat context. By the way, this service caching messages in Redis.

#### 3. Eureka Service
* Description: The eureka service acts as a service registry, allowing other microservices to discover each other.
* Functionality: By registering each service instance with Eureka, the system maintains an updated directory of all running services and their instances. This dynamic discovery is crucial for ensuring that messages are routed correctly and services can communicate seamlessly.

#### 4. Gateway Service
* Description: The gateway service serves as an entry point for all client requests and manages routing and load balancing.
* Functionality: It routes incoming requests to the appropriate microservice instance based on the request's nature and current system load. The gateway's load balancing capabilities help distribute traffic evenly across service instances, ensuring optimal performance and reliability.

### Communication Flow

#### 1. Message Sending:
* The `app` service sends messages by publishing them to RabbitMQ.
* RabbitMQ acts as an intermediary, ensuring messages are delivered to the `chats` service.

#### 2. Service Discovery and Routing:
* All services register with the `eureka` service, enabling them to discover each other dynamically.
* The `gateway` service, upon receiving client requests, consults the `eureka` registry to determine the optimal service instance for routing the request.

#### 3. Load Balancing:
* The `gateway` service implements load balancing, distributing incoming traffic among available instances to enhance system stability and performance.

### Benefits
* Scalability: The microservices architecture allows individual services to be scaled independently, enhancing the system's ability to handle varying loads.
* Flexibility: Each microservice can be developed, deployed, and maintained independently, allowing for greater agility in updates and improvements.
* Resilience: The use of RabbitMQ for messaging ensures reliable communication between services, even in cases of service failure or downtime.
* Efficient Routing: The gateway service's routing and load balancing capabilities ensure efficient use of resources and optimal response times.

### Conclusion
This project leverages a microservices architecture to create a robust, scalable, and efficient system for managing and routing messages. By integrating services like RabbitMQ, Eureka, and a sophisticated gateway, the system ensures reliable communication, dynamic service discovery, and effective load balancing, all contributing to a high-performance application environment.