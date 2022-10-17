package com.example.KafkaProducer.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("invoice")
public class InvoiceController {
    @Value("${kafka.topic.name}")
    private String topicName;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping
    public String create(@RequestBody Map<String, Object> invoice) {
        kafkaTemplate.send(topicName, invoice);
        return "created";
    }
}
