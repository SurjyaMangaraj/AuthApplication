package com.Thrilfix.AuthApplication.security;

import com.Thrilfix.AuthApplication.exceptions.ResourceNotFoundException;
import com.Thrilfix.AuthApplication.model.User;
import com.Thrilfix.AuthApplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

     private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("Invalid Email or password"));
        return user;
    }
}
