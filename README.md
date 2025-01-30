# Weather-Shopper---Automation-Testing

This repository contains automated test scripts for the Weather Shopper website. The project is designed to test the website's functionality for shopping based on weather conditions using Java, Cucumber, Selenium WebDriver, and JUnit 5.

---

## Project Overview

The Weather Shopper website is an e-commerce platform where users can purchase moisturizers or sunscreens based on the current weather conditions. The test automation scripts validate various workflows and scenarios to ensure the website functions as expected.

---

## Features Tested

### 1. **Weather-Based Shopping**
- Automatically determine the weather condition.
- Navigate to the appropriate category:
    - Moisturizers (for temperatures below 19°C).
    - Sunscreens (for temperatures above 34°C).

### 2. **Product Selection**
- Add products to the cart based on specific criteria:
    - Sunscreens:
        - Select the least expensive SPF-50 sunscreen.
        - Select the least expensive SPF-30 sunscreen.
    - Moisturizers:
        - Select the least expensive Aloe-based moisturizer.
        - Select the least expensive Almond-based moisturizer.

### 3. **Cart Verification**
- Verify that the shopping cart displays the correct items with accurate prices.

### 4. **Payment Workflow**
- Automate the payment process using Stripe test card numbers.
- Handle scenarios where the payment fails 5% of the time (retry mechanism).

### 5. **Payment Success Validation**
- Confirm successful payment by verifying the receipt or success message.

---

## Tools and Technologies Used
- **Programming Language**: Java
- **Test Framework**: Cucumber
- **Automation Tool**: Selenium WebDriver
- **Test Runner**: JUnit 5

---

## Installation

1. **Clone the Repository**
   ```bash
   git clone https://github.com/ChaymaeBenayad/Weather-Shopper---Automation-Testing
   ```

2. **Import the Project**
    - Open the project in your preferred IDE (e.g., IntelliJ IDEA or Eclipse).

3. **Install Dependencies**
    - This project uses essential dependencies for Selenium WebDriver, Cucumber for behavior-driven testing, and JUnit 5 for test execution.
    - WebDriverManager is included to handle browser driver management automatically.
    - Ensure your `pom.xml` file includes these dependencies for smooth execution.

4. **Setup WebDriver**
    - This project uses **WebDriverManager** from `io.github.bonigarcia` to automate driver setup.
    - No need to manually download WebDriver executables.
    - WebDriverManager automatically detects and manages the appropriate browser driver.

---

## How to Run the Tests

1. **Run Tests from Command Line**:
   ```bash
   mvn test
   ```

2. **Run Tests from IDE**:
    - Locate the test runner file (e.g., `Runner.java`) and execute it.

3. **Generate Reports**:
    - Use Cucumber's HTML report plugin to generate detailed execution reports.

---

## Project Structure

```
weather-shopper-automation
├── src
│   ├── test
│   │   ├── java
│   │   │   ├── configuration
│   │   │   │   ├── ConfigFileReader.java
│   │   │   │   ├── SetupTearDown.java
│   │   │   ├── pages
│   │   │   │   ├── CartPage.java
│   │   │   │   ├── ConfirmationPage.java
│   │   │   │   ├── MoisturizersPage.java
│   │   │   │   ├── ProductsPage.java
│   │   │   │   ├── SunscreensPage.java
│   │   │   │   ├── WelcomePage.java
│   │   │   ├── runner
│   │   │   │   ├── Runner.java
│   │   │   ├── steps
│   │   │   │   ├── CartSteps.java
│   │   │   │   ├── ConfirmationSteps.java
│   │   │   │   ├── MoisturizersSteps.java
│   │   │   │   ├── ProductSteps.java
│   │   │   │   ├── SunscreenProductsSteps.java
│   │   │   │   ├── WelcomeSteps.java
│   │   │   ├── utils
│   │   │   │   ├── BaseTools.java
│   │   │   │   ├── moisturizersData.txt
│   │   │   │   ├── sunscreensData.txt
│   ├── resources
│   │   ├── features
│   │   │   ├── buy_moisturizers.feature
│   │   │   ├── buy_sunscreens.feature
│   │   │   ├── invalid_payment_details.feature
│   │   │   ├── shopping_based_on_temperature.feature
│   │   ├── configuration.properties
│   │   ├── junit-platform.properties
│   │   ├── simplelogger.properties
├── pom.xml
├── testng.xml
├── .gitignore
└── README.md
```

---

## Contribution Guidelines

1. Fork the repository.
2. Create a new feature branch.
3. Commit your changes with proper messages.
4. Push the changes and create a pull request.

---

## Contact
For questions or suggestions, feel free to contact me via my email **benayad.chaymae@gmail.com** or open an issue in this repository.

