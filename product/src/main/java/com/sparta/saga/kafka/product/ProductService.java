package com.sparta.saga.kafka.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ObjectMapper objectMapper;

    public void reduceProductAmount(String serializedDeliveryMessage) {
        try {
            DeliveryMessage deliveryMessage = objectMapper.readValue(serializedDeliveryMessage, DeliveryMessage.class);

            Integer productId = deliveryMessage.getProductId();
            Integer productQuantity = deliveryMessage.getProductQuantity();

            log.info("deliveryMessage.getProductId() : {}", deliveryMessage.getProductId());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
