package com.example.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity //JPA persistent table in db
public class user { //table name = user
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto generation
    private Long id;

    @Column(length = 128, nullable = false, unique = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Column(name = "password_hash", length = 255, nullable = false)
    private String passwordHash;

    @Column(length = 45, nullable = false)
    @Size(min = 2, max = 45, message = "First Name must be at least 2 characters long")
    @NotBlank(message = "First Name is required")
    private String firstName;

    @Column(length = 45, nullable = false)
    @Size(min = 2, max = 45, message = "Length must be greater than 1")
    @NotBlank(message = "Last Name is required")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role = Role.USER;

    protected user() {} //no args constructor for JPA

    public user(String email, String passwordHash, String firstName, String lastName, Role role) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role == null ? Role.USER : role;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getHashedPassword() {
        return passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Role getRole() {
        return role;
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

    public void setRole(Role role) {
        this.role = role == null ? Role.USER : role;
    }

    public boolean isAdminFlag() {
        return this.role == Role.ADMIN;
    }

    public void setAdminFlag(boolean adminFlag) {
        this.role = adminFlag ? Role.ADMIN : Role.USER;
    }
}
