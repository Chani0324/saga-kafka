package com.sparta.saga.kafka.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private static final String toProducerTopic1 = "toProducerTopic1";

    @KafkaListener(groupId = "product_A", topics = toProducerTopic1)
    public void receiveMessage(String serializedDeliveryMessage) {
        log.info("product receive : {}", serializedDeliveryMessage);
        productService.reduceProductAmount(serializedDeliveryMessage);
    }
}
