package com.chatflatform.domain.auth.service;


import com.chatflatform.common.CustomException;
import com.chatflatform.common.exception.ErrorCode;
import com.chatflatform.domain.auth.model.response.UserSearchResponse;
import com.chatflatform.domain.auth.repository.UserRepository;
import com.chatflatform.domain.auth.repository.entity.User;
import com.chatflatform.security.Hasher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceV1 {

    private final UserRepository userRepository;
    private final Hasher hasher;

    public final UserSearchResponse searchUser(String name, String user) {
        List<String> names = userRepository.findNameByNameMatch(name,user);
        return new UserSearchResponse(ErrorCode.SUCCESS, names);
    }
}
