package com.project.waglewagle.Notification;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
public interface SSEService {
    SseEmitter connection(HttpServletResponse response, String email);
    void send(String targetEmail, String userName, String type);

}
