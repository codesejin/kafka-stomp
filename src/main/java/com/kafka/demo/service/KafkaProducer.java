package com.kafka.demo.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private static final String TOPIC = "kafka-demo";
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message, String token) {

        System.out.println("Produce token = " + token);
        System.out.println(String.format("Produce message : %s", message));
        // 핵심은 producer는 this.kafkaTemplate.send(TOPIC, message); 를 통해서 TOPIC에 해당하는 message를 전달할 것이다.
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, message);
        record.headers().add(new RecordHeader("authorization", token.getBytes()));

        this.kafkaTemplate.send(record);
    }
}