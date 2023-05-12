package com.kafka.demo.controller;

import com.kafka.demo.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
    private final KafkaProducer producer;

    @Autowired
    KafkaController(KafkaProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public String sendMessage(@RequestParam("message") String message,
                              @RequestHeader("authorization") String token) {

        System.out.println("RequestHeader token = " + token);
        this.producer.sendMessage(message, token);
        return "success";
    }
}
