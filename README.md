# REST Assured API Testing

A Java-based API testing project demonstrating REST API automation using REST Assured, TestNG, Maven, Cucumber, and data-driven testing.

## Technologies

- Java
- REST Assured
- TestNG
- Cucumber
- Maven
- Jenkins
- Git

## Project Structure

### API_framework

REST API automation using REST Assured and TestNG.

Includes API request execution, response validation, JSON validation, and reusable test components.

### ExcelDriven

Data-driven API testing using test data stored in Excel.

### api_demo_with_cucumber_and_html_report

BDD-style API testing using Cucumber with HTML test reporting.

## Testing

The project demonstrates testing of REST APIs using different approaches, including:

- GET requests
- POST requests
- PUT requests
- DELETE requests
- HTTP status code validation
- Response validation
- JSON response validation
- Request and response logging
- Data-driven testing
- Cucumber BDD testing
- HTML test reports

## Build Tool

The project uses Maven for dependency management and test execution.

Run the tests with:

```bash
mvn clean test
```

## Jenkins

The tests were also executed using a local Jenkins installation.

Jenkins was used to run the Maven test suite and verify the automated API tests in a CI environment.

## Purpose

This project is a practical demonstration of API testing and test automation using Java and REST Assured, including different approaches to organizing and executing API tests.
