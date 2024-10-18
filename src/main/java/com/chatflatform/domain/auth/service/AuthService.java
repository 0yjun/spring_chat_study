package com.chatflatform.domain.auth.service;

import com.chatflatform.domain.auth.model.CustomUserDetails;
import com.chatflatform.domain.user.entity.User;
import com.chatflatform.domain.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    UserRepository userRepository;

    public AuthService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username)
                .orElseThrow(
                        ()->new IllegalArgumentException("없는유저 유저")
                );

        return new CustomUserDetails(user);
    }
}
