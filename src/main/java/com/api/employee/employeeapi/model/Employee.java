package com.api.employee.employeeapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Column(unique = true)
    private String email;

    private String fullName;
    private LocalDate birthday;

    @ElementCollection
    private List<String> hobbies;


}
