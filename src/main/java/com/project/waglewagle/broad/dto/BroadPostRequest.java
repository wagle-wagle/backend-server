package com.project.waglewagle.broad.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.waglewagle.broad.Broad;
import lombok.Getter;

@Getter
public class BroadPostRequest {
    private String version;

    private String title;

    @JsonProperty("broadStyle")
    private BroadStyleDTO broadSytle;

    private String url;


}
