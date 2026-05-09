package org.ifglife.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderRequest {
    @NotBlank
    public String orderId;

    @NotBlank
    public String customerName;

    @NotNull
    @Positive
    public Double amount;
}
