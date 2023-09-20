package com.project.waglewagle.dto.common;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@RequiredArgsConstructor(staticName = "in")
public class ResultDto<Data> {
    private final String status;  // 응답 성공여부
    private final String messege; // 응답 메세지
    private Data data; //  응답 데이터

}
