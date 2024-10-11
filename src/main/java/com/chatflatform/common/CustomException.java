package com.chatflatform.common;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private final CodeInterface codeInterface;

    public CustomException(CodeInterface v){
        super(v.getMessage());
        this.codeInterface = v;
    }

    public CustomException(CodeInterface v, String messgage){
        super(v.getMessage() + messgage);
        this.codeInterface = v;
    }
}
