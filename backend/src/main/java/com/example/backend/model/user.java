package com.example.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
//@Table(name = "users")
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(length = 128, nullable = false, unique = true)
    @NotEmpty(message = "Email is required")
    @NotNull(message = "Email should not be null")
    @Email(message = "Invalid email format")
    private String email;

    @Column(length = 64, nullable = false)
    @NotBlank(message = "Password is required")
    private String password;

    @Column(name = "first_name", length = 45, nullable = false)
    @Size(min = 2, message = "First Name must be at least 2 characters long")
    @NotBlank(message = "First Name is required")
    private String firstName;

    @Column(name = "last_name", length = 45, nullable = false)
    @Size(min = 2, message = "Length must be greater than 1")
    @NotBlank(message = "Last Name is required")
    private String lastName;


    protected user(){};

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
