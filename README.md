# View Management Service

## 1. Overview

The **View Management Service** is a Spring Boot application responsible for managing views and schemas in Trino. It processes various types of events and, based on the event type and its properties, creates, updates, or deletes the corresponding Trino views in different catalogs.

This application is built with Java 21, uses Maven for dependency management, and follows a modular, extensible, and event-driven architecture.

## 2. Architecture

The service is designed around a robust event-driven architecture that leverages the **Strategy design pattern** to handle different event types in a decoupled and scalable manner.

-   **REST API**: The application exposes a single `POST /events` endpoint to receive all incoming events.
-   **Polymorphic Deserialization**: Jackson's polymorphic deserialization (`@JsonTypeInfo` and `@JsonSubTypes`) is used to automatically deserialize the incoming JSON payload into the correct `Event` subclass based on the `eventType` property.
-   **Event Handlers (Strategy Pattern)**: For each event type, there is a dedicated `EventHandler` implementation. The `ViewManagementService` acts as a context that delegates the processing of an event to the appropriate handler (strategy) based on its type. This makes it easy to add new event types without modifying the core service logic.

## 3. Event Types

The service is designed to handle the following event types:

1.  **Resource Metadata Event** (`RESOURCE_METADATA`): Handles events related to resource metadata changes.
2.  **DAP Event** (`DAP`): Handles events from the DAP (Data Access Platform).
3.  **Derived Rule Event** (`DERIVED_RULE`): Handles events related to derived business rules.
4.  **Migration View Event** (`MIGRATION_VIEW`): Handles events for creating or managing migration-related views.
5.  **Backup Table View Event** (`BACKUP_TABLE_VIEW`): Handles events for creating views for backup tables.

## 4. API Usage

To process an event, send a `POST` request to the `/events` endpoint. The request body must be a JSON object containing the `eventType` and the specific properties for that event.

**Endpoint**: `POST /events`

**Headers**: `Content-Type: application/json`

### Example Payloads

Here are some example JSON payloads for different event types.

#### Resource Metadata Event

```json
{
  "eventType": "RESOURCE_METADATA",
  "resourceName": "com.example.resource.MyResource"
}
```

#### DAP Event

```json
{
  "eventType": "DAP",
  "dapName": "com.example.dap.MyDap"
}
```

#### Derived Rule Event

```json
{
  "eventType": "DERIVED_RULE",
  "ruleName": "com.example.rule.MyRule"
}
```

## 5. Project Structure

The project follows a standard layered architecture, with key components organized into the following packages:

-   `com.viewmanagementservice.controller`: Contains the REST controllers that expose the API endpoints.
-   `com.viewmanagementservice.service`: Contains the core business logic and service interfaces/implementations.
-   `com.viewmanagementservice.handler`: Contains the `EventHandler` implementations for each event type (the Strategy pattern).
-   `com.viewmanagementservice.dto`: Contains the Data Transfer Objects (DTOs) for the different event types.
-   `com.viewmanagementservice.model`: Contains domain models and enums, such as `EventType`.

## 6. How to Build and Run

### Prerequisites

-   Java 21
-   Maven

### Build the Application

To build the project and generate the executable JAR file, run the following command from the root directory:

```bash
mvn clean install
```

### Run the Application

Once the build is complete, you can run the application using the following command:

```bash
java -jar target/view-management-service-0.0.1-SNAPSHOT.jar
```

## 7. How to Run Tests

To run the unit tests for the application, execute the following command:

```bash
mvn clean test
```
