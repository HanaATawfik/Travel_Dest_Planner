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
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Email(message = "Invalid email format")
    private String email;


    @NotBlank(message = "Password is required")
    @Column(name = "password_hash", length = 255, nullable = false)
    private String passwordHash;
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
        return passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
