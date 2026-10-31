package com.example.backend.service;

import com.example.backend.model.user;
public interface UserService {
    user SignUp (user User);
    user LogIn (user User);
}
