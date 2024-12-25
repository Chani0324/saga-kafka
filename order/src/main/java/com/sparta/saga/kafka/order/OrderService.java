package com.sparta.saga.kafka.order;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    private static final String toProducerTopic1 = "toProducerTopic1";

    private final Map<UUID, Order> orderStore = new HashMap<>();

    public Order createOrder(OrderRequestDto orderRequestDto){
        Order order = orderRequestDto.toOrder();
        orderStore.put(order.getOrderId(), order);

        DeliveryMessage deliveryMessage = orderRequestDto.toDeliveryMessage(order.getOrderId());

        try {
            String jsonOrder = objectMapper.writeValueAsString(deliveryMessage);
            // topic, key, message
            kafkaTemplate.send(toProducerTopic1, orderRequestDto.getUserId(), jsonOrder);

            return order;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
