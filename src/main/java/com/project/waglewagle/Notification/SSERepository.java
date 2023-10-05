package com.project.waglewagle.Notification;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

public interface SSERepository {


    SseEmitter save(String id, SseEmitter sseEmitter);

    void saveEventCache(String id, Notification event);

    Map<String, SseEmitter> findAllStartById(String id);

    Map<String, Notification> findAllEventCacheStartWithId(String id, Map<String, Notification> map);
    void deleteAllStartByWithId(String id);

    void deleteCacheById(String id);



}
