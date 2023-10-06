package com.project.waglewagle.Notification;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.waglewagle.Notification.DTO.NotificationResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Repository
public class SSERepositoryImpl implements SSERepository{

    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final RedisTemplate<String, Object> redisTemplate;
    private final ValueOperations<String, Object> valueOperations;

    public SSERepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
    }
    @Override
    public SseEmitter save(String id, SseEmitter sseEmitter) {
        emitters.put(id, sseEmitter);
        return sseEmitter;
    }

    @SneakyThrows
    @Override
    public void saveEventCache(String key, Notification event) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        valueOperations.set(key,mapper.writeValueAsString(event));
        if(event.getType() == "new"){
            redisTemplate.expire(key,1,TimeUnit.DAYS);
        }
    }

    @SneakyThrows
    @Override
    public Map<String, NotificationResponse> findAllEventCacheStartWithId(String id, Map<String, NotificationResponse> map) {
        Set<String> list = redisTemplate.keys(id+"*");
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        for (String element : list) {
            NotificationResponse notificationResponse = mapper.readValue(valueOperations.get(element).toString(),NotificationResponse.class);
            notificationResponse.setId(element);
            map.put(element.substring(element.lastIndexOf("_")+1),notificationResponse);
        }
        return map;
    }

    @Override
    public Map<String, SseEmitter> findAllStartById(String id) {
        return emitters.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(id))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }

    @Override
    public Map<String, SseEmitter> findAll() {
        return emitters.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }

    @Override
    public void deleteAllStartByWithId(String id) {
        emitters.forEach((key, emitter) -> {
            if (key.startsWith(id)) emitters.remove(key);
        });
    }

    @Override
    public void deleteCacheById(String id) {
        redisTemplate.delete(id);
    }
}
