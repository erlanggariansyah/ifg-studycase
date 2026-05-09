package org.ifglife.resource;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ifglife.constant.ResourceConstant;
import org.ifglife.dto.request.OrderRequest;
import org.ifglife.producer.OrderProducer;

@Path(ResourceConstant.ORDER_V1)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {
    @Inject
    OrderProducer producer;

    @POST
    public Response create(@Valid OrderRequest request) {
        producer.send(request);
        return Response.ok(request).build();
    }
}
