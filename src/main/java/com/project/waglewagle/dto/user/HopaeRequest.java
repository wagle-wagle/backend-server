package com.project.waglewagle.dto.user;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HopaeRequest {
    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("userName")
    private String userName;

}
