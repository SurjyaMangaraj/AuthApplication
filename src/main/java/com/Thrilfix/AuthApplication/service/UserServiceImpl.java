package com.Thrilfix.AuthApplication.service;

import com.Thrilfix.AuthApplication.dtos.UserDto;
import com.Thrilfix.AuthApplication.model.Provider;
import com.Thrilfix.AuthApplication.model.User;
import com.Thrilfix.AuthApplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {
        if(userDto.getEmail()==null || userDto.getEmail().isBlank()){
            throw new IllegalArgumentException("Email is required");
        }
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new IllegalArgumentException("email already exists");
        }
        User user = modelMapper.map(userDto, User.class);
        user.setProvider(userDto.getProvider()!=null ? userDto.getProvider() : Provider.LOCAL);
//        role assign here to user for authorization
        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return null;
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        return null;
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public UserDto getUserById(String userId) {
        return null;
    }

    @Override
    public Iterable<UserDto> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }
}
