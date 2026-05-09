package org.ifglife.consumer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.ifglife.constant.ChannelConstant;
import org.ifglife.constant.MessageConstant;
import org.ifglife.constant.OrderConstant;
import org.ifglife.dto.request.OrderProcessedRequest;
import org.ifglife.dto.request.OrderRequest;

@ApplicationScoped
public class OrderConsumer {
    @Inject
    @Channel(ChannelConstant.PROCESSED)
    Emitter<OrderProcessedRequest> processedEmitter;

    @Inject
    @Channel(ChannelConstant.DLQ)
    Emitter<OrderRequest> dlqEmitter;

    @Incoming(ChannelConstant.ORDER)
    @Retry(maxRetries = 3, delay = 1000)
    @Fallback(fallbackMethod = "sendToDlq")
    public void consume(OrderRequest order) {
        if (order.amount < 0)
            throw new RuntimeException(MessageConstant.INVALID_AMOUNT);

        OrderProcessedRequest processed = new OrderProcessedRequest();
        processed.orderId = order.orderId;
        processed.customerName = order.customerName;
        processed.amount = order.amount;
        processed.priority = order.amount > 1000 ? OrderConstant.ORDER_HIGH : OrderConstant.ORDER_NORMAL;

        processedEmitter.send(processed);
    }

    public void sendToDlq(OrderRequest order) {
        dlqEmitter.send(order);
    }
}
