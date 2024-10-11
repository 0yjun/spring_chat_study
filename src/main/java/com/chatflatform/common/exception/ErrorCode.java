package com.chatflatform.common.exception;

import com.chatflatform.common.CodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode implements CodeInterface {
    SUCCESS(0,"SUCESS"),
    USER_ALREADY_EXIST(-1,"USER_ALREADY_EXIST"),
    USER_SAVED_FAIL(-2,"USER_SAVED_FAIL"),
    NOT_EXIST_USER(-3,"NOT_EXIST_USER"),
    MIS_MATCH_PASSWORD(-3,"MIS_MATCH_PASSWORD"),

    TOKEN_IS_INVALID(-200, "token is invalid"),
    ACCESS_TOKEN_IS_NOT_EXPIRED(-201, "token is not expired"),
    ACCESS_TOKEN_IS_EXPIRED(-201, "token is expired");

    private final Integer code;
    private final String message;

}
