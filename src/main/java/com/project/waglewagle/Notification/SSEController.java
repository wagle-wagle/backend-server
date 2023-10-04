package com.project.waglewagle.Notification;

import com.project.waglewagle.global.config.security.PrincipalDetail;
import com.project.waglewagle.global.util.ApiResponse;
import com.project.waglewagle.global.util.CommonResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/notification")
public class SSEController {
    private final SSEService sseService;
    @GetMapping(value = "/connect", produces = "text/event-stream")
    public ResponseEntity<SseEmitter> sseConnection(@AuthenticationPrincipal PrincipalDetail principalDetail, HttpServletResponse response) {

        return new ResponseEntity<>(sseService.connection(principalDetail.getUsername()), HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public void test(){
        sseService.sendNoti("test","test");
    }

    @DeleteMapping(value = "/")
    public CommonResponse deleteNoti(@RequestParam("id") String id){
        sseService.delete(id);
        return ApiResponse.createSuccess("알림 삭제 완료", HttpStatus.OK,null);
    }

}
