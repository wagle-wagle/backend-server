package com.project.waglewagle.Notification;

import com.project.waglewagle.global.config.security.PrincipalDetail;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class SSEController {
    private final SSEService sseService;
    @GetMapping(value = "/connect", produces = "text/event-stream")
    public ResponseEntity<SseEmitter> sseConnection(@AuthenticationPrincipal PrincipalDetail principalDetail, HttpServletResponse response) {

        return new ResponseEntity<>(sseService.connection(response, principalDetail.getUsername()), HttpStatus.OK);
    }

    @PostMapping(value = "/testNo")
    public void test(){
        sseService.send("qnrms2898@naver.com","케인인","new");
    }
}
