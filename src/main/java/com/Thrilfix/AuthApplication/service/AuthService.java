package com.Thrilfix.AuthApplication.service;

import com.Thrilfix.AuthApplication.dtos.UserDto;

public interface AuthService {

    UserDto registerUser(UserDto userDto);
}
