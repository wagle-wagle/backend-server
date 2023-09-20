package com.project.waglewagle.global.error;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor(staticName = "of")
public class ErrorResponse {
    private final ErrorCode errorCode;
    private final String errorMessage;
}
