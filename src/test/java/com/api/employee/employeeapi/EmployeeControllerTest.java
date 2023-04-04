package com.api.employee.employeeapi;

import com.api.employee.employeeapi.controller.EmployeeController;
import com.api.employee.employeeapi.model.Employee;
import com.api.employee.employeeapi.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeController employeeController;

    private Employee testEmployee;

    @Before
    public void setUp() {
        testEmployee = new Employee();
        testEmployee.setUuid(UUID.randomUUID());
        testEmployee.setEmail("test@test.com");
        testEmployee.setFullName("Test Employee");
        testEmployee.setBirthday(LocalDate.of(2000, 1, 1));
        testEmployee.setHobbies(Arrays.asList("Reading", "Cooking"));
    }

    @Test
    public void createEmployee_success() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(testEmployee);

        ResponseEntity<Employee> response = employeeController.createEmployee(testEmployee);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testEmployee, response.getBody());
    }

    @Test
    public void createEmployee_failure() {
        when(employeeRepository.save(any(Employee.class))).thenThrow(new RuntimeException());

        ResponseEntity<Employee> response = employeeController.createEmployee(testEmployee);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void getAllEmployees_success() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(testEmployee));

        ResponseEntity<List<Employee>> response = employeeController.getAllEmployees();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(testEmployee, response.getBody().get(0));
    }

    @Test
    public void getEmployeeById_success() {
        when(employeeRepository.findById(any(UUID.class))).thenReturn(Optional.of(testEmployee));

        ResponseEntity<Employee> response = employeeController.getEmployeeById(testEmployee.getUuid());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testEmployee, response.getBody());
    }

    @Test
    public void getEmployeeById_notFound() {
        when(employeeRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        ResponseEntity<Employee> response = employeeController.getEmployeeById(UUID.randomUUID());

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void updateEmployee_success() {
        Employee updatedEmployee = new Employee();
        updatedEmployee.setEmail("updated@test.com");
        updatedEmployee.setFullName("Updated Employee");
        updatedEmployee.setBirthday(LocalDate.of(1990, 1, 1));
        updatedEmployee.setHobbies(Arrays.asList("Traveling", "Hiking"));

        when(employeeRepository.findById(any(UUID.class))).thenReturn(Optional.of(testEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

        ResponseEntity<Employee> response = employeeController.updateEmployee(testEmployee.getUuid(), updatedEmployee);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedEmployee, response.getBody());
    }

    @Test
    public void updateEmployee_notFound() {
        when(employeeRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        ResponseEntity<Employee> response = employeeController.updateEmployee(UUID.randomUUID(), testEmployee);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void deleteEmployee_success() {
        when(employeeRepository.findById(any(UUID.class))).thenReturn(Optional.of(testEmployee));

        ResponseEntity<Void> response = employeeController.deleteEmployee(testEmployee.getUuid());

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(employeeRepository, times(1)).delete(testEmployee);
    }

}