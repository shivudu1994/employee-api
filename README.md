# Emmployee API Backend Service
This is a simple Spring Boot REST API for managing employee data. The application provides endpoints for creating, retrieving, updating, and deleting employee records. The data is stored in a H2 database using Spring Data JPA.


## **Assumptions**

The service assumes that:

1.  The due date-time is in the future when adding them into database.
2.  The due date-time and description of the task is mandatory when adding them into database.
3.  The description should not be empty while updating the database.
4.  You can't update the Status to done  when the todo item status is already a past due or done status.
5.  You can't update the Status to not done  when the todo item status is already a past due or done status. (Because , Here i have assumed that user may mistakenly update the status not done  to done. Here user have a possibility to revert the changes)



## **Tech Stack**

- Java 17
- Spring Boot 3.0.3
- H2 in-memory database
- JPA
- Maven


## **How to Build the Service**

- Clone the repository

- Open a terminal in the root directory of the project

- Run the following command:
  ```
  mvn clean package
  ```




## **How to Run the Service Locally**

1. Open a terminal in the root directory of the project
2. Run the following command:
   ```
   mvn spring-boot:run
   ```
## **How to Run Automatic Tests**

1. Open a terminal in the root directory of the project
2. Run the following command:
   ```
   mvn test
   ```

## **RESTful API Endpoints**

Endpoints:

1. POST /employees - creates a new employee record
2. GET /employees - retrieves a list of all employee records
3. GET /employees/{uuid} - retrieves a specific employee record by UUID
4. PUT /employees/{uuid} - updates a specific employee record by UUID
5. DELETE /employees/{uuid} - deletes a specific employee record by UUID


## **The employee data is represented as a JSON object with the following fields:**

1. uuid: a unique identifier for the employee (generated automatically)
2. fullName: the full name of the employee
3. email: the email address of the employee
4. birthday: the date of birth of the employee (in YYYY-MM-DD format)
5. hobbies: a comma-separated list of the employee's hobbies

Note: This is a basic implementation of a REST API and is not intended for production use. It is recommended to add authentication, validation, error handling, and other features to make the application more robust and secure.
