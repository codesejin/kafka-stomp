package com.kafka.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AlarmService {

    private final SimpMessagingTemplate messagingTemplate;

    public String orderAlarm(String message) {

        //User oUser = userRepository.getByEmail(userEmail);
        // message와 토큰을 매개변수로 받아서 user의 pk를 토픽으로 websocket 연결
        messagingTemplate.convertAndSend("/sub/" + 100001, message);
        return null;
    }
}
