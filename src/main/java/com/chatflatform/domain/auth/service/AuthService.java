package com.chatflatform.domain.auth.service;


import com.chatflatform.domain.auth.model.request.CreateUserRequest;
import com.chatflatform.domain.auth.model.response.CreateUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    public CreateUserResponse createUser(CreateUserRequest request){
        return new CreateUserResponse(request.name());
    }
}
