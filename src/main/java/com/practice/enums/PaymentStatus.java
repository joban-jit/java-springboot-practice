package com.practice.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    PENDING("Waiting for approval"),
    COMPLETED("Payment successful"),
    FAILED("Payment failed");

    private final String description;

    PaymentStatus(String description){
        this.description = description;
    }
}
