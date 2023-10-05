package com.project.waglewagle.broad.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class BroadUpdateRequest {
    @NotNull
    private String title;
}
