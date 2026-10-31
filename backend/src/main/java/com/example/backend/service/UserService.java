package com.example.backend.service;

import com.example.backend.model.User;

public interface UserService {
    User SignUp (User User);
    User LogIn (User User);
}
