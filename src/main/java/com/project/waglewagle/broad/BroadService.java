package com.project.waglewagle.broad;

import com.project.waglewagle.broad.dto.BroadPostRequest;
import com.project.waglewagle.broad.dto.BroadResponse;
import com.project.waglewagle.broad.dto.BroadStyleDTO;
import com.project.waglewagle.broad.dto.BroadUpdateRequest;
import com.project.waglewagle.entity.Users;
import org.springframework.stereotype.Component;

@Component
public interface BroadService {
    BroadResponse getBroad(Long broadId);

    void postBroad(BroadPostRequest request, Users users);

    BroadResponse changeTitle(Long broadId, BroadUpdateRequest request);

    BroadStyleDTO changeStyle(Long broadStyleId, BroadStyleDTO request);




}
