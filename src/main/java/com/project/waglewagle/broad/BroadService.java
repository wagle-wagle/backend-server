package com.project.waglewagle.broad;

import com.project.waglewagle.broad.dto.BroadPostRequest;
import com.project.waglewagle.broad.dto.BroadResponse;
import org.springframework.stereotype.Component;

@Component
public interface BroadService {
    BroadResponse getBroad(Long broadId);

    void postBroad(BroadPostRequest request);




}
