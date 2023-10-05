package com.project.waglewagle.Notification;

import jakarta.persistence.SecondaryTable;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
public class SSEServiceImpl implements SSEService{
    private static final Long DEFAULT_TIMEOUT = 120L * 1000 * 60;
    private final SSERepository sseRepository;
    public SseEmitter connection(String email) {
        // 1
        String id = email;

        // 2
        SseEmitter emitter = sseRepository.save(id, new SseEmitter(DEFAULT_TIMEOUT));

        emitter.onCompletion(() -> sseRepository.deleteAllStartByWithId(id));
        emitter.onTimeout(() -> sseRepository.deleteAllStartByWithId(id));

        // 3
        // 503 에러를 방지하기 위한 더미 이벤트 전송
        sendToClient(emitter, id, "EventStream Created. [userId=" + email + "]");

        // 4
        // 클라이언트가 미수신한 Event 목록이 존재할 경우 전송하여 Event 유실을 예방
        Map<String, Notification> Notievents = new HashMap<>();
        Notievents = sseRepository.findAllEventCacheStartWithId("ALL", Notievents);
        Notievents = sseRepository.findAllEventCacheStartWithId(String.valueOf(email),Notievents);
        Map<String, Notification> events = new TreeMap<>(Notievents);
        events.entrySet().stream()
                .forEach(entry -> sendToClient(emitter, entry.getKey(), entry.getValue()));


        return emitter;
    }

    // 3
    private void sendToClient(SseEmitter emitter, String id, Object data) {
        try {
            emitter.send(SseEmitter.event()
                    .id(id)
                    .name("sse")
                    .data(data));
        } catch (IOException exception) {
            System.out.println("에러");
            sseRepository.deleteAllStartByWithId(id);
            connection(id);
        }
    }
    public void send(String targetEmail, String userName,Integer styleType, String Type) {
        Notification notification = new Notification("기와가 도착했오",userName,null,"new");
        String id = String.valueOf(targetEmail);

        // 로그인 한 유저의 SseEmitter 모두 가져오기
        Map<String, SseEmitter> sseEmitters = sseRepository.findAllStartById(id);
        sseEmitters.forEach(
                (key, emitter) -> {
                    // 데이터 캐시 저장(유실된 데이터 처리하기 위함)
                    sseRepository.saveEventCache(key, notification);
                    // 데이터 전송
                    sendToClient(emitter, key, notification);
                }
        );
    }

    @Override
    public void sendNoti(String title, String message) {
        Notification notification = new Notification(title,null, message, "Notification");
        sseRepository.saveEventCache("ALL", notification);
    }

    @Override
    public void delete(String id) {
        sseRepository.deleteCacheById(id);
    }
}
