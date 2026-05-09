package org.ifglife.producer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.ifglife.constant.ChannelConstant;
import org.ifglife.dto.request.OrderRequest;

@ApplicationScoped
public class OrderProducer {
    @Inject
    @Channel(ChannelConstant.ORDERS)
    Emitter<OrderRequest> emitter;

    public void send(OrderRequest request) {
        emitter.send(request);
    }
}
