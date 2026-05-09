package org.ifglife.config;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import org.ifglife.dto.request.OrderRequest;

public class OrderDeserializer extends ObjectMapperDeserializer<OrderRequest> {
    public OrderDeserializer() {
        super(OrderRequest.class);
    }
}
