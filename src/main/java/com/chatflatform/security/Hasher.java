package com.chatflatform.security;

import io.netty.handler.codec.base64.Base64Encoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class Hasher  {
    public String getHashingValue(String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);

        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            throw new RuntimeException("HASH failed" , e);
        }
    }
}
