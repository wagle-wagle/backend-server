package com.project.waglewagle.broad;

import com.project.waglewagle.broad.dto.*;
import com.project.waglewagle.global.config.security.PrincipalDetail;
import com.project.waglewagle.global.util.ApiResponse;
import com.project.waglewagle.global.util.CommonResponse;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class BroadController {
    private final BroadService broadService;

    @GetMapping("/broads/{Broad}")
    public CommonResponse<BroadResponse> getBroad(@PathVariable("Broad") String broad){
        try{
            BroadResponse response = broadService.getBroad(Long.parseLong(broad));
            return ApiResponse.createSuccess("기와집을 성공적으로 불러왔습니다.",HttpStatus.OK,response);
        }
        catch (NumberFormatException e)
        {
            BroadResponse response = broadService.getBroadByUrl(broad);
            return ApiResponse.createSuccess("기와집을 성공적으로 불러왔습니다.",HttpStatus.OK,response);
        }

    }

    @PostMapping("/broads")
    @PreAuthorize("isAuthenticated()")
    public CommonResponse<BroadPostResponse> postBroad(@Valid @RequestBody BroadPostRequest request, @AuthenticationPrincipal PrincipalDetail principalDetail){
        BroadPostResponse broadPostResponse = broadService.postBroad(request,principalDetail.getUser());
        return ApiResponse.createSuccess("기와집을 성공적으로 생성했습니다.", HttpStatus.CREATED, broadPostResponse);
    }

    @PatchMapping("/broads/{BroadId}")
    @PreAuthorize("isAuthenticated()")
    public CommonResponse<BroadResponse> changeTitle(@PathVariable("BroadId") Long broadId,@Valid @RequestBody BroadUpdateRequest request){
        BroadResponse response = broadService.changeTitle(broadId,request);
        return ApiResponse.createSuccess("기와집을 이름을 성공적으로 수정했습니다.",HttpStatus.CREATED,response);
    }

    @PatchMapping("/broads/style/{BroadStyleId}")
    @PreAuthorize("isAuthenticated()")
    public CommonResponse<BroadStyleDTO> changeStyle(@PathVariable("BroadStyleId") Long broadStyleId,@Valid @RequestBody BroadStyleDTO request){
        BroadStyleDTO broadStyle = broadService.changeStyle(broadStyleId,request);
        return ApiResponse.createSuccess("기와집 차림새를 성공적으로 수정했습니다.", HttpStatus.CREATED, broadStyle);
    }

}
