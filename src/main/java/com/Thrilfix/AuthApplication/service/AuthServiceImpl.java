package com.Thrilfix.AuthApplication.service;

import com.Thrilfix.AuthApplication.dtos.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserService userService;

    @Override
    public UserDto registerUser(UserDto userDto) {
        UserDto userDto1=userService.createUser(userDto);
        return userDto1;
    }
}
