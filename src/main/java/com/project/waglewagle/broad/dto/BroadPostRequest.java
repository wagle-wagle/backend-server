package com.project.waglewagle.broad.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.waglewagle.broad.Broad;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class BroadPostRequest {

    @NotNull
    private String version;

    @NotNull
    private String title;

    @NotNull
    @JsonProperty("broadStyle")
    private BroadStyleDTO broadStyle;

    @NotNull
    private String url;


}
