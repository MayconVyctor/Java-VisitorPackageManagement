# Java-VisitorPackageManagement 🏢

> A robust, full-stack condominium management system designed to streamline visitor access, parcel tracking, and resident management.

## 📖 About the Project

Java-VisitorPackageManagement is a web application built to modernize the daily operations of residential buildings. It provides a secure and efficient way to manage the flow of people and goods, ensuring a collaborative and safe environment for residents and staff. 

This project was developed focusing on enterprise-level architectural patterns, utilizing a robust Java backend, real-time event streaming, and a component-based UI.

## 🚀 Technologies & Stack

*   **Backend:** Java 17, Spring Boot 3
*   **Data Persistence:** Spring Data JPA, Oracle DB
*   **Security:** Spring Security (Role-Based Access Control)
*   **Messaging / Event Streaming:** Apache Kafka
*   **Frontend:** JSF (Jakarta Server Faces), PrimeFaces, JoinFaces
*   **Infrastructure:** Docker & Docker Compose

## ✨ Key Features

*   **Role-Based Dashboards:** Distinct interfaces and permissions for Admins (Managers) and Porters.
*   **Resident Management:** Complete CRUD for apartments, residents, and contact information.
*   **Visitor Access Control:** Real-time logging of visitor entries and exits.
*   **Parcel Notifications:** Event-driven architecture using **Kafka** to asynchronously process and notify residents when packages arrive at the front desk.
*   **Modern UI:** Responsive and interactive components powered by PrimeFaces.

## 🛠️ Getting Started

### Prerequisites

*   Java 17 or higher
*   Maven
*   Docker & Docker Compose (for running Oracle DB and Kafka locally)

### Installation & Setup

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/MayconVyctor/Java-VisitorPackageManagement.git](https://github.com/MayconVyctor/Java-VisitorPackageManagement.git)
    cd Java-VisitorPackageManagement
    ```

2.  **Spin up the infrastructure (Oracle DB & Kafka):**
    ```bash
    docker-compose up -d
    ```

3.  **Build the application:**
    ```bash
    mvn clean install
    ```

4.  **Run the application:**
    ```bash
    mvn spring-boot:run
    ```

5.  **Access the application:**
    Open your browser and navigate to `http://localhost:8080`

