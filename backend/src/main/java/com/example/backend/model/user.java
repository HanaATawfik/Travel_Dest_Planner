package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
//@Table(name = "users")
public class User {
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
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
        message = "Password must contain at least one alphabetical character, one digit, one special character, and be at least 8 characters long.")
    private String password;

    @Column(name = "first_name", length = 45, nullable = false)
    @Size(min = 2, message = "First Name must be at least 2 characters long")
    @NotBlank(message = "First Name is required")
    @Pattern(regexp = "^[A-Z][a-zA-Z]*$", message = "First Name should start with a capital letter and contain only alphabets.")
    private String firstName;

    @Column(name = "last_name", length = 45, nullable = false)
    @Size(min = 2, message = "Length must be greater than 1")
    @NotBlank(message = "Last Name is required")
    @Pattern(regexp = "^[A-Z][a-zA-Z]*$", message = "Last Name should start with a capital letter and contain only alphabets.")
    private String lastName;


    protected User(){};

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
