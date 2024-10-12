package com.chatflatform.domain.auth.model.response;


import com.chatflatform.common.exception.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "유저 검색 response")
public record UserSearchResponse(
        @Schema(description = "code")
        ErrorCode code,

        @Schema(description = "name")
        List<String> name
){}
