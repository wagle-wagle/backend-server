package com.project.waglewagle.broad;

import com.project.waglewagle.broad.dto.*;
import com.project.waglewagle.entity.Users;
import org.springframework.stereotype.Component;

@Component
public interface BroadService {
    BroadResponse getBroad(Long broadId);

    BroadResponse getBroadByUrl(String broadUrl);

    BroadPostResponse postBroad(BroadPostRequest request, Users users);

    BroadResponse changeTitle(Long broadId, BroadUpdateRequest request);

    BroadStyleDTO changeStyle(Long broadStyleId, BroadStyleDTO request);





}
