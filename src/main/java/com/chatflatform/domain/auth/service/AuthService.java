package com.chatflatform.domain.auth.service;


import com.chatflatform.common.CustomException;
import com.chatflatform.common.exception.ErrorCode;
import com.chatflatform.domain.auth.model.request.CreateUserRequest;
import com.chatflatform.domain.auth.model.request.LoginRequest;
import com.chatflatform.domain.auth.model.response.CreateUserResponse;
import com.chatflatform.domain.auth.model.response.LoginResponse;
import com.chatflatform.domain.auth.repository.UserRepository;
import com.chatflatform.domain.auth.repository.entity.User;
import com.chatflatform.domain.auth.repository.entity.UserCredentials;
import com.chatflatform.security.Hasher;
import com.chatflatform.security.JWTProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final Hasher hasher;

    public String getUserFromToken(String token){
        return JWTProvider.getUserFromToken(token);
    }

    public LoginResponse login(LoginRequest request){
        Optional<User> user = userRepository.findByName(request.name());

        if(user.isEmpty()){
            log.error("NOT_EXIST_USER : {}",request.name());
            throw new CustomException(ErrorCode.NOT_EXIST_USER);

        }

        user.map(u->{
            String hashedValue = hasher.getHashingValue(request.password());
            if(!u.getUserCredentials().getHashed_password().equals(hashedValue)){
                log.error("MIS_MATCH_PASSWORD : {}",request.name());
                throw new CustomException(ErrorCode.MIS_MATCH_PASSWORD);
            }
            return hashedValue;
        }).orElseThrow(()->{
            throw new CustomException(ErrorCode.MIS_MATCH_PASSWORD);
        });

        //return JWT
        String token = JWTProvider.createRefreshToken(request.name());
        return new LoginResponse(ErrorCode.SUCCESS,token);

    }


    @Transactional(transactionManager="createUserTransactionManager")
    public CreateUserResponse createUser(CreateUserRequest request){
        Optional<User> user = userRepository.findByName(request.name());

        if(user.isPresent()){
            throw new CustomException(ErrorCode.USER_ALREADY_EXIST);
        }

        try {
            User newUser = this.newUser(request.name());
            UserCredentials newCredential = this.newUserCredentials(request.password(),newUser);
            newUser.setCredentials(newCredential);

            User saveUser = userRepository.save(newUser);
            if(saveUser == null){
                log.error("USER_SAVED_FAIL: {}", request.name());
                throw new CustomException(ErrorCode.USER_SAVED_FAIL);
            }

        }catch (Exception e){
            log.error("USER_SAVED_FAIL: {}", request.name());
            throw new CustomException(ErrorCode.USER_SAVED_FAIL);
        }

        return new CreateUserResponse(request.name());
    }

    private User newUser(String name){
        User newUser = User.builder()
                .name(name)
                .created_at(new Timestamp(System.currentTimeMillis()))
                .build();

        return newUser;
    }

    private UserCredentials newUserCredentials(String password, User user) throws Exception {
        String hashValue = hasher.getHashingValue(password);
        UserCredentials cre = UserCredentials.builder()
                .user(user)
                .hashed_password(hashValue)
                .build();
        return cre;
    }
}
