package com.api.employee.employeeapi.controller;

import com.api.employee.employeeapi.model.Employee;
import com.api.employee.employeeapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        try {
            Employee savedEmployee = employeeRepository.save(employee);
            return ResponseEntity.ok(savedEmployee);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable UUID uuid) {
        try {
            Optional<Employee> optionalEmployee = employeeRepository.findById(uuid);
            if (optionalEmployee.isPresent()) {
                return ResponseEntity.ok(optionalEmployee.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable UUID uuid, @RequestBody Employee employee) {
        try {
            Optional<Employee> optionalEmployee = employeeRepository.findById(uuid);
            if (optionalEmployee.isPresent()) {
                Employee existingEmployee = optionalEmployee.get();
                existingEmployee.setEmail(employee.getEmail());
                existingEmployee.setFullName(employee.getFullName());
                existingEmployee.setBirthday(employee.getBirthday());
                existingEmployee.setHobbies(employee.getHobbies());
                Employee savedEmployee = employeeRepository.save(existingEmployee);
                return ResponseEntity.ok(savedEmployee);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable UUID uuid) {
        try {
            Optional<Employee> optionalEmployee = employeeRepository.findById(uuid);
            if (optionalEmployee.isPresent()) {
                employeeRepository.delete(optionalEmployee.get());
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
