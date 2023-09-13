package com.project.waglewagle.broad.dto;

import com.project.waglewagle.broad.Broad;
import lombok.Getter;

@Getter
public class BroadPostRequest {
    private String version;

    private String title;

    private BroadStyleDTO broadSytle;

    private String url;


}
