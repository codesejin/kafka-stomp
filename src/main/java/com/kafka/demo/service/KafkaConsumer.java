package com.kafka.demo.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final AlarmService alarmService;

    @KafkaListener(topics = "kafka-demo", groupId = "kafka-demo")
    public void consume(ConsumerRecord<String, String> record) throws IOException {

        System.out.println(record.headers());
        System.out.println(String.format("Consumed message : %s", record.value()));

        // Authorization 헤더에서 토큰을 추출해서 byte 배열을 String으로 변경.
        String token = extractTokenFromHeaders(record);
        System.out.println("Consumed token = " + token);

        alarmService.orderAlarm(record.value());
    }

    private String extractTokenFromHeaders(ConsumerRecord<String, String> record) {
        String token = null;
        Iterable<Header> headers = record.headers().headers("authorization");
        if (headers.iterator().hasNext()) {
            Header authorizationHeader = headers.iterator().next();
            token = new String(authorizationHeader.value());
        }
        return token;
    }
}
