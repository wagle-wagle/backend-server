package com.project.waglewagle.Notification;

import com.project.waglewagle.Notification.DTO.NotificationResponse;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

public interface SSERepository {


    SseEmitter save(String id, SseEmitter sseEmitter);

    void saveEventCache(String key, Notification event);

    Map<String, SseEmitter> findAllStartById(String id);
    Map<String, SseEmitter> findAll();

    Map<String, NotificationResponse> findAllEventCacheStartWithId(String id, Map<String, NotificationResponse> map);
    void deleteAllStartByWithId(String id);

    void deleteCacheById(String id);



}
