# 📦 Product Management System - E2E & API Testing Portfolio

![Java](https://shields.io)
![Spring Boot](https://shields.io)
![Selenium](https://shields.io)
![REST Assured](https://shields.io)
![Cucumber](https://shields.io)
![JUnit 5](https://shields.io)

This project is a full-stack **Product Management Application** featuring automated **End-to-End (E2E) UI & REST API Testing Suites**. It demonstrates modern test automation practices using Java 21, Selenium WebDriver, REST Assured, Cucumber BDD, and Spring Boot.

---

## 🚀 Technologies Used

* **Language:** Java 21
* **Framework:** Spring Boot (Spring Data JPA, Web, Thymeleaf, H2 Database)
* **Testing & Automation:**
  * **Selenium WebDriver** (UI E2E Automation)
  * **REST Assured** (API Integration Testing)
  * **Cucumber BDD** (Gherkin feature scenarios)
  * **JUnit 5 Platform Suite** (Test Runner Engine)
  * **WebDriverManager** (Automated Browser Driver Management)

---

## 📌 Project Architecture

The testing suite is structured using the **Page Object Pattern (POM)** and clean separation for API/E2E tests:

```text
src/test/
└── java/
    └── com/portfolio/product_management/
        ├── api/        # REST Assured API test suites
        └── e2e/        # Cucumber & Selenium UI test suites
            ├── pages/      # Page Object Classes
            ├── runners/    # Cucumber TestRunner
            └── steps/      # Step Definitions linking Gherkin to Selenium
```

---

## 📋 Prerequisites

Ensure you have the following installed before running the project:

* **JDK 21** or higher
* **Apache Maven 3.8+** (or use the included `./mvnw` wrapper)
* **Google Chrome** browser

---

## ⚙️ How to Run

### 1. Clone the Repository
```bash
git clone https://github.com
cd QA-Java-Portfolio/product-management
```

### 2. Run Application Locally
```bash
./mvnw spring-boot:run
```

### 3. Run Automated Tests
```bash
# Run UI E2E Tests (Cucumber)
./mvnw test -Dtest=TestRunner

# Run API Tests (REST Assured)
./mvnw test -Dtest=ProductApiTest
```
