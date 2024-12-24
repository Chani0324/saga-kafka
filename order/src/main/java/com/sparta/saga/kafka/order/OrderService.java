package com.sparta.saga.kafka.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private static final String topic1 = "TOPIC1";

    private final Map<UUID, Order> orderStore = new HashMap<>();

    public void sendMessage(String topic, String key, OrderRequestDto OrderRequestDto) {

    }
}
