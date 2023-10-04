package com.project.waglewagle.Notification;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public void saveEventCache(String id, Notification event) {
        String key = id+"_"+System.currentTimeMillis();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        valueOperations.set(key,mapper.writeValueAsString(event));
        if(event.getType() == "new"){
            redisTemplate.expire(key,1,TimeUnit.DAYS);
        }
    }

    @SneakyThrows
    @Override
    public Map<String, Notification> findAllEventCacheStartWithId(String id) {
        Set<String> list = redisTemplate.keys(id+"*");
        Map<String, Notification> result = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        for (String element : list) {

            result.put(element,mapper.readValue(element,Notification.class));
        }
        return result;
    }

    @Override
    public Map<String, SseEmitter> findAllStartById(String id) {
        return emitters.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(id))
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
